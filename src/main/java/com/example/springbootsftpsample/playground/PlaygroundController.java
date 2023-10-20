package com.example.springbootsftpsample.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootsftpsample.services.SFTPService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

@RestController
@RequestMapping("/api/v1/playground")
public class PlaygroundController {
    @Autowired
    private SFTPService sftpService;

    @GetMapping
    public String sayHi() throws JSchException, SftpException {
        sftpService.downloadFile();
        return "Say Hi";
    }

}
