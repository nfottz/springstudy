package com.gdu.staff.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gdu.staff.domain.StaffDTO;

public interface StaffService {
	
	public ResponseEntity<List<StaffDTO>> getStaffList();
	public ResponseEntity<String> addStaff(StaffDTO staffDTO);
	public ResponseEntity<StaffDTO> searchStaff(int sno);
}
