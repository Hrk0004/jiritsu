package com.jiritsu.jiritsu_kensyu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jiritsu.jiritsu_kensyu.entity.employee.AdminAccount;
import com.jiritsu.jiritsu_kensyu.repository.employee.AdminAccountRepository;

@Service
public class AdminAccountUserDetailsService implements UserDetailsService {
    
    @Autowired
    AdminAccountRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminAccount account = repo.selectByName(username);
         if (account == null)
            throw new UsernameNotFoundException("ユーザが存在しません");
        return new AdminAccountUserDetails(account,
                AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }
}
