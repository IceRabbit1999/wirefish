package com.icerabbit.wirefish.ssh;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @Author iceRabbit
 * @Date 7/21/22 7:24 PM
 **/
@Slf4j
public class Capture {
    public static void main(String[] args) throws Exception {
        JSch jsch = new JSch();

        jsch.addIdentity("/root/.ssh/id_rsa");

        String username = "root";
        String host = "127.0.0.1";
        int port = 2222;

        Session session = jsch.getSession(username, host, port);


        UserInfo ui = new SSHUser();

        session.setUserInfo(ui);
        session.setConfig("StrictHostKeyChecking", "no");

        session.connect(3000);

        ChannelExec exec = (ChannelExec) session.openChannel("exec");
        ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
        ChannelShell shell = (ChannelShell) session.openChannel("shell");
        exec.setPty(true);
        exec.setOutputStream(System.out);
        Command command = new Command();
        command.setCmd("tcpdump");
        command.setParam("-i any -w /work/test.pcap");

        InputStream in = exec.getInputStream();
        OutputStream out = exec.getOutputStream();
        InputStream err = exec.getErrStream();
        log.info(command.toString());

        ThreadFactory nThread = new ThreadFactoryBuilder().setNameFormat("test-thread-%d").build();
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(32, nThread);
        service.schedule(() -> {
            try {
                log.info("after 2s");

                out.write(3);
                out.flush();
                log.info(String.valueOf(exec.isConnected()));
                log.info(String.valueOf(exec.isClosed()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, 2, TimeUnit.SECONDS);
        exec.setCommand(command.toString());
        //exec.setCommand("echo hello world; echo world hello; ls");

        byte[] tmp = new byte[1024];


        /*byte[] tmp = new byte[1024];
        while (in.available() > 0){
            in.read(tmp,0,1024);
            System.out.println(new String(tmp));
        }*/
        //exec.setOutputStream(System.out);
        exec.connect();

        out.write("echo world hello \n".getBytes(StandardCharsets.UTF_8));





        log.info("end");
    }
}
