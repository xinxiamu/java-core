package com.ymu.study.ftp;

import java.io.File;
import java.io.IOException;

public class FtpTest {
	
	public static void main(String[] args) throws IOException {
		FTPUtil.storeFileToServer("127.0.0.1", "admin", "123456", "/server", new File("H:\\aa.jpg"));
	}

}
