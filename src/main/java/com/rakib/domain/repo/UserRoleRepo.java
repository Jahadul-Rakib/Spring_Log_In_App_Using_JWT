package com.rakib.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rakib.domain.UserRole;
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
}
