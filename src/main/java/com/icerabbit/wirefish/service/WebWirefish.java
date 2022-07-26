package com.icerabbit.wirefish.service;

import com.icerabbit.wirefish.config.ApplicationConfig;
import com.icerabbit.wirefish.ssh.SSHInfo;
import com.icerabbit.wirefish.ssh.SSHUser;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

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
    public void connect(SSHUser user) {

        if (sshMap.containsKey(user.getId())) {
            log.info("already login");
            return;
        }

        try {

            JSch jsch = new JSch();
            Session session = jsch.getSession(user.getUsername(), user.getHost(), user.getPort());
            jsch.addIdentity(config.getSsh().getPrvKeyPath());
            session.setUserInfo(user);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(5000);
            Channel shell = session.openChannel("shell");
            Channel sftp = session.openChannel("sftp");
            Channel exec = session.openChannel("exec");


            shell.connect();
            sftp.connect();
            exec.connect();

            List<Channel> list = new ArrayList<>();
            list.add(shell);
            list.add(sftp);


            SSHInfo sshInfo = new SSHInfo(session, user, list);

            sshMap.put(user.getId(), sshInfo);


        } catch (JSchException e) {

            log.error("ssh login fail, {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disConnect(SSHUser user) throws JSchException {
        if (!sshMap.containsKey(user.getId())) {
            log.info("connection not exist");
            return;
        }
        SSHInfo sshInfo = sshMap.get(user.getId());

        sshInfo.getChannel().forEach(Channel::disconnect);
        sshInfo.getSession().disconnect();

        log.info("session & channel disconnected");


    }

    @Override
    public void handleData(String command) {

    }
}
