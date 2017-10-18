package com.taotao.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPClientTest {
	//@Test
	public void testFtpClient() {
		FTPClient client = new FTPClient();
		try {
			client.connect("192.168.136.128", 21);
			client.login("ftpuser", "1234");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.changeWorkingDirectory("/home/ftpuser/www/images");
			FileInputStream inputStream = new FileInputStream("");
			client.storeFile("123.jpg", inputStream);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
