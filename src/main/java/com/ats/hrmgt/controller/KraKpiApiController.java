package com.ats.hrmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.hrmgt.krakpi.model.FinancialYear;
import com.ats.hrmgt.krakpi.model.GetEmpKpiReview;
import com.ats.hrmgt.krakpi.model.GetEmpKra;
import com.ats.hrmgt.krakpi.model.GetEmpKraKpiCount;
import com.ats.hrmgt.krakpi.model.GetKraReviewList;
import com.ats.hrmgt.krakpi.model.Kpi;
import com.ats.hrmgt.krakpi.model.KpiReview;
import com.ats.hrmgt.krakpi.model.Kra;
import com.ats.hrmgt.krakpi.model.KraReview;
import com.ats.hrmgt.krakpi.repo.FinancialYearRepo;
import com.ats.hrmgt.krakpi.repo.GetEmpKpiReviewRepo;
import com.ats.hrmgt.krakpi.repo.GetEmpKraKpiRepo;
import com.ats.hrmgt.krakpi.repo.GetEmpKraRepo;
import com.ats.hrmgt.krakpi.repo.GetKraReviewListRepo;
import com.ats.hrmgt.krakpi.repo.KpiRepo;
import com.ats.hrmgt.krakpi.repo.KpiReviewRepo;
import com.ats.hrmgt.krakpi.repo.KraRepo;
import com.ats.hrmgt.krakpi.repo.KraReviewRepo;
import com.ats.hrmgt.model.ClaimProof;
import com.ats.hrmgt.model.Company;
import com.ats.hrmgt.model.Info;

@RestController
public class KraKpiApiController {
	
	@Autowired
	GetEmpKraKpiRepo getEmpKraKpiRepo;
	
