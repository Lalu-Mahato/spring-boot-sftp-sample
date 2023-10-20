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

    @GetMapping("/download")
    public String downloadFile() throws JSchException, SftpException {
        sftpService.downloadFile();
        return "File Downloaded";
    }

    @GetMapping("/upload")
    public String uploadFile() throws JSchException, SftpException {
        sftpService.uploadFile();
        return "File Uploaded";
    }

    @GetMapping("/move")
    public String moveFile() throws JSchException, SftpException {
        sftpService.moveFile();
        return "File Moved";
    }
}
