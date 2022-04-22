package com.mytest.fni.repository;

import com.mytest.fni.domain.FniTOFinancierSyncStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FniTOFinancierSyncStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FniTOFinancierSyncStatusRepository extends JpaRepository<FniTOFinancierSyncStatus, Long> {}
