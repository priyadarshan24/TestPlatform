package com.mytest.fni.web.rest;

import com.mytest.fni.domain.OppurtunityDetails;
import com.mytest.fni.repository.OppurtunityDetailsRepository;
import com.mytest.fni.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mytest.fni.domain.OppurtunityDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OppurtunityDetailsResource {

    private final Logger log = LoggerFactory.getLogger(OppurtunityDetailsResource.class);

    private static final String ENTITY_NAME = "oppurtunityDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OppurtunityDetailsRepository oppurtunityDetailsRepository;

    public OppurtunityDetailsResource(OppurtunityDetailsRepository oppurtunityDetailsRepository) {
        this.oppurtunityDetailsRepository = oppurtunityDetailsRepository;
    }

    /**
     * {@code POST  /oppurtunity-details} : Create a new oppurtunityDetails.
     *
     * @param oppurtunityDetails the oppurtunityDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oppurtunityDetails, or with status {@code 400 (Bad Request)} if the oppurtunityDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/oppurtunity-details")
    public ResponseEntity<OppurtunityDetails> createOppurtunityDetails(@RequestBody OppurtunityDetails oppurtunityDetails)
        throws URISyntaxException {
        log.debug("REST request to save OppurtunityDetails : {}", oppurtunityDetails);
        if (oppurtunityDetails.getId() != null) {
            throw new BadRequestAlertException("A new oppurtunityDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OppurtunityDetails result = oppurtunityDetailsRepository.save(oppurtunityDetails);
        return ResponseEntity
            .created(new URI("/api/oppurtunity-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /oppurtunity-details/:id} : Updates an existing oppurtunityDetails.
     *
     * @param id the id of the oppurtunityDetails to save.
     * @param oppurtunityDetails the oppurtunityDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oppurtunityDetails,
     * or with status {@code 400 (Bad Request)} if the oppurtunityDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oppurtunityDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/oppurtunity-details/{id}")
    public ResponseEntity<OppurtunityDetails> updateOppurtunityDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OppurtunityDetails oppurtunityDetails
    ) throws URISyntaxException {
        log.debug("REST request to update OppurtunityDetails : {}, {}", id, oppurtunityDetails);
        if (oppurtunityDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, oppurtunityDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!oppurtunityDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OppurtunityDetails result = oppurtunityDetailsRepository.save(oppurtunityDetails);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, oppurtunityDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /oppurtunity-details/:id} : Partial updates given fields of an existing oppurtunityDetails, field will ignore if it is null
     *
     * @param id the id of the oppurtunityDetails to save.
     * @param oppurtunityDetails the oppurtunityDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oppurtunityDetails,
     * or with status {@code 400 (Bad Request)} if the oppurtunityDetails is not valid,
     * or with status {@code 404 (Not Found)} if the oppurtunityDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the oppurtunityDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/oppurtunity-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OppurtunityDetails> partialUpdateOppurtunityDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OppurtunityDetails oppurtunityDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update OppurtunityDetails partially : {}, {}", id, oppurtunityDetails);
        if (oppurtunityDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, oppurtunityDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!oppurtunityDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OppurtunityDetails> result = oppurtunityDetailsRepository
            .findById(oppurtunityDetails.getId())
            .map(existingOppurtunityDetails -> {
                if (oppurtunityDetails.getCrmOppurtunityID() != null) {
                    existingOppurtunityDetails.setCrmOppurtunityID(oppurtunityDetails.getCrmOppurtunityID());
                }
                if (oppurtunityDetails.getOppurtunityCreatedDate() != null) {
                    existingOppurtunityDetails.setOppurtunityCreatedDate(oppurtunityDetails.getOppurtunityCreatedDate());
                }
                if (oppurtunityDetails.getCrmCustomerID() != null) {
                    existingOppurtunityDetails.setCrmCustomerID(oppurtunityDetails.getCrmCustomerID());
                }
                if (oppurtunityDetails.getBdmName() != null) {
                    existingOppurtunityDetails.setBdmName(oppurtunityDetails.getBdmName());
                }
                if (oppurtunityDetails.getBdmID() != null) {
                    existingOppurtunityDetails.setBdmID(oppurtunityDetails.getBdmID());
                }
                if (oppurtunityDetails.getDseID() != null) {
                    existingOppurtunityDetails.setDseID(oppurtunityDetails.getDseID());
                }
                if (oppurtunityDetails.getDseName() != null) {
                    existingOppurtunityDetails.setDseName(oppurtunityDetails.getDseName());
                }
                if (oppurtunityDetails.getAccountType() != null) {
                    existingOppurtunityDetails.setAccountType(oppurtunityDetails.getAccountType());
                }
                if (oppurtunityDetails.getAccountName() != null) {
                    existingOppurtunityDetails.setAccountName(oppurtunityDetails.getAccountName());
                }
                if (oppurtunityDetails.getAccountSite() != null) {
                    existingOppurtunityDetails.setAccountSite(oppurtunityDetails.getAccountSite());
                }
                if (oppurtunityDetails.getVehicleClass() != null) {
                    existingOppurtunityDetails.setVehicleClass(oppurtunityDetails.getVehicleClass());
                }
                if (oppurtunityDetails.getVehicleVariant() != null) {
                    existingOppurtunityDetails.setVehicleVariant(oppurtunityDetails.getVehicleVariant());
                }
                if (oppurtunityDetails.getEngineCapacity() != null) {
                    existingOppurtunityDetails.setEngineCapacity(oppurtunityDetails.getEngineCapacity());
                }
                if (oppurtunityDetails.getFuelTankCapacity() != null) {
                    existingOppurtunityDetails.setFuelTankCapacity(oppurtunityDetails.getFuelTankCapacity());
                }
                if (oppurtunityDetails.getWheelBase() != null) {
                    existingOppurtunityDetails.setWheelBase(oppurtunityDetails.getWheelBase());
                }
                if (oppurtunityDetails.getPower() != null) {
                    existingOppurtunityDetails.setPower(oppurtunityDetails.getPower());
                }
                if (oppurtunityDetails.getGvwWeight() != null) {
                    existingOppurtunityDetails.setGvwWeight(oppurtunityDetails.getGvwWeight());
                }
                if (oppurtunityDetails.getPayloadWeight() != null) {
                    existingOppurtunityDetails.setPayloadWeight(oppurtunityDetails.getPayloadWeight());
                }
                if (oppurtunityDetails.getExShowRoomPrice() != null) {
                    existingOppurtunityDetails.setExShowRoomPrice(oppurtunityDetails.getExShowRoomPrice());
                }
                if (oppurtunityDetails.getOnRoadPrice() != null) {
                    existingOppurtunityDetails.setOnRoadPrice(oppurtunityDetails.getOnRoadPrice());
                }
                if (oppurtunityDetails.getLob() != null) {
                    existingOppurtunityDetails.setLob(oppurtunityDetails.getLob());
                }
                if (oppurtunityDetails.getPpl() != null) {
                    existingOppurtunityDetails.setPpl(oppurtunityDetails.getPpl());
                }
                if (oppurtunityDetails.getPl() != null) {
                    existingOppurtunityDetails.setPl(oppurtunityDetails.getPl());
                }

                return existingOppurtunityDetails;
            })
            .map(oppurtunityDetailsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, oppurtunityDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /oppurtunity-details} : get all the oppurtunityDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oppurtunityDetails in body.
     */
    @GetMapping("/oppurtunity-details")
    public List<OppurtunityDetails> getAllOppurtunityDetails() {
        log.debug("REST request to get all OppurtunityDetails");
        return oppurtunityDetailsRepository.findAll();
    }

    /**
     * {@code GET  /oppurtunity-details/:id} : get the "id" oppurtunityDetails.
     *
     * @param id the id of the oppurtunityDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oppurtunityDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/oppurtunity-details/{id}")
    public ResponseEntity<OppurtunityDetails> getOppurtunityDetails(@PathVariable Long id) {
        log.debug("REST request to get OppurtunityDetails : {}", id);
        Optional<OppurtunityDetails> oppurtunityDetails = oppurtunityDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(oppurtunityDetails);
    }

    /**
     * {@code DELETE  /oppurtunity-details/:id} : delete the "id" oppurtunityDetails.
     *
     * @param id the id of the oppurtunityDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/oppurtunity-details/{id}")
    public ResponseEntity<Void> deleteOppurtunityDetails(@PathVariable Long id) {
        log.debug("REST request to delete OppurtunityDetails : {}", id);
        oppurtunityDetailsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
