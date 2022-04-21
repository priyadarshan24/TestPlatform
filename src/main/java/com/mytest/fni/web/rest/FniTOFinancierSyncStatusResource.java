package com.mytest.fni.web.rest;

import com.mytest.fni.domain.FniTOFinancierSyncStatus;
import com.mytest.fni.repository.FniTOFinancierSyncStatusRepository;
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
 * REST controller for managing {@link com.mytest.fni.domain.FniTOFinancierSyncStatus}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FniTOFinancierSyncStatusResource {

    private final Logger log = LoggerFactory.getLogger(FniTOFinancierSyncStatusResource.class);

    private static final String ENTITY_NAME = "fniTOFinancierSyncStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FniTOFinancierSyncStatusRepository fniTOFinancierSyncStatusRepository;

    public FniTOFinancierSyncStatusResource(FniTOFinancierSyncStatusRepository fniTOFinancierSyncStatusRepository) {
        this.fniTOFinancierSyncStatusRepository = fniTOFinancierSyncStatusRepository;
    }

    /**
     * {@code POST  /fni-to-financier-sync-statuses} : Create a new fniTOFinancierSyncStatus.
     *
     * @param fniTOFinancierSyncStatus the fniTOFinancierSyncStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fniTOFinancierSyncStatus, or with status {@code 400 (Bad Request)} if the fniTOFinancierSyncStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fni-to-financier-sync-statuses")
    public ResponseEntity<FniTOFinancierSyncStatus> createFniTOFinancierSyncStatus(
        @RequestBody FniTOFinancierSyncStatus fniTOFinancierSyncStatus
    ) throws URISyntaxException {
        log.debug("REST request to save FniTOFinancierSyncStatus : {}", fniTOFinancierSyncStatus);
        if (fniTOFinancierSyncStatus.getId() != null) {
            throw new BadRequestAlertException("A new fniTOFinancierSyncStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FniTOFinancierSyncStatus result = fniTOFinancierSyncStatusRepository.save(fniTOFinancierSyncStatus);
        return ResponseEntity
            .created(new URI("/api/fni-to-financier-sync-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fni-to-financier-sync-statuses/:id} : Updates an existing fniTOFinancierSyncStatus.
     *
     * @param id the id of the fniTOFinancierSyncStatus to save.
     * @param fniTOFinancierSyncStatus the fniTOFinancierSyncStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fniTOFinancierSyncStatus,
     * or with status {@code 400 (Bad Request)} if the fniTOFinancierSyncStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fniTOFinancierSyncStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fni-to-financier-sync-statuses/{id}")
    public ResponseEntity<FniTOFinancierSyncStatus> updateFniTOFinancierSyncStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FniTOFinancierSyncStatus fniTOFinancierSyncStatus
    ) throws URISyntaxException {
        log.debug("REST request to update FniTOFinancierSyncStatus : {}, {}", id, fniTOFinancierSyncStatus);
        if (fniTOFinancierSyncStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fniTOFinancierSyncStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fniTOFinancierSyncStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FniTOFinancierSyncStatus result = fniTOFinancierSyncStatusRepository.save(fniTOFinancierSyncStatus);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fniTOFinancierSyncStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /fni-to-financier-sync-statuses/:id} : Partial updates given fields of an existing fniTOFinancierSyncStatus, field will ignore if it is null
     *
     * @param id the id of the fniTOFinancierSyncStatus to save.
     * @param fniTOFinancierSyncStatus the fniTOFinancierSyncStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fniTOFinancierSyncStatus,
     * or with status {@code 400 (Bad Request)} if the fniTOFinancierSyncStatus is not valid,
     * or with status {@code 404 (Not Found)} if the fniTOFinancierSyncStatus is not found,
     * or with status {@code 500 (Internal Server Error)} if the fniTOFinancierSyncStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/fni-to-financier-sync-statuses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FniTOFinancierSyncStatus> partialUpdateFniTOFinancierSyncStatus(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FniTOFinancierSyncStatus fniTOFinancierSyncStatus
    ) throws URISyntaxException {
        log.debug("REST request to partial update FniTOFinancierSyncStatus partially : {}, {}", id, fniTOFinancierSyncStatus);
        if (fniTOFinancierSyncStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fniTOFinancierSyncStatus.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fniTOFinancierSyncStatusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FniTOFinancierSyncStatus> result = fniTOFinancierSyncStatusRepository
            .findById(fniTOFinancierSyncStatus.getId())
            .map(existingFniTOFinancierSyncStatus -> {
                if (fniTOFinancierSyncStatus.getSyncDateTimeStamp() != null) {
                    existingFniTOFinancierSyncStatus.setSyncDateTimeStamp(fniTOFinancierSyncStatus.getSyncDateTimeStamp());
                }
                if (fniTOFinancierSyncStatus.getSyncStatus() != null) {
                    existingFniTOFinancierSyncStatus.setSyncStatus(fniTOFinancierSyncStatus.getSyncStatus());
                }
                if (fniTOFinancierSyncStatus.getComments() != null) {
                    existingFniTOFinancierSyncStatus.setComments(fniTOFinancierSyncStatus.getComments());
                }

                return existingFniTOFinancierSyncStatus;
            })
            .map(fniTOFinancierSyncStatusRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fniTOFinancierSyncStatus.getId().toString())
        );
    }

    /**
     * {@code GET  /fni-to-financier-sync-statuses} : get all the fniTOFinancierSyncStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fniTOFinancierSyncStatuses in body.
     */
    @GetMapping("/fni-to-financier-sync-statuses")
    public List<FniTOFinancierSyncStatus> getAllFniTOFinancierSyncStatuses() {
        log.debug("REST request to get all FniTOFinancierSyncStatuses");
        return fniTOFinancierSyncStatusRepository.findAll();
    }

    /**
     * {@code GET  /fni-to-financier-sync-statuses/:id} : get the "id" fniTOFinancierSyncStatus.
     *
     * @param id the id of the fniTOFinancierSyncStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fniTOFinancierSyncStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fni-to-financier-sync-statuses/{id}")
    public ResponseEntity<FniTOFinancierSyncStatus> getFniTOFinancierSyncStatus(@PathVariable Long id) {
        log.debug("REST request to get FniTOFinancierSyncStatus : {}", id);
        Optional<FniTOFinancierSyncStatus> fniTOFinancierSyncStatus = fniTOFinancierSyncStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fniTOFinancierSyncStatus);
    }

    /**
     * {@code DELETE  /fni-to-financier-sync-statuses/:id} : delete the "id" fniTOFinancierSyncStatus.
     *
     * @param id the id of the fniTOFinancierSyncStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fni-to-financier-sync-statuses/{id}")
    public ResponseEntity<Void> deleteFniTOFinancierSyncStatus(@PathVariable Long id) {
        log.debug("REST request to delete FniTOFinancierSyncStatus : {}", id);
        fniTOFinancierSyncStatusRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
