package com.rakib.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rakib.domain.UserInfo;
@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
	
	UserInfo getUserInfoByUserEmail(String userEmail);

}
