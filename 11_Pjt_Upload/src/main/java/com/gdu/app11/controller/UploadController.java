package com.gdu.app11.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app11.service.UploadService;

@RequestMapping("/upload")
@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
//	@GetMapping("/list.do")
//	public String list(Model model) {
//		uploadService.getUploadList(model);
//		return "upload/list";
//	}

	@GetMapping("/list.do")
	public String list(HttpServletRequest request, Model model) {
		uploadService.getUploadListUsingPagination(request, model);
		return "upload/list";
	}
	
	@GetMapping("/change/record.do")
	public String changeRecord(HttpSession session
			                 , HttpServletRequest request
			                 , @RequestParam(value="recordPerPage", required=false, defaultValue="10") int recordPerPage) {
		session.setAttribute("recordPerPage", recordPerPage);
		return "redirect:" + request.getHeader("referer");	// 현재 주소(/employees/change/record.do)의 이전 주소(Referer)로 이동하시오.
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "upload/write";
	}
	
	@PostMapping("/add.do")
	public String add(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int addResult = uploadService.addUpload(multipartRequest);
		redirectAttributes.addFlashAttribute("addResult", addResult);
		return "redirect:/upload/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	}
	
	@GetMapping("/display.do")
	public ResponseEntity<byte[]> display(@RequestParam("attachNo") int attachNo){
		return uploadService.display(attachNo);
	}
	
	@GetMapping("/download.do")
	// method에서 args는 적을수록 좋다!
	// args를 HttpServletRequest request로 받으면 서비스에서 하나로 받아서 .getParameter, .getHeader를 사용할 수도 있다!
	public ResponseEntity<Resource> download(@RequestParam("attachNo") int attachNo, @RequestHeader("User-Agent") String userAgent){
		return uploadService.download(attachNo, userAgent);
	}
	
	@GetMapping("/downloadAll.do")
	public ResponseEntity<Resource> downloadAll(@RequestParam("uploadNo") int uploadNo){
		return uploadService.downloadAll(uploadNo);
	}
	
	@PostMapping("/removeUpload.do")
	public String removeUpload(@RequestParam("uploadNo") int uploadNo, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", uploadService.removeUpload(uploadNo));
		return "redirect:/upload/list.do";
	}
	
	@PostMapping("/editUpload.do")
	public String edit(@RequestParam("uploadNo") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/edit";
	}
	
	@PostMapping("/modify.do")
	public String modify(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int modifyResult = uploadService.modifyUpload(multipartRequest);
		redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
		return "redirect:/upload/detail.do?uploadNo=" + multipartRequest.getParameter("uploadNo");
	}
	
	@GetMapping("/removeAttach.do")
	public String removeAttach(@RequestParam("uploadNo") int uploadNo, @RequestParam("attachNo") int attachNo) {
		uploadService.removeAttach(attachNo);
		return "redirect:/upload/detail.do?uploadNo=" + uploadNo;
	}
	
}
