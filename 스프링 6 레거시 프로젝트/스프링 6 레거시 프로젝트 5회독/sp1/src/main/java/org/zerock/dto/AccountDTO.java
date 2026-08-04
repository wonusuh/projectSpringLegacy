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
	private static final long serialVersionUID = 1L;
	private String uid;
	private String upw;
	private String uname;
	private String email;
	private List<AccountRole> roleNames;

	// 권한 추가
	public void addRole(AccountRole role) {
		if (roleNames == null) {
			roleNames = new ArrayList<>();
		}
		roleNames.add(role);
	}

	// 권한 제거
	public void clearRoles() {
		roleNames.clear();
	}

	// 권한 조회
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
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
		return true; // 만료되지 않았음
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // 잠긴 계정 아님
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // 인증정보 활용 가능함
	}

	@Override
	public boolean isEnabled() {
		return true; // 사용 가능
	}
}
