package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DocList;

public interface DocListRepo extends JpaRepository<DocList, Integer>{

	@Query(value = "select dm.doctype_id,dm.doctype_key,dm.doctype_name,coalesce((select doc_image from emp_docs where emp_id=:empId and "
			+ "doctype_id=dm.doctype_id),0) as image_name,coalesce((select doc_id from emp_docs where emp_id=:empId and doctype_id=dm.doctype_id),0) as doc_id ,dm.ex_int1 as is_required from dm_emp_doctype dm where dm.del_status=1 "
			+ "order by ex_int2 asc", nativeQuery = true)
	List<DocList> getdocListByEmpId(@Param("empId") int empId);

}
