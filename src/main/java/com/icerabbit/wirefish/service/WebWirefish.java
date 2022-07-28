package com.icerabbit.wirefish.service;

import com.icerabbit.wirefish.config.ApplicationConfig;
import com.icerabbit.wirefish.ssh.Command;
import com.icerabbit.wirefish.ssh.SSHInfo;
import com.icerabbit.wirefish.ssh.SSHUser;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author iceRabbit
 * @Date 7/26/22 9:01 AM
 **/
@Slf4j
@Service
public class WebWirefish implements WebSSH {

    @Autowired
    ApplicationConfig config;
    private Map<String, SSHInfo> sshMap = new HashMap<>();

    @Override
    public boolean connect(SSHUser user) {

        if (sshMap.containsKey(user.getId())) {
            log.info("already login");
            return false;
        }

        try {

            JSch jsch = new JSch();
            Session session = jsch.getSession(user.getUsername(), user.getHost(), user.getPort());
            jsch.addIdentity(config.getSsh().getPrvKeyPath());
            session.setUserInfo(user);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(5000);
            ChannelShell shell = (ChannelShell) session.openChannel("shell");
            ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
            ChannelExec exec = (ChannelExec) session.openChannel("exec");
            exec.setPty(true);


            List<Channel> list = new ArrayList<>();
            list.add(shell);
            list.add(sftp);
            list.add(exec);


            SSHInfo sshInfo = new SSHInfo(session, user, list);

            sshMap.put(user.getId(), sshInfo);
            return true;

        } catch (JSchException e) {

            log.error("ssh login fail, {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean disConnect(SSHUser user) throws JSchException {
        if (!sshMap.containsKey(user.getId())) {
            log.info("connection not exist");
            return false;
        }
        SSHInfo sshInfo = sshMap.get(user.getId());

        sshInfo.getChannel().forEach(Channel::disconnect);
        sshInfo.getSession().disconnect();
        log.info("session & channel disconnected");
        sshMap.remove(user.getId());
        return true;

    }

    @Override
    public boolean execCommand(Command command) throws IOException {
        if (!sshMap.containsKey(command.getId())) {
            return false;
        }

        SSHInfo sshInfo = sshMap.get(command.getId());
        sshInfo.getChannel().forEach(channel -> {
            if (channel instanceof ChannelExec) {
                ((ChannelExec) channel).setCommand(command.toString());
                try {
                    channel.connect();

                } catch (JSchException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        return true;
    }

    @Override
    public void stop(SSHUser user) {
        SSHInfo sshInfo = sshMap.get(user.getId());
        sshInfo.getChannel().forEach(channel -> {
            if (channel instanceof ChannelExec) {
                try {
                    OutputStream out = ((ChannelExec) channel).getOutputStream();
                    out.flush();
                    out.write(3);
                    out.flush();
                    refresh(user);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void refresh(SSHUser user) throws JSchException {
        disConnect(user);
        connect(user);
    }

}
