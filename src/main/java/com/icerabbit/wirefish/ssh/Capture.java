package com.icerabbit.wirefish.ssh;

import com.jcraft.jsch.*;

import java.io.IOException;

/**
 * @Author iceRabbit
 * @Date 7/21/22 7:24 PM
 **/
public class Capture {
    public static void main(String[] args) throws JSchException, IOException {
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

        Channel shell = session.openChannel("shell");



        shell.connect();
        System.out.println("connect");


    }
}
