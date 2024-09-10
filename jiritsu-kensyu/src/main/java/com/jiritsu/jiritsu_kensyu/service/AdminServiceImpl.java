package com.jiritsu.jiritsu_kensyu.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiritsu.jiritsu_kensyu.entity.employee.AdminAccount;
import com.jiritsu.jiritsu_kensyu.repository.employee.AdminAccountRepository;

import jakarta.validation.OverridesAttribute;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminAccountRepository adminAccountRepository;

    @Override
    public void addAdminAccount(AdminAccount adminAccount){
        adminAccountRepository.insert(adminAccount);
    }
    
}
