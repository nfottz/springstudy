package com.gdu.app11.batch;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app11.util.MyFileUtil;

@EnableScheduling
@Component
public class RemoveTempfileScheduler {

	/* 임시 폴더(temp)의 모든 파일을 매일 새벽 3시에 지우는 스케줄러 */
	
	@Autowired
	private MyFileUtil myFileUtil;
	
	@Scheduled(cron="0 0 3 1/1 * ?")	// www.cronmaker.com
	public void execute() {
		
		// 임시 폴더의 File 객체
		File dir = new File(myFileUtil.getTempPath());
		
		// 임시 폴더가 존재하면 폴더 내의 모든 파일을 가져와서 하나씩 삭제
		if(dir.exists()) {
			for(File file : dir.listFiles()) {
				file.delete();
			}
		}
	}
	
}