	@Autowired
	FinancialYearRepo  financialYearRepo;
	
	
	@RequestMapping(value = { "/getEmpKraKpiCount" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpKraKpiCount> getEmpKraKpiCount(@RequestParam("status") int status,@RequestParam("finYrId") int finYrId
			,	@RequestParam("locIdList") List<Integer> locIdList) {

		List<GetEmpKraKpiCount> list = new ArrayList<GetEmpKraKpiCount>();
		try {

			if(status==1) {
				list = getEmpKraKpiRepo.getEmpKraAlloted(finYrId ,locIdList);
			}
			else if(status==2) {
				list = getEmpKraKpiRepo.getEmpKpiAlloted(finYrId,locIdList );
			}
			else if(status==3) {
				list = getEmpKraKpiRepo.getEmpKraNotAlloted(finYrId,locIdList );
				
			}else if(status==4) {
				list = getEmpKraKpiRepo.getEmpKpiNotAlloted(finYrId,locIdList );
				
			}else if(status==5) {
				list = getEmpKraKpiRepo.getEmpBothAlloted(finYrId,locIdList );
				
			}
			else {
				//all
				list = getEmpKraKpiRepo.getEmpAllAlloted(finYrId ,locIdList);
			}
		
		
			  System.err.println("emp List is:"+list.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	
	@RequestMapping(value = { "/getFinYearList" }, method = RequestMethod.GET)
	public @ResponseBody List<FinancialYear> getFinYearList() {

		List<FinancialYear> list = new ArrayList<FinancialYear>();
		try {

			list = financialYearRepo.findByDelStatusAndIsActive(1,1 );

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/getFinYearByFinYrId" }, method = RequestMethod.POST)
	public @ResponseBody FinancialYear getFinYearByFinYrId(@RequestParam("finYrId") int finYrId) {

		FinancialYear list = new FinancialYear();
		try {

			list = financialYearRepo.findByFinYrIdAndDelStatusAndIsActive(finYrId,1,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	//***************************KRA****************************************
	
	@Autowired
	GetEmpKraRepo getEmpKraRepo;
	
	@RequestMapping(value = { "/getEmpKraList" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpKra> getEmpKraList(@RequestParam("empId") int empId,@RequestParam("finYrId") int finYrId) {

		List<GetEmpKra> list = new ArrayList<GetEmpKra>();
		try {

			list = getEmpKraRepo.getEmpKra(empId,finYrId );
			  System.err.println("emp List is:"+list.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired
	KraRepo kraRepo;
	
	@RequestMapping(value = { "/saveKra" }, method = RequestMethod.POST)
	public @ResponseBody Kra saveKra(@RequestBody Kra kra) {

		Kra save = new Kra();
		try {

			save = kraRepo.saveAndFlush(kra);

			if (save != null) {
				save.setError(false);
			} else {

				save = new Kra();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new Kra();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}
	
	@RequestMapping(value = { "/getKraByKraId" }, method = RequestMethod.POST)
	public @ResponseBody Kra getKraByKraId(@RequestParam("kraId") int kraId) {

		Kra kra = new Kra();
		try {

			kra = kraRepo.findByKraIdAndDelStatus(kraId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return kra;

	}
	
	@RequestMapping(value = { "/deleteKra" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKra(@RequestParam("kraId") int kraId) {
		System.out.println("in deleteKra "+kraId);

		Info info = new Info();

		try {

			int delete = kraRepo.deleteKra(kraId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
	
	@Autowired
	GetKraReviewListRepo getKraReviewListRepo;
	
	@Autowired
	KraReviewRepo kraReviewRepo;

	@RequestMapping(value = { "/getEmpKraReview" }, method = RequestMethod.POST)
	public @ResponseBody List<GetKraReviewList> getEmpKraReview(@RequestParam("kraId") int kraId) {

		List<GetKraReviewList> list = new ArrayList<GetKraReviewList>();
		try {

		  list = getKraReviewListRepo.getEmpKraReviewList(kraId );
		  
			  System.err.println("emp List is:"+list.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = { "/saveKraReview" }, method = RequestMethod.POST)
	public @ResponseBody KraReview saveKraReview(@RequestBody KraReview kra) {

		KraReview save = new KraReview();
		try {

			save = kraReviewRepo.saveAndFlush(kra);

			if (save != null) {
				save.setError(false);
			} else {

				save = new KraReview();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new KraReview();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}
	
	@RequestMapping(value = { "/deleteKraReview" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKraReview(@RequestParam("kraReviewId") int kraReviewId) {
		System.out.println("in deleteKra "+kraReviewId);

		Info info = new Info();

		try {

			int delete = kraReviewRepo.deleteKraReview(kraReviewId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
	
	
	@RequestMapping(value = { "/getKraReviewByKraReviewId" }, method = RequestMethod.POST)
	public @ResponseBody KraReview getKraReviewByKraReviewId(@RequestParam("kraReviewId") int kraReviewId) {

		KraReview kra = new KraReview();
		try {

			kra = kraReviewRepo.findByKraReviewIdAndDelStatus(kraReviewId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return kra;

	}
	
	//**************************************KPI*****************************************
	@Autowired
	KpiRepo kpiRepo;
	
	@RequestMapping(value = { "/deleteKpiOfKra" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKpiOfKra(@RequestParam("kraId") int kraId
			) {

		System.err.println("kra in deleteKpiOfKra ::"+kraId);
		Info info = new Info();

		try {

			int delete = kpiRepo.updateKpi(kraId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("kpi of kra deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
	
	@RequestMapping(value = { "/saveKpi" }, method = RequestMethod.POST)
	public @ResponseBody Kpi saveKpi(@RequestBody Kpi kpi) {

		Kpi save = new Kpi();
		try {

			save = kpiRepo.saveAndFlush(kpi);

			if (save != null) {
				save.setError(false);
			} else {

				save = new Kpi();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new Kpi();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}
	
	@RequestMapping(value = { "/deleteKpi" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKpi(@RequestParam("kpiId") int kpiId) {

		Info info = new Info();

		try {

			int delete = kpiRepo.deleteKpi(kpiId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
	
	
	@RequestMapping(value = { "/getKpiByKpiId" }, method = RequestMethod.POST)
	public @ResponseBody Kpi getKpiByKpiId(@RequestParam("kpiId") int kpiId) {

		Kpi kpi = new Kpi();
		try {

			kpi = kpiRepo.findByKpiIdAndDelStatus(kpiId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return kpi;

	}
	
	
	@RequestMapping(value = { "/getKpiByKraId" }, method = RequestMethod.POST)
	public @ResponseBody List<Kpi> getKpiByKpraId(@RequestParam("kraId") int kraId) {

		 
		List<Kpi> list = new ArrayList<Kpi>();
		try {

			list = kpiRepo.findByKraIdAndDelStatus(kraId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired
	GetEmpKpiReviewRepo getEmpKpiReviewRepo;
	
	@RequestMapping(value = { "/getEmpKpiReview" }, method = RequestMethod.POST)
	public @ResponseBody List<GetEmpKpiReview> getEmpKpiReview(@RequestParam("kpiId") int kpiId) {

		List<GetEmpKpiReview> list = new ArrayList<GetEmpKpiReview>();
		try {

		  list = getEmpKpiReviewRepo.getEmpKpiReviewList(kpiId);
		  
			  System.err.println("emp List is:"+list.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;

	}
	
	@Autowired
	KpiReviewRepo kpiReviewRepo;
	@RequestMapping(value = { "/saveKpiReview" }, method = RequestMethod.POST)
	public @ResponseBody KpiReview saveKpiReview(@RequestBody KpiReview kra) {

		KpiReview save = new KpiReview();
		try {

			save = kpiReviewRepo.saveAndFlush(kra);

			if (save != null) {
				save.setError(false);
			} else {

				save = new KpiReview();
				save.setError(true);
			}

		} catch (Exception e) {
			save = new KpiReview();
			save.setError(true);
			e.printStackTrace();
		}

		return save;
	}
	
	@RequestMapping(value = { "/deleteKpiReview" }, method = RequestMethod.POST)
	public @ResponseBody Info deleteKpiReview(@RequestParam("kpiReviewId") int kpiReviewId) {
		System.out.println("in delete Kpi Review "+kpiReviewId);

		Info info = new Info();

		try {

			int delete = kpiReviewRepo.deleteKpiReview(kpiReviewId);

			if (delete > 0) {
				info.setError(false);
				info.setMsg("deleted");
			} else {
				info.setError(true);
				info.setMsg("failed");
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("failed");
		}

		return info;

	}
	
	
	@RequestMapping(value = { "/getKpiReviewByKpiReviewId" }, method = RequestMethod.POST)
	public @ResponseBody KpiReview getKpiReviewByKpiReviewId(@RequestParam("kpiReviewId") int kpiReviewId) {

		KpiReview kra = new KpiReview();
		try {

			kra = kpiReviewRepo.findByKpiReviewIdAndDelStatus(kpiReviewId,1);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return kra;

	}
	

}
