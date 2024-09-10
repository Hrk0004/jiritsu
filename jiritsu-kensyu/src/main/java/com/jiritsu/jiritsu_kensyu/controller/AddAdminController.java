package com.jiritsu.jiritsu_kensyu.controller;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jiritsu.jiritsu_kensyu.form.employee.AdminAccountForm;
import com.jiritsu.jiritsu_kensyu.security.AdminAccountUserDetailsService;
import com.jiritsu.jiritsu_kensyu.service.AdminService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;



@Controller
@RequestMapping("management/account/add")
public class AddAdminController {
    

    @Autowired
    AdminService adminService;

    @ModelAttribute("adminAccForm")
    public AdminAccountForm setupForm(){
        return new AdminAccountForm();
    }
    
    @GetMapping("form")
    public String form(){
        return "management/account/addAdminAccountForm";
    }

    @PostMapping("confirm")
    public String confirm(@Validated @ModelAttribute("adminAccForm") AdminAccountForm form,BindingResult result,Model model) {
        model.addAttribute("hiddenPass", form.getPass().replaceAll("[A-Za-z0-9]", "*"));
        model.addAttribute("adminAccForm", form);
        
        return "management/account/addAdminAccountConfirm";
    }

    @PostMapping("execute")
    public String execute(@ModelAttribute("addAccForm") AdminAccountForm form, RedirectAttributes redirectAttributes) {
        adminService.addAdmin(AdminAccountHelper.convert(form));
        redirectAttributes.addFlashAttribute("adminAccForm", form);
        return "redirect:/management/account/addAdminComplete";

    }
    
    @GetMapping("complete")
    public String complete(SessionStatus stat, @ModelAttribute("adminAccForm") AdminAccountForm form, Model model) {
        
        String name = form.getAdminName();
        stat.setComplete();
        if(name == null){
            return "redirect:/management/error";
        }
         
        model.addAttribute("adminName", form.getAdminName());
        return "management/account/account/addEmployeeAccountComplete";
    }
    
    
    
}
