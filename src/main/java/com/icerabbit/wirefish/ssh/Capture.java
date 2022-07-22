package com.icerabbit.wirefish.ssh;

import com.jcraft.jsch.*;

/**
 * @Author iceRabbit
 * @Date 7/21/22 7:24 PM
 **/
public class Capture {
    public static void main(String[] args) throws JSchException {
        JSch jsch = new JSch();
        String username = "root";
        String password = "root";
        String host = "localhost";
        int port = 2222;

        Session session = jsch.getSession(username, host, port);

        session.setPassword(password);

        UserInfo ui = new SSHUser();

        session.setUserInfo(ui);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setConfig("kex", "diffie-hellman-group1-sha1");
        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");

        session.connect(3000);

        Channel shell = session.openChannel("shell");


        shell.setInputStream(System.in);
        shell.setOutputStream(System.out);
        shell.connect();
        System.out.println("connect");


    }
}
