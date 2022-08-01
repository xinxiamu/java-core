package com.ymu.study.ftp;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTPUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
	 
    /**
     * Push a file to server
     */
    public static void storeFileToServer(String server, String username, String password, String directory, File file)
            throws IOException {
        SSLSessionReuseFTPSClient ftpsClient = new SSLSessionReuseFTPSClient();
        FTPClientConfig config = new FTPClientConfig();
        ftpsClient.configure(config);
        try {
            int reply;
            ftpsClient.connect(server);
            ftpsClient.login(username, password);
 
            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftpsClient.getReplyCode();
 
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpsClient.disconnect();
            }
 
            ftpsClient.enterLocalPassiveMode();
            ftpsClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            ftpsClient.execPROT("P");
 
            ftpsClient.changeWorkingDirectory(directory);
            ftpsClient.storeFile(file.getName(), new FileInputStream(file));
            ftpsClient.logout();
        } catch (IOException e) {
            logger.error("storeFileToServerFTPError", e);
            throw e;
        } finally {
            if (ftpsClient.isConnected()) {
                try {
                    ftpsClient.disconnect();
                } catch (IOException ioe) {
                    logger.error("storeFileToServerFTPErrorDisconnect", ioe);
                }
            }
        }
    }
 
    /**
     * Get FTP server files under the directory
     *
     * @param server
     * @param username
     * @param password
     * @param keyWord
     */
    public static List<File> getFiles(String server, String username, String password, String directory, String keyWord) {
        SSLSessionReuseFTPSClient ftpsClient = new SSLSessionReuseFTPSClient();
        FTPClientConfig config = new FTPClientConfig();
        ftpsClient.configure(config);
        try {
            int reply;
            ftpsClient.connect(server);
            ftpsClient.login(username, password);
 
            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftpsClient.getReplyCode();
 
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpsClient.disconnect();
            }
 
            ftpsClient.enterLocalPassiveMode();
            ftpsClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            ftpsClient.execPROT("P");
            List<File> files = new ArrayList<>();
 
            FTPFile[] ftpFiles = null;
            if (StringUtils.isBlank(directory)) {
                ftpFiles = ftpsClient.listFiles(ftpsClient.printWorkingDirectory());
            } else {
                ftpFiles = ftpsClient.listFiles(directory);
            }
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.isFile() && ftpFile.getName().indexOf(keyWord) >= 0) {
                    //String prefix = keyWord + "_" + DateFormatUtils.format(new Date(), "yyyy_MM_dd_HHmmss");
                    String remoteName = ftpFile.getName();
                    File tmpFile = File.createTempFile("TMP_", remoteName);
                    ftpsClient.setFileType(3);
                    ftpsClient.retrieveFile(remoteName, new FileOutputStream(tmpFile));
                    files.add(tmpFile);
                }
            }
            ftpsClient.logout();
            return files;
        } catch (IOException e) {
            logger.error("FTPError", e);
            return null;
        } finally {
            if (ftpsClient.isConnected()) {
                try {
                    ftpsClient.disconnect();
                } catch (IOException ioe) {
                    // do nothing
                }
            }
        }
    }
 
    /**
     * Get FTP server files under the directory
     *
     * @param server
     * @param username
     * @param password
     * @param keyWord
     */
    public static void doCommandRename(String server, String username, String password, String directory, String keyWord,
                                       String targetDirectory) {
        SSLSessionReuseFTPSClient ftpsClient = new SSLSessionReuseFTPSClient();
        FTPClientConfig config = new FTPClientConfig();
        ftpsClient.configure(config);
        try {
            int reply;
            ftpsClient.connect(server);
            ftpsClient.login(username, password);
 
            // After connection attempt, you should check the reply code to verify
            // success.
            reply = ftpsClient.getReplyCode();
 
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpsClient.disconnect();
            }
 
            ftpsClient.enterLocalPassiveMode();
            ftpsClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            ftpsClient.execPROT("P");
            FTPFile[] ftpFiles = null;
            if (StringUtils.isBlank(directory)) {
                ftpFiles = ftpsClient.listFiles(ftpsClient.printWorkingDirectory());
            } else {
                ftpFiles = ftpsClient.listFiles(directory);
            }
 
            for (FTPFile ftpFile : ftpFiles) {
                if (ftpFile.isFile() && ftpFile.getName().indexOf(keyWord) >= 0) {
                    ftpsClient.rename(ftpFile.getName(), targetDirectory + ftpFile.getName());
                }
            }
            ftpsClient.logout();
        } catch (IOException e) {
            logger.error("FTPError", e);
        } finally {
            if (ftpsClient.isConnected()) {
                try {
                    ftpsClient.disconnect();
                } catch (IOException ioe) {
                    // do nothing
                }
            }
        }
    }

}
