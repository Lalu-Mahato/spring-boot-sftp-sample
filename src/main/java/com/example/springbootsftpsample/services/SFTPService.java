package com.example.springbootsftpsample.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class SFTPService {
    @Value("${sftp.username}")
    private String username;

    @Value("${sftp.password}")
    private String password;

    @Value("${sftp.port}")
    private Integer port;

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.sessionTimeout}")
    private Integer sessionTimeout;

    @Value("${sftp.channelTimeout}")
    private Integer channelTimeout;

    @Value("${sftp.remoteFile}")
    private String remoteFile;

    @Value("${sftp.localDir}")
    private String localDir;

    private ChannelSftp setupJsch() throws JSchException {
        JSch jsch = new JSch();
        Session jschSession = jsch.getSession(username, host);
        jschSession.setConfig("StrictHostKeyChecking", "no");
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

    public boolean downloadFile() throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();

        channelSftp.get(remoteFile, localDir + "sample.xlsx");
        channelSftp.exit();

        return true;
    }

    public boolean uploadFile() throws JSchException, SftpException {
        ChannelSftp channelSftp = setupJsch();
        channelSftp.connect();

        String localFile = "src/main/resources/data/sample.xlsx";
        String remoteDir = "/sftp_user/dhanam/Rejected/sample.xlsx";

        channelSftp.put(localFile, remoteDir);
        channelSftp.exit();
        return true;
    }
}
