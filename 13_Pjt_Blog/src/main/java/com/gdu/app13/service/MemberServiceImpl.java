package com.gdu.app13.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app13.domain.MemberDTO;
import com.gdu.app13.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void login(HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", pw);
		MemberDTO memberDTO = memberMapper.selectMemberByMap(map);  // map으로 member값 가져오기
		if(memberDTO != null) { 									// 만약에 memberDTO가 존재 한다면
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id);
			session.setAttribute("loginNo", memberDTO.getMemberNo()); // memberNo는 MemberDTO에 있기 때문에 
		}
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate(); 		// 세션의 정보를 다 지워버린다. 그러면 끝 세션 자체를 받아왔기 때문에 ;; 
	}

}
