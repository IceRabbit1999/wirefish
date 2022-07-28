package com.icerabbit.wirefish.ssh;

/**
 * @Author iceRabbit
 * @Date 7/27/22 7:34 AM
 **/
public enum Cmd {

    TCPDUMP("tcpdump")

    ;
    private final String cmd;

    private Cmd(String cmd){
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}
