package com.ats.hrmgt.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.GetUserData;
 
 
public interface GetUserDataRepo extends JpaRepository<GetUserData, Integer> {

	@Query(value = " SELECT\n" + 
			"    m_user.user_pwd,\n" + 
			"    m_user.user_id,\n" + 
			"    emp_info.emp_id,\n" + 
			"    emp_info.emp_fname,\n" + 
			"    emp_info.emp_mname,\n" + 
			"    emp_info.emp_sname,\n" + 
			"    emp_info.emp_mobile1,\n" + 
			"    emp_info.emp_email\n" + 
			"FROM\n" + 
			"    m_user,\n" + 
			"    emp_info\n" + 
			"WHERE\n" + 
			"  (  emp_info.emp_mobile1 =:inputValue ||  emp_info.emp_email =:inputValue || m_user.user_name=:inputValue ) AND emp_info.del_status = 1 AND emp_info.emp_id = m_user.emp_id AND m_user.del_status=1", nativeQuery = true)
	
	GetUserData getUserByEmailId(@Param("inputValue") String inputValue
			 );
	
	/*
	 * @Query(value = " SELECT\n" + "    m_user.user_pwd,\n" +
	 * "    m_user.user_id,\n" + "    emp_info.emp_id,\n" +
	 * "    emp_info.emp_fname,\n" + "    emp_info.emp_mname,\n" +
	 * "    emp_info.emp_sname,\n" + "    emp_info.emp_mobile1,\n" +
	 * "    emp_info.emp_email\n" + "FROM\n" + "    m_user,\n" + "    emp_info\n" +
	 * "WHERE\n" +
	 * "    emp_info.emp_mobile1 =:inputValue  AND emp_info.del_status = 1 AND emp_info.emp_id = m_user.emp_id AND m_user.del_status=1"
	 * , nativeQuery = true)
	 * 
	 * GetUserData getUserByMobNo(@Param("inputValue") String inputValue );
	 */
	
}
