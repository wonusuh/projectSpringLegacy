package org.zerock.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AccountDTO implements UserDetails {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String uid;
    private String upw;
    private String uname;
    private String email;
    private List<AccountRole> roleNames;

    public void addRole(AccountRole role) {
	// 방어 로직
	if (roleNames == null) {
	    roleNames = new ArrayList<>();
	}

	roleNames.add(role);
    }

    public void clearRole() {
	roleNames.clear();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	// 방어 로직
	if (roleNames == null || roleNames.size() == 0) {
	    return List.of();
	}

	return roleNames.stream().map((accountRole) -> {
	    return new SimpleGrantedAuthority("ROLE_" + accountRole.name());
	}).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
	return upw;
    }

    @Override
    public String getUsername() {
	return uname;
    }

    @Override
    public boolean isAccountNonExpired() {
	// 만료되지 않았음
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	// 잠긴 계정 아님
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	// 인증정보 활용 가능함
	return true;
    }

    @Override
    public boolean isEnabled() {
	// 사용 가능
	return true;
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//	// TODO Auto-generated method stub
//	return super.clone();
//    }
//
//    @Override
//    protected void finalize() throws Throwable {
//	// TODO Auto-generated method stub
//	super.finalize();
//    }
}
