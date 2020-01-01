package com.ats.hrmgt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	@Transactional
	@Modifying
	@Query("update User set user_pwd=:password  WHERE emp_id=:empId")
	int updateUserPassword(int empId, String password);

	@Query(value = " SELECT * FROM m_user WHERE BINARY m_user.user_pwd=:password AND  m_user.emp_id=:empId and m_user.del_status=1", nativeQuery = true)
	User getSpecificUserRecord(@Param("empId") int empId, @Param("password") String password);
}
