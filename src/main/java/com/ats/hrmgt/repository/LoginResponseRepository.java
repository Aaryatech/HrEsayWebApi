package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.LoginResponse;

public interface LoginResponseRepository extends JpaRepository<LoginResponse, Integer>{

	
	@Query(value = "SELECT e.emp_id,e.emp_code,e.first_name,e.middle_name,e.surname,e.mother_name,e.email_id,u.loc_id from m_employees e, m_user u "
			+ "where e.emp_id=u.emp_id and BINARY u.user_name =:username and BINARY u.user_pwd =:password ", nativeQuery = true)
	LoginResponse loginProcess(@Param("username") String username,@Param("password") String password);

}
