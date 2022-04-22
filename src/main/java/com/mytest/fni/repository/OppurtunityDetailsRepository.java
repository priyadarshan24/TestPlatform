package com.mytest.fni.repository;

import com.mytest.fni.domain.OppurtunityDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OppurtunityDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OppurtunityDetailsRepository extends JpaRepository<OppurtunityDetails, Long> {}
