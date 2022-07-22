package com.icerabbit.wirefish.ssh;

import com.jcraft.jsch.UserInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author iceRabbit
 * @Date 7/21/22 7:38 PM
 **/
@Slf4j
public class SSHUser implements UserInfo {
    @Override
    public String getPassphrase() {
        log.info("getPassphrase");
        return null;
    }

    @Override
    public String getPassword() {
        log.info("getPassword");
        return "8DpyZ?L41wsZ";
    }

    @Override
    public boolean promptPassword(String s) {
        log.info(s);
        return true;
    }

    @Override
    public boolean promptPassphrase(String s) {
        log.info(s);
        return false;
    }

    @Override
    public boolean promptYesNo(String s) {
        log.info(s);
        return true;
    }

    @Override
    public void showMessage(String s) {
        log.info(s);
    }
}
