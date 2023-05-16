package com.gdu.app12.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app12.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

  // field
  @Autowired
  UserService userService;
  
  @GetMapping("/agree.form")
  public String agreeJsp() {
    return "user/agree";
  }
  
  @GetMapping("/join.form")
  public String joinJsp(@RequestParam(value="location", required=false) String location
                       // 파라미터 location이 전달되지 않으면 빈 문자열("")이 String location에 저장된다.
                      , @RequestParam(value="event", required=false) String event
                      , Model model) {
    model.addAttribute("location", location);
    model.addAttribute("event", event);
    return "user/join";
  }
  
  @GetMapping("/login.jsp")
  public String loginJsp() {
    return "user/login";
  }
  
  @ResponseBody
  @GetMapping(value="/verifyId.do", produces="application/json")
  public Map<String, Object> verifyId(@RequestParam("id") String id){
    return userService.verifyId(id);
  }
  
  @ResponseBody
  @GetMapping(value="/verifyEmail.do", produces="application/json")
  public Map<String, Object> verifyEmail(@RequestParam("email") String email){
    return userService.verifyEmail(email);
  }
  
  @ResponseBody
  @GetMapping(value="/sendAuthCode.do", produces="application/json")
  public Map<String, Object> sendAuthCode(@RequestParam("email") String email){
    return userService.sendAuthCode(email);
  }
  
}
