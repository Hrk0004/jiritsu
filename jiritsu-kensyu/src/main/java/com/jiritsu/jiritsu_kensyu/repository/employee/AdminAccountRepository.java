package com.jiritsu.jiritsu_kensyu.repository.employee;

import org.apache.ibatis.annotations.Mapper;

import com.jiritsu.jiritsu_kensyu.entity.employee.AdminAccount;

@Mapper
public interface AdminAccountRepository {
    void insert(AdminAccount adminAccount);
    AdminAccount selectByName(String adminName);
    
}
