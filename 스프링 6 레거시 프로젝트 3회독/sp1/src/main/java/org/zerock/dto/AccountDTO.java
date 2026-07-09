package org.zerock.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class AccountDTO implements UserDetails {
    private String uid;
    private String upw;
    private String uname;
    private String email;
    private List<AccountRole> roleNames;

    public void addRole(AccountRole role) {
	if (roleNames == null) {
	    roleNames = new ArrayList<>();
	}

	roleNames.add(role);
    }

    public void clearRoles() {
	roleNames.clear();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	if (roleNames == null || roleNames.size() == 0) {
	    return List.of();
	}

	return roleNames.stream().map(accountRole -> new SimpleGrantedAuthority("ROLE_" + accountRole.name()))
		.collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
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
}
