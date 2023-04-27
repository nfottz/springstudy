package com.gdu.app10.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface BbsService {
	
	public void loadBbsList(HttpServletRequest request, Model model);
	public int addBbs(HttpServletRequest request);
	public int removeBbs(int bbsNo);

}
