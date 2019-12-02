package com.ats.hrmgt.krakpi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.krakpi.model.GetEmpKraKpiCount;
 

public interface GetEmpKraKpiRepo extends JpaRepository<GetEmpKraKpiCount, Integer> {
	
	@Query(value = " SELECT\n" + 
			"        DISTINCT  e.emp_code,\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_fname,\n" + 
			"        e.emp_mname,\n" + 
			"        e.emp_sname,\n" + 
			"        e.emp_cat_id,\n" + 
			"        e.emp_dept_id,\n" + 
			"        e.emp_type_id,\n" + 
			"        c.emp_cat_name,\n" + 
			"        c.emp_cat_short_name,\n" + 
			"        d.emp_dept_name,\n" + 
			"        d.emp_dept_short_name,\n" + 
			"        t.emp_type_name,\n" + 
			"        t.emp_type_short_name,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(kra.kra_id)     \n" + 
			"        FROM\n" + 
			"            t_kra kra     \n" + 
			"        WHERE\n" + 
			"            kra.emp_id = e.emp_id \n" + 
			"            AND kra.del_status = 1 \n" + 
			"            AND kra.year_id =:finYrId  ) AS kra_count,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(kpi.kpi_id)     \n" + 
			"        FROM\n" + 
			"            t_kpi kpi     \n" + 
			"        WHERE\n" + 
			"            kpi.emp_id = e.emp_id \n" + 
			"            AND kpi.del_status = 1 ) AS kpi_count \n" + 
			"    FROM\n" + 
			"        t_kra kra,\n" + 
			"        emp_info e,\n" + 
			"        m_emp_category c,\n" + 
			"        m_emp_department d,\n" + 
			"        m_emp_type t \n" + 
			"    WHERE\n" + 
			"        e.emp_id = kra.emp_id \n" + 
			"        AND e.del_status = 1 \n" + 
			"        AND e.emp_cat_id = c.emp_cat_id \n" + 
			"        AND e.emp_dept_id = d.emp_dept_id \n" + 
			"        AND e.emp_type_id = t.emp_type_id \n" + 
			"        AND kra.year_id=:finYrId AND  kra.del_status=1 AND e.loc_id IN(:locIdList) \n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpKraAlloted(@Param("finYrId") int finYrId,@Param("locIdList") List<Integer> locIdList
		);
	
	@Query(value = " SELECT\n" + 
			"        DISTINCT     e.emp_code,\n" + 
			"        e.emp_id,\n" + 
			"        e.emp_fname,\n" + 
			"        e.emp_mname,\n" + 
			"        e.emp_sname,\n" + 
			"        e.emp_cat_id,\n" + 
			"        e.emp_dept_id,\n" + 
			"        e.emp_type_id,\n" + 
			"        c.emp_cat_name,\n" + 
			"        c.emp_cat_short_name,\n" + 
			"        d.emp_dept_name,\n" + 
			"        d.emp_dept_short_name,\n" + 
			"        t.emp_type_name,\n" + 
			"        t.emp_type_short_name,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(kra.kra_id)     \n" + 
			"        FROM\n" + 
			"            t_kra kra     \n" + 
			"        WHERE\n" + 
			"            kra.emp_id = e.emp_id \n" + 
			"            AND kra.del_status = 1 \n" + 
			"            AND kra.year_id = :finYrId  ) AS kra_count,\n" + 
			"        (     SELECT\n" + 
			"            COUNT(kpi.kpi_id)     \n" + 
			"        FROM\n" + 
			"            t_kpi kpi     \n" + 
			"        WHERE\n" + 
			"            kpi.emp_id = e.emp_id \n" + 
			"            AND kpi.del_status = 1 ) AS kpi_count      \n" + 
			"    FROM\n" + 
			"        t_kpi kra,\n" + 
			"        emp_info e,\n" + 
			"        m_emp_category c,\n" + 
			"        m_emp_department d,\n" + 
			"        m_emp_type t \n" + 
			"    WHERE\n" + 
			"        e.emp_id = kra.emp_id  \n" + 
			"        AND e.del_status = 1 AND kra.del_status=1 \n" + 
			"        AND  e.emp_cat_id = c.emp_cat_id \n" + 
			"        AND e.emp_dept_id = d.emp_dept_id \n" + 
			"        AND e.emp_type_id = t.emp_type_id    AND e.loc_id IN(\n" + 
			"            :locIdList \n" + 
			"        )\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpKpiAlloted(@Param("finYrId") int finYrId,@Param("locIdList") List<Integer> locIdList
		);
	
