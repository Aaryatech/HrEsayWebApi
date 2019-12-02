package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.hrmgt.model.DashboardCount;

public interface DashboardRepo extends JpaRepository<DashboardCount, Integer> {

	/*@Query(value = " select  coalesce (((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.ini_auth_emp_id=:empId) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (1))+(select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.fin_auth_emp_id=:empId) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (2))),0) as pending_request,\n"
			+ "coalesce (((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.ini_auth_emp_id=:empId) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (2))+(select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.fin_auth_emp_id=:empId) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (1))),0) as info,\n"
			+ "coalesce ((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where la.emp_id=:empId  and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (1,2)),0) as my_leave,\n"
			+ "CASE WHEN (coalesce ((select count(DISTINCT(la.emp_id)) from leave_authority la  where (la.ini_auth_emp_id=:empId or la.fin_auth_emp_id=:empId )),0) > 0)  then 1 else 0 END as is_authorized", nativeQuery = true)
	DashboardCount getDashboardCount(@Param("empId") int empId, @Param("curYrId") int curYrId);*/

/*	@Query(value = " select  coalesce ((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where ((la.ini_auth_emp_id=:empId and le.ex_int1 =1) or (la.fin_auth_emp_id=:empId and le.ex_int1=2)) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id),0) as pending_request,\n"
			+ "coalesce (((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.ini_auth_emp_id=:empId) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (2) and la.fin_auth_emp_id!=la.ini_auth_emp_id)+(select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where (la.fin_auth_emp_id=:empId) and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (1) and la.fin_auth_emp_id!=la.ini_auth_emp_id)),0) as info,\n"
			+ "coalesce ((select count(DISTINCT(le.leave_id)) from leave_authority la,leave_apply le where la.emp_id=:empId  and le.cal_yr_id=:curYrId and le.emp_id=la.emp_id and le.ex_int1 in (1,2)),0) as my_leave, CASE WHEN (coalesce ((select count(DISTINCT(la.emp_id)) from leave_authority la  where (la.ini_auth_emp_id=:empId or la.fin_auth_emp_id=:empId )),0) > 0)  then 1 else 0 END as is_authorized", nativeQuery = true)
	DashboardCount getDashboardCount(@Param("empId") int empId, @Param("curYrId") int curYrId);
*/
	
	
	/*@Query(value = " SELECT\n" + 
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))\n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le\n" + 
			"        WHERE\n" + 
			"            (\n" + 
			"                (\n" + 
			"                    la.ini_auth_emp_id = :empId AND le.ex_int1 = 1\n" + 
			"                ) OR(\n" + 
			"                    la.fin_auth_emp_id = :empId AND le.ex_int1 = 2\n" + 
			"                )\n" + 
			"            ) AND le.cal_yr_id = :curYrId AND le.emp_id = la.emp_id\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS pending_request,\n" + 
			
			"    COALESCE(\n" + 
			"        (\n" + 
			"            (\n" + 
			"            SELECT\n" + 
			"                COUNT(DISTINCT(le.leave_id))\n" + 
			"            FROM\n" + 
			"                leave_authority la,\n" + 
			"                leave_apply le\n" + 
			"            WHERE\n" + 
			"                ((la.ini_auth_emp_id = :empId)  OR ( la.emp_id=:empId AND le.ex_int1 in (2,1) ) ) AND le.cal_yr_id = :curYrId AND le.emp_id = la.emp_id AND le.ex_int1 IN(2) AND la.fin_auth_emp_id != la.ini_auth_emp_id\n" + 
			"        ) +(\n" + 
			"        SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))\n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le\n" + 
			"        WHERE\n" + 
			"            (la.fin_auth_emp_id =:empId) AND le.cal_yr_id = :curYrId AND le.emp_id = la.emp_id AND le.ex_int1 IN(1) AND la.fin_auth_emp_id != la.ini_auth_emp_id\n" + 
			"    )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"    ) AS info,\n" + 
			
			"    COALESCE(\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))\n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le\n" + 
			"        WHERE\n" + 
			"            la.emp_id = :empId AND le.cal_yr_id = :curYrId AND le.emp_id = la.emp_id AND le.ex_int1 IN(1, 2)\n" + 
			"    ),\n" + 
			"    0\n" + 
			"    ) AS my_leave,\n" + 
			
			"    (\n" + 
			"        CASE WHEN(\n" + 
			"            COALESCE(\n" + 
			"                (\n" + 
			"                SELECT\n" + 
			"                    COUNT(DISTINCT(la.emp_id))\n" + 
			"                FROM\n" + 
			"                    leave_authority la\n" + 
			"                WHERE\n" + 
			"                    (\n" + 
			"                        la.ini_auth_emp_id = :empId OR la.fin_auth_emp_id = :empId\n" + 
			"                    )\n" + 
			"            ),\n" + 
			"            0\n" + 
			"            ) > 0\n" + 
			"        ) THEN 1 ELSE 0\n" + 
			"    END\n" + 
			") AS is_authorized,\n" + 
			
			"COALESCE(\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT(cp.claim_id))\n" + 
			"    FROM\n" + 
			"        claim_authority ca,\n" + 
			"        claim_apply cp\n" + 
			"    WHERE\n" + 
			"        (\n" + 
			"            (\n" + 
			"                ca.ca_ini_auth_emp_id = :empId AND cp.ex_int1 = 1\n" + 
			"            ) OR(\n" + 
			"                ca.ca_fin_auth_emp_id = :empId AND cp.ex_int1 = 2\n" + 
			"            )\n" + 
			"        ) AND cp.emp_id = ca.emp_id\n" + 
			"),\n" + 
			"0\n" + 
			") AS pending_claim,\n" + 
			
			"COALESCE(\n" + 
			"    (\n" + 
			"        (\n" + 
			"        SELECT\n" + 
			"            COUNT(DISTINCT(cp.claim_id))\n" + 
			"        FROM\n" + 
			"            claim_authority ca,\n" + 
			"            claim_apply cp\n" + 
			"        WHERE\n" + 
			"            ((ca.ca_ini_auth_emp_id = :empId) OR ( ca.emp_id=:empId AND cp.ex_int1 in (2,1) )) AND cp.emp_id = ca.emp_id AND cp.ex_int1 IN(2) AND ca.ca_fin_auth_emp_id != ca.ca_ini_auth_emp_id\n" + 
			"    ) +(\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT(cp.claim_id))\n" + 
			"    FROM\n" + 
			"        claim_authority ca,\n" + 
			"        claim_apply cp\n" + 
			"    WHERE\n" + 
			"        (ca.ca_fin_auth_emp_id = :empId) AND cp.emp_id = ca.emp_id AND cp.ex_int1 IN(1) AND ca.ca_fin_auth_emp_id != ca.ca_ini_auth_emp_id\n" + 
			")\n" + 
			"    ),\n" + 
			"    0\n" + 
			") AS info_claim,\n" + 
			
			"COALESCE(\n" + 
			"    (\n" + 
			"    SELECT\n" + 
			"        COUNT(DISTINCT(cp.claim_id))\n" + 
			"    FROM\n" + 
			"        claim_apply cp\n" + 
			"    WHERE\n" + 
			"        cp.emp_id = :empId AND cp.ex_int1 IN(1, 2)\n" + 
			"),\n" + 
			"0\n" + 
			") AS my_claim,\n" + 
			
			"COALESCE(\n" + 
			"    CASE WHEN(\n" + 
			"        COALESCE(\n" + 
			"            (\n" + 
			"            SELECT\n" + 
			"                COUNT(DISTINCT(ca.emp_id))\n" + 
			"            FROM\n" + 
			"                claim_authority ca\n" + 
			"            WHERE\n" + 
			"                (\n" + 
			"                    ca.ca_ini_auth_emp_id = :empId OR ca.ca_fin_auth_emp_id = :empId\n" + 
			"                )\n" + 
			"        ),\n" + 
			"        0\n" + 
			"        ) > 0\n" + 
			"    ) THEN 1 ELSE 0\n" + 
			"END\n" + 
			") AS is_authorized_claim ", nativeQuery = true)
	DashboardCount getDashboardCount(@Param("empId") int empId, @Param("curYrId") int curYrId);*/

	
	
