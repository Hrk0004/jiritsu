package com.jiritsu.jiritsu_kensyu.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminAccount {
    private String adminID;
    private String adminName;
    private  String password;
}
