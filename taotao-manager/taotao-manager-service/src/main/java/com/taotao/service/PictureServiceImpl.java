package com.taotao.service;

import java.io.IOException;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWD}")
	private String FTP_PASSWD;
	@Value("${FTP_BASEPATH}")
	private String FTP_BASEPATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	/**
	 * 文件上传
	 */
	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) {

		String oldName = uploadFile.getOriginalFilename();
		String newName = IDUtils.genImageName()
				+ oldName.substring(oldName.lastIndexOf("."));

		try {
			String filePath = new DateTime().toString("/yyyy/MM/dd");

			boolean b = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWD,
					FTP_BASEPATH, filePath, newName,
					uploadFile.getInputStream());
			if(!b){
				throw new IOException();
			}

			return new PictureResult(0, IMAGE_BASE_URL + filePath + "/"
					+ newName);
		} catch (IOException e) {
			return new PictureResult(1, null, "file upload failed");
		}

	}

}