	/*@Query(value = " SELECT\n" + 
			"        COALESCE(         (         SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            (                 (                     la.ini_auth_emp_id = :empId \n" + 
			"            AND le.ex_int1 = 1                 ) \n" + 
			"            OR(                     la.fin_auth_emp_id = :empId \n" + 
			"            AND le.ex_int1 = 2                 )             ) \n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id     ),\n" + 
			"        0     ) AS pending_request,\n" + 
			"        COALESCE(         (             (             SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))             \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le             \n" + 
			"        WHERE\n" + 
			"             la.ini_auth_emp_id = :empId \n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id \n" + 
			"            AND le.ex_int1 IN(2) \n" + 
			"            AND la.fin_auth_emp_id != la.ini_auth_emp_id         ) +(SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            (la.fin_auth_emp_id =:empId) \n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id \n" + 
			"            AND le.ex_int1 IN(1) \n" + 
			"            AND la.fin_auth_emp_id != la.ini_auth_emp_id     )+(SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            la.emp_id=:empId \n" + 
			"            AND le.ex_int1 in (2,1)\n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id  \n" + 
			"             )         ),\n" + 
			"        0     ) AS info,\n" + 
			"        COALESCE(         (         SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            la.emp_id = :empId  \n" + 
			"            AND le.cal_yr_id = :curYrId  \n" + 
			"            AND le.emp_id = la.emp_id \n" + 
			"            AND le.ex_int1 IN(1, 2)     ),\n" + 
			"        0     ) AS my_leave,\n" + 
			"        (         CASE \n" + 
			"            WHEN(             COALESCE(                 (                 SELECT\n" + 
			"                COUNT(DISTINCT(la.emp_id))                 \n" + 
			"            FROM\n" + 
			"                leave_authority la                 \n" + 
			"            WHERE\n" + 
			"                (                         la.ini_auth_emp_id = :empId\n" + 
			"                OR la.fin_auth_emp_id = :empId                    )             ),\n" + 
			"            0             ) > 0         ) THEN 1 \n" + 
			"            ELSE 0     \n" + 
			"        END ) AS is_authorized,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            COUNT(DISTINCT(cp.claim_id))     \n" + 
			"        FROM\n" + 
			"            claim_authority ca,\n" + 
			"            claim_apply cp     \n" + 
			"        WHERE\n" + 
			"            (             (                 ca.ca_ini_auth_emp_id = :empId \n" + 
			"            AND cp.ex_int1 = 1             ) \n" + 
			"            OR(                 ca.ca_fin_auth_emp_id = :empId \n" + 
			"            AND cp.ex_int1 = 2             )         ) \n" + 
			"            AND cp.emp_id = ca.emp_id ),\n" + 
			"        0 ) AS pending_claim,\n" + 
			"        COALESCE(     (         (         SELECT\n" + 
			"            COUNT(DISTINCT(cp.claim_id))         \n" + 
			"        FROM\n" + 
			"            claim_authority ca,\n" + 
			"            claim_apply cp         \n" + 
			"        WHERE\n" + 
			"            ca.ca_ini_auth_emp_id = :empId \n" + 
			"            AND cp.emp_id = ca.emp_id \n" + 
			"            AND cp.ex_int1 IN(2) \n" + 
			"            AND ca.ca_fin_auth_emp_id != ca.ca_ini_auth_emp_id     ) +(     SELECT\n" + 
			"            COUNT(DISTINCT(cp.claim_id))     \n" + 
			"        FROM\n" + 
			"            claim_authority ca,\n" + 
			"            claim_apply cp     \n" + 
			"        WHERE\n" + 
			"            (ca.ca_fin_auth_emp_id = :empId) \n" + 
			"            AND cp.emp_id = ca.emp_id \n" + 
			"            AND cp.ex_int1 IN(1) \n" + 
			"            AND ca.ca_fin_auth_emp_id != ca.ca_ini_auth_emp_id )+(     SELECT\n" + 
			"            COUNT(DISTINCT(cp.claim_id))     \n" + 
			"        FROM\n" + 
			"            claim_authority ca,\n" + 
			"            claim_apply cp     \n" + 
			"        WHERE\n" + 
			"            ca.emp_id=:empId \n" + 
			"            AND cp.ex_int1 in (2,1)\n" + 
			"            AND cp.emp_id = ca.emp_id  \n" + 
			"            )     ),\n" + 
			"        0 ) AS info_claim,\n" + 
			"        COALESCE(     (     SELECT\n" + 
			"            COUNT(DISTINCT(cp.claim_id))     \n" + 
			"        FROM\n" + 
			"            claim_apply cp     \n" + 
			"        WHERE\n" + 
			"            cp.emp_id = :empId \n" + 
			"            AND cp.ex_int1 IN(1, 2) ),\n" + 
			"        0 ) AS my_claim,\n" + 
			"        COALESCE(     CASE \n" + 
			"            WHEN(         COALESCE(             (             SELECT\n" + 
			"                COUNT(DISTINCT(ca.emp_id))             \n" + 
			"            FROM\n" + 
			"                claim_authority ca             \n" + 
			"            WHERE\n" + 
			"                (                     ca.ca_ini_auth_emp_id = :empId \n" + 
			"                OR ca.ca_fin_auth_emp_id = :empId                 )         ),\n" + 
			"            0         ) > 0     ) THEN 1 \n" + 
			"            ELSE 0 \n" + 
			"        END ) AS is_authorized_claim\n" + 
			"", nativeQuery = true)*/
	//12-11-2019 akshay
	
	
	
