package com.ats.hrmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ats.hrmgt.model.LvType;

public interface LvTypeRepository extends JpaRepository<LvType, Integer>{

	@Query(value="select * from tbl_lvm_lvtype where to_show_drowpdown=1",nativeQuery=true)
	List<LvType> getLvTypeList();

}
