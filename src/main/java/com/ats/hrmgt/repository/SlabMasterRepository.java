package com.ats.hrmgt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.SlabMaster;


public interface SlabMasterRepository extends JpaRepository<SlabMaster, Integer>{
	
	public SlabMaster findBySlabId(int slabId);

	@Transactional
	@Modifying 
	@Query(value="DELETE FROM `tbl_slabs` WHERE `tbl_slabs`.`slab_id` = :slabId",nativeQuery=true)
	public int deleteSlabSlabById(@Param("slabId") int slabId);

	@Query(value="SELECT * FROM `tbl_slabs` ORDER BY slab_id DESC", nativeQuery=true)
	public List<SlabMaster> getAllOrderBySlabIdDesc();

	



}