	@Query(value = " SELECT DISTINCT\n" + 
			" e.emp_id, e.emp_code, \n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_cat_id,\n" + 
			"    e.emp_dept_id,\n" + 
			"    e.emp_type_id,\n" + 
			"    c.emp_cat_name,\n" + 
			"    c.emp_cat_short_name,\n" + 
			"    d.emp_dept_name,\n" + 
			"    d.emp_dept_short_name,\n" + 
			"    t.emp_type_name,\n" + 
			"    t.emp_type_short_name,\n" + 
			"     (\n" + 
			"    SELECT\n" + 
			"        COUNT(kra.kra_id)\n" + 
			"    FROM\n" + 
			"        t_kra kra\n" + 
			"    WHERE\n" + 
			"        kra.emp_id = e.emp_id AND kra.del_status = 1 AND kra.year_id =:finYrId \n" + 
			") AS kra_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(kpi.kpi_id)\n" + 
			"    FROM\n" + 
			"        t_kpi kpi\n" + 
			"    WHERE\n" + 
			"        kpi.emp_id = e.emp_id AND kpi.del_status = 1\n" + 
			") AS kpi_count\n" + 
			"    \n" + 
			"FROM\n" + 
			"    \n" + 
			"    t_kpi kra,\n" + 
			"    emp_info e,\n" + 
			"    m_emp_category c,\n" + 
			"    m_emp_department d,\n" + 
			"    m_emp_type t\n" + 
			"WHERE\n" + 
			"  e.del_status = 1 AND e.emp_cat_id = c.emp_cat_id AND e.emp_dept_id = d.emp_dept_id AND e.emp_type_id = t.emp_type_id\n" + 
			" AND \n" + 
			"  e.emp_id NOT IN(SELECT DISTINCT e.emp_id from emp_info e,t_kpi kra where e.emp_id=kra.emp_id and kra.del_status=1) AND e.loc_id IN(:locIdList)\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpKpiNotAlloted(@Param("finYrId") int finYrId,@Param("locIdList") List<Integer> locIdList
		);
	