	@Query(value = " SELECT\n" + 
			"        COALESCE(         (         SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"             ((la.ini_auth_emp_id = :empId AND le.ex_int1 = 1) OR(la.fin_auth_emp_id = :empId  AND le.ex_int1 = 2 )) AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id     ),\n" + 
			"        0     ) AS pending_request,\n" + 
			"        COALESCE(         (             (             SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))             \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le             \n" + 
			"        WHERE\n" + 
			"             la.ini_auth_emp_id = :empId \n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id \n" + 
			"            AND le.ex_int1 IN(2) \n" + 
			"            AND la.fin_auth_emp_id != la.ini_auth_emp_id         ) +(SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            (la.fin_auth_emp_id =:empId) \n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id \n" + 
			"            AND le.ex_int1 IN(1) \n" + 
			"            AND la.fin_auth_emp_id != la.ini_auth_emp_id     )+(SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            la.emp_id=:empId \n" + 
			"            AND le.ex_int1 in (2,1)\n" + 
			"            AND le.cal_yr_id = :curYrId \n" + 
			"            AND le.emp_id = la.emp_id  \n" + 
			"             )         ),\n" + 
			"        0     ) AS info,\n" + 
			"        COALESCE(         (         SELECT\n" + 
			"            COUNT(DISTINCT(le.leave_id))         \n" + 
			"        FROM\n" + 
			"            leave_authority la,\n" + 
			"            leave_apply le         \n" + 
			"        WHERE\n" + 
			"            la.emp_id = :empId  \n" + 
			"            AND le.cal_yr_id = :curYrId  \n" + 
			"            AND le.emp_id = la.emp_id \n" + 
			"            AND le.ex_int1 IN(1, 2)     ),\n" + 
			"        0     ) AS my_leave,\n" + 
			"        (         CASE \n" + 
			"            WHEN(             COALESCE(                 (                 SELECT\n" + 
			"                COUNT(DISTINCT(la.emp_id))                 \n" + 
			"            FROM\n" + 
			"                leave_authority la                 \n" + 
			"            WHERE\n" + 
			"                (                         la.ini_auth_emp_id = :empId\n" + 
			"                OR la.fin_auth_emp_id = :empId                    )             ),\n" + 
			"            0             ) > 0         ) THEN 1 \n" + 
			"            ELSE 0     \n" + 
			"        END ) AS is_authorized, 0 AS pending_claim,\n" + 
			"        0 AS info_claim,\n" + 
			"        0 AS my_claim,\n" + 
			"        0 AS is_authorized_claim", nativeQuery = true)
	DashboardCount getDashboardCount(@Param("empId") int empId, @Param("curYrId") int curYrId);
	
	
}

