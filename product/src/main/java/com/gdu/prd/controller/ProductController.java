package com.gdu.prd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.prd.domain.ProductDTO;
import com.gdu.prd.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		productService.loadProductList(model);
		return "product/list";
	}
	
	@PostMapping("/add.do")
	public String add(ProductDTO productDTO, RedirectAttributes redirectAttributes) {
		int addResult = productService.addProduct(productDTO);
		redirectAttributes.addFlashAttribute("addResult", addResult);
		return "redirect:/product/list.do";
	}
	
//	@PostMapping("/add.do")
//	public String add(String prodName, int prodPrice, RedirectAttributes redirectAttributes) {
//		int addResult = productService.addProduct(prodName, prodPrice);
//		redirectAttributes.addFlashAttribute("addResult", addResult);
//		return "redirect:/product/list.do";
//	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="prodNo", required=false, defaultValue="0") int prodNo, Model model) {
		productService.loadProduct(prodNo, model);
		return "product/detail";
	}
	
	@PostMapping("/edit.do")
	public String edit(ProductDTO product) {
		// 객체로 받을 경우 자동으로 전달된다.
		return "product/edit";
	}
	
}