	@Query(value = " SELECT DISTINCT\n" + 
			"    e.emp_code,\n" + 
			"    e.emp_id,\n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_cat_id,\n" + 
			"    e.emp_dept_id,\n" + 
			"    e.emp_type_id,\n" + 
			"    c.emp_cat_name,\n" + 
			"    c.emp_cat_short_name,\n" + 
			"    d.emp_dept_name,\n" + 
			"    d.emp_dept_short_name,\n" + 
			"    t.emp_type_name,\n" + 
			"    t.emp_type_short_name,\n" + 
			"     (\n" + 
			"    SELECT\n" + 
			"        COUNT(kra.kra_id)\n" + 
			"    FROM\n" + 
			"        t_kra kra\n" + 
			"    WHERE\n" + 
			"        kra.emp_id = e.emp_id AND kra.del_status = 1 AND kra.year_id =:finYrId \n" + 
			") AS kra_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(kpi.kpi_id)\n" + 
			"    FROM\n" + 
			"        t_kpi kpi\n" + 
			"    WHERE\n" + 
			"        kpi.emp_id = e.emp_id AND kpi.del_status = 1\n" + 
			") AS kpi_count\n" + 
			"FROM\n" + 
			"   \n" + 
			"    t_kra kra,\n" + 
			"    emp_info e,\n" + 
			"    m_emp_category c,\n" + 
			"    m_emp_department d,\n" + 
			"    m_emp_type t\n" + 
			"WHERE\n" + 
			"  e.del_status = 1 AND e.emp_cat_id = c.emp_cat_id AND e.emp_dept_id = d.emp_dept_id AND e.emp_type_id = t.emp_type_id AND kra.year_id=:finYrId AND \n" + 
			"  e.emp_id NOT IN(SELECT DISTINCT e.emp_id from emp_info e,t_kra kra where e.emp_id=kra.emp_id AND kra.del_status=1) AND e.loc_id IN(:locIdList )\n" + 
			"" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpKraNotAlloted(@Param("finYrId") int finYrId,@Param("locIdList") List<Integer> locIdList
		);
	
	//empid that lies in both table
	@Query(value = " SELECT DISTINCT\n" + 
			"    e.emp_code,\n" + 
			"    e.emp_id,\n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_cat_id,\n" + 
			"    e.emp_dept_id,\n" + 
			"    e.emp_type_id,\n" + 
			"    c.emp_cat_name,\n" + 
			"    c.emp_cat_short_name,\n" + 
			"    d.emp_dept_name,\n" + 
			"    d.emp_dept_short_name,\n" + 
			"    t.emp_type_name,\n" + 
			"    t.emp_type_short_name,\n" + 
			"     (\n" + 
			"    SELECT\n" + 
			"        COUNT(kra.kra_id)\n" + 
			"    FROM\n" + 
			"        t_kra kra\n" + 
			"    WHERE\n" + 
			"        kra.emp_id = e.emp_id AND kra.del_status = 1 AND kra.year_id = :finYrId\n" + 
			") AS kra_count,\n" + 
			"(\n" + 
			"    SELECT\n" + 
			"        COUNT(kpi.kpi_id)\n" + 
			"    FROM\n" + 
			"        t_kpi kpi\n" + 
			"    WHERE\n" + 
			"        kpi.emp_id = e.emp_id AND kpi.del_status = 1\n" + 
			") AS kpi_count\n" + 
			"FROM\n" + 
			"   t_kpi kpi,\n" + 
			"    t_kra kra,\n" + 
			"    emp_info e,\n" + 
			"    m_emp_category c,\n" + 
			"    m_emp_department d,\n" + 
			"    m_emp_type t\n" + 
			"WHERE\n" + 
			"    e.emp_id = kra.emp_id AND  e.del_status = 1 AND e.emp_cat_id = c.emp_cat_id AND e.emp_dept_id = d.emp_dept_id AND e.emp_type_id = t.emp_type_id AND kra.year_id=:finYrId AND e.loc_id IN(:locIdList) \n" + 
			"    AND   e.emp_id = kpi.emp_id" + 
			"" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpBothAlloted(@Param("finYrId") int finYrId,@Param("locIdList") List<Integer> locIdList
		);
	
	
	@Query(value = " SELECT DISTINCT\n" + 
			"    e.emp_code,\n" + 
			"    e.emp_id,\n" + 
			"    e.emp_fname,\n" + 
			"    e.emp_mname,\n" + 
			"    e.emp_sname,\n" + 
			"    e.emp_cat_id,\n" + 
			"    e.emp_dept_id,\n" + 
			"    e.emp_type_id,\n" + 
			"    c.emp_cat_name,\n" + 
			"    c.emp_cat_short_name,\n" + 
			"    d.emp_dept_name,\n" + 
			"    d.emp_dept_short_name,\n" + 
			"    t.emp_type_name,\n" + 
			"    t.emp_type_short_name,\n" + 
			"     \n" + 
			"    COALESCE((\n" + 
			"      SELECT  COUNT(kra.kra_id)\n" + 
			"    FROM\n" + 
			"        t_kra kra\n" + 
			"    WHERE\n" + 
			"        kra.emp_id = e.emp_id AND kra.del_status = 1 AND kra.year_id=:finYrId)\n" + 
			" ,0) AS kra_count,\n" + 
			"\n" + 
			"  COALESCE((\n" + 
			"          SELECT  COUNT(kpi.kpi_id)\n" + 
			"    FROM\n" + 
			"        t_kpi kpi\n" + 
			"    WHERE\n" + 
			"        kpi.emp_id = e.emp_id AND kpi.del_status = 1)\n" + 
			",0) AS kpi_count\n" + 
			"FROM  \n" + 
			"    emp_info e,\n" + 
			"    m_emp_category c,\n" + 
			"    m_emp_department d,\n" + 
			"    m_emp_type t\n" + 
			"WHERE\n" + 
			"  e.del_status = 1 AND e.emp_cat_id = c.emp_cat_id AND e.emp_dept_id = d.emp_dept_id AND e.emp_type_id = t.emp_type_id AND e.loc_id IN(:locIdList)" + 
			"" + 
			"" + 
			"\n" + 
			"\n" + 
			"\n" + 
			"\n" + 
			""
			+ " ", nativeQuery = true)

	List<GetEmpKraKpiCount> getEmpAllAlloted(@Param("finYrId") int finYrId,@Param("locIdList") List<Integer> locIdList
		);
	
	
	
	
	

}
