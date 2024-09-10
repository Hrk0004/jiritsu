package com.jiritsu.jiritsu_kensyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    
 @GetMapping("login")
 public String login() {
     return "management/login";
 }
 @GetMapping("menu")
 public String menu() {
     return "menu";
 }
 
 
}
