package com.icerabbit.wirefish.ssh;

import lombok.Data;

/**
 * @Author iceRabbit
 * @Date 7/27/22 7:20 AM
 **/
@Data
public class Command {

    private String cmd;
    private String param;
    private String id;

    @Override
    public String toString() {
        return cmd + " " + param;
    }
}
