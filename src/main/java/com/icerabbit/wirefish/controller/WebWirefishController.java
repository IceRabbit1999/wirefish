package com.icerabbit.wirefish.controller;

import com.icerabbit.wirefish.io.Response;
import com.icerabbit.wirefish.service.WebSSH;
import com.icerabbit.wirefish.ssh.SSHUser;
import com.jcraft.jsch.JSchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author iceRabbit
 * @Date 7/26/22 9:23 AM
 **/
@RestController
@RequestMapping("/wirefish")
@Slf4j
public class WebWirefishController {

    @Autowired
    WebSSH wirefish;

    @PostMapping(value = "/login")
    public Response<SSHUser> login(@RequestBody SSHUser user) {
        wirefish.connect(user);
        log.info("ssh login success");
        return Response.success(user);
    }

    @PostMapping("/logout")
    public Response<SSHUser> logout(@RequestBody SSHUser user) throws JSchException {
        wirefish.disConnect(user);
        log.info("ssh logout success");
        return Response.success(user);
    }



}
