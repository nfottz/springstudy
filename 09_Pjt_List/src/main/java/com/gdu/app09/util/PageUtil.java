package com.gdu.app09.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PageUtil {

	private int page;			// 현재 페이지(파라미터로 받아온다)
	private int totalRecord;	// 전체 레코드 개수(DB에서 구해온다)
	private int recordPerPage;	// 한 페이지에 표시할 레코드 개수(파라미터로 받아온다)
	private int begin;			// 한 페이지에 표시할 레코드의 시작 번호(계산한다)
	private int end;			// 한 페이지에 표시할 레코드의 종료 번호(계산한다)
	
	private int pagePerBlock = 5;	// 한 블록에 표시할 페이지의 개수(임의로 정한다)
	private int totalPage;			// 전체 페이지 개수(계산한다)
	private int beginPage;			// 한 블록에 표시할 페이지의 시작 번호(계산한다)
	private int endPage;			// 한 블록에 표시할 페이지의 종료 번호(계산한다)
	
	public void setPageUtil(int page, int totalRecord, int recordPerPage) {
		
		// page, totalRecord, recordPerPage 저장
		this.page = page;
		this.totalRecord = totalRecord;
		this.recordPerPage = recordPerPage;
		
		// begin, end 계산
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;
		if(end > totalRecord) end = totalRecord;
//		end = begin + recordPerPage - 1 < totalRecord? begin + recordPerPage - 1 : totalRecord;
		
		// totalPage 계산
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) totalPage++;
//		totalPage = totalRecord % recordPerPage == 0? totalRecord / recordPerPage : totalRecord / recordPerPage + 1;
//		totalPage = (totalRecord + recordPerPage - 1) / recordPerPage;
		
		// beginPage, endPage 계산
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) endPage = totalPage;
		
	}
	
}
