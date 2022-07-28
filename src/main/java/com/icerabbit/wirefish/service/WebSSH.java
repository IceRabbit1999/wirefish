package com.icerabbit.wirefish.service;

import com.icerabbit.wirefish.ssh.Command;
import com.icerabbit.wirefish.ssh.SSHUser;
import com.jcraft.jsch.JSchException;

import java.io.IOException;

/**
 * @Author iceRabbit
 * @Date 7/22/22 2:22 PM
 **/
public interface WebSSH {

    boolean connect(SSHUser user);

    boolean disConnect(SSHUser user) throws JSchException;

    boolean execCommand(Command command) throws IOException;

    void stop(SSHUser user);

}
