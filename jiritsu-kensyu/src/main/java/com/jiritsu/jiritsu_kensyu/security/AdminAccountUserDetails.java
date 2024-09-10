package com.jiritsu.jiritsu_kensyu.security;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import com.jiritsu.jiritsu_kensyu.entity.employee.AdminAccount;
import org.springframework.security.core.GrantedAuthority;

public class AdminAccountUserDetails implements UserDetails {
    
    private final AdminAccount account;
    private final Collection<GrantedAuthority> authorities;
    

    public AdminAccountUserDetails(AdminAccount account, Collection<GrantedAuthority> auth){
        this.account =account;
        authorities= auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getAdminName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
