package com.icerabbit.wirefish.service;

import com.icerabbit.wirefish.ssh.SSHUser;
import com.jcraft.jsch.JSchException;

/**
 * @Author iceRabbit
 * @Date 7/22/22 2:22 PM
 **/
public interface WebSSH {

    void connect(SSHUser user);

    void disConnect(SSHUser user) throws JSchException;

    void handleData(String command);


}
