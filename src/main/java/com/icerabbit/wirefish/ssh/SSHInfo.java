package com.icerabbit.wirefish.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import lombok.Data;

import java.util.List;

/**
 * @Author iceRabbit
 * @Date 7/26/22 11:14 AM
 **/
@Data
public class SSHInfo {
    private Session session;
    private SSHUser user;
    private List<Channel> channel;

    public SSHInfo(Session session, SSHUser user, List<Channel> list) {
        this.session = session;
        this.user = user;
        this.channel = list;
    }
}
