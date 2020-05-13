package com.rakib.service.impl;
import org.springframework.stereotype.Service;
import com.rakib.domain.UserRole;
import com.rakib.domain.repo.UserRoleRepo;
import com.rakib.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	UserRoleRepo roleRepo;
	public RoleServiceImpl(UserRoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}


	@Override
	public UserRole saveRole(UserRole role) {
		return roleRepo.save(role);
	}


}
