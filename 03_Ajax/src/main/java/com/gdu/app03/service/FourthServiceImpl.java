package com.gdu.app03.service;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class FourthServiceImpl implements IFourthService {

	@Override
	public ResponseEntity<byte[]> display(String path, String filename) {
		
		try {
			// path와 filename을 이용해서 File 객체 만들기
			File file = new File(path, filename);
			
			byte[] b = FileCopyUtils.copyToByteArray(file);
			
			return new ResponseEntity<byte[]>(b, HttpStatus.OK);	// 진짜 반환,,
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;	// 문법을 맞추기 위한 가짜 반환,,
	}

}
