package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.domain.UploadDTO;

@Mapper
public interface UploadMapper {

	// getUploadList
	public List<UploadDTO> getUploadList();
	
	// getUploadListUsingPagination
	public List<UploadDTO> getUploadListUsingPagination(Map<String, Object> map);
	public int getUploadCount();
	
	// addUpload
	public int addUpload(UploadDTO uploadDTO);
	public int addAttach(AttachDTO attachDTO);
	
	// getUploadByNo
	public UploadDTO getUploadByNo(int uploadNo);
	public List<AttachDTO> getAttachList(int uploadNo);
	
	// display, download
	public AttachDTO getAttachByNo(int attachNo);
	
	// download
	public int increaseDownloadCount(int attachNo);
	
	// downloadAll
	// public int addAttach(AttachDTO attachDTO);
	
	// removeUpload
	public int removeUpload(int uploadNo);
	
	// modifyUpload
	public int modifyUpload(UploadDTO uploadDTO);
	
	// removeAttach
	public int removeAttach(int attachNo);
	
	// RemoveWrongfileScheduler
	public List<AttachDTO> getAttachListInYesterday();
}
