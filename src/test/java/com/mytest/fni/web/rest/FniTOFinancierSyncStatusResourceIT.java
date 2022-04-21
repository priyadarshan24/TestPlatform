package com.mytest.fni.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mytest.fni.IntegrationTest;
import com.mytest.fni.domain.FniTOFinancierSyncStatus;
import com.mytest.fni.domain.enumeration.SyncStatus;
import com.mytest.fni.repository.FniTOFinancierSyncStatusRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FniTOFinancierSyncStatusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FniTOFinancierSyncStatusResourceIT {

    private static final LocalDate DEFAULT_SYNC_DATE_TIME_STAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SYNC_DATE_TIME_STAMP = LocalDate.now(ZoneId.systemDefault());

    private static final SyncStatus DEFAULT_SYNC_STATUS = SyncStatus.PENDING;
    private static final SyncStatus UPDATED_SYNC_STATUS = SyncStatus.SUCCESS;

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fni-to-financier-sync-statuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FniTOFinancierSyncStatusRepository fniTOFinancierSyncStatusRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFniTOFinancierSyncStatusMockMvc;

    private FniTOFinancierSyncStatus fniTOFinancierSyncStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FniTOFinancierSyncStatus createEntity(EntityManager em) {
        FniTOFinancierSyncStatus fniTOFinancierSyncStatus = new FniTOFinancierSyncStatus()
            .syncDateTimeStamp(DEFAULT_SYNC_DATE_TIME_STAMP)
            .syncStatus(DEFAULT_SYNC_STATUS)
            .comments(DEFAULT_COMMENTS);
        return fniTOFinancierSyncStatus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FniTOFinancierSyncStatus createUpdatedEntity(EntityManager em) {
        FniTOFinancierSyncStatus fniTOFinancierSyncStatus = new FniTOFinancierSyncStatus()
            .syncDateTimeStamp(UPDATED_SYNC_DATE_TIME_STAMP)
            .syncStatus(UPDATED_SYNC_STATUS)
            .comments(UPDATED_COMMENTS);
        return fniTOFinancierSyncStatus;
    }

    @BeforeEach
    public void initTest() {
        fniTOFinancierSyncStatus = createEntity(em);
    }

    @Test
    @Transactional
    void createFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeCreate = fniTOFinancierSyncStatusRepository.findAll().size();
        // Create the FniTOFinancierSyncStatus
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isCreated());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeCreate + 1);
        FniTOFinancierSyncStatus testFniTOFinancierSyncStatus = fniTOFinancierSyncStatusList.get(fniTOFinancierSyncStatusList.size() - 1);
        assertThat(testFniTOFinancierSyncStatus.getSyncDateTimeStamp()).isEqualTo(DEFAULT_SYNC_DATE_TIME_STAMP);
        assertThat(testFniTOFinancierSyncStatus.getSyncStatus()).isEqualTo(DEFAULT_SYNC_STATUS);
        assertThat(testFniTOFinancierSyncStatus.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    void createFniTOFinancierSyncStatusWithExistingId() throws Exception {
        // Create the FniTOFinancierSyncStatus with an existing ID
        fniTOFinancierSyncStatus.setId(1L);

        int databaseSizeBeforeCreate = fniTOFinancierSyncStatusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFniTOFinancierSyncStatuses() throws Exception {
        // Initialize the database
        fniTOFinancierSyncStatusRepository.saveAndFlush(fniTOFinancierSyncStatus);

        // Get all the fniTOFinancierSyncStatusList
        restFniTOFinancierSyncStatusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fniTOFinancierSyncStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].syncDateTimeStamp").value(hasItem(DEFAULT_SYNC_DATE_TIME_STAMP.toString())))
            .andExpect(jsonPath("$.[*].syncStatus").value(hasItem(DEFAULT_SYNC_STATUS.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)));
    }

    @Test
    @Transactional
    void getFniTOFinancierSyncStatus() throws Exception {
        // Initialize the database
        fniTOFinancierSyncStatusRepository.saveAndFlush(fniTOFinancierSyncStatus);

        // Get the fniTOFinancierSyncStatus
        restFniTOFinancierSyncStatusMockMvc
            .perform(get(ENTITY_API_URL_ID, fniTOFinancierSyncStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fniTOFinancierSyncStatus.getId().intValue()))
            .andExpect(jsonPath("$.syncDateTimeStamp").value(DEFAULT_SYNC_DATE_TIME_STAMP.toString()))
            .andExpect(jsonPath("$.syncStatus").value(DEFAULT_SYNC_STATUS.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS));
    }

    @Test
    @Transactional
    void getNonExistingFniTOFinancierSyncStatus() throws Exception {
        // Get the fniTOFinancierSyncStatus
        restFniTOFinancierSyncStatusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFniTOFinancierSyncStatus() throws Exception {
        // Initialize the database
        fniTOFinancierSyncStatusRepository.saveAndFlush(fniTOFinancierSyncStatus);

        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();

        // Update the fniTOFinancierSyncStatus
        FniTOFinancierSyncStatus updatedFniTOFinancierSyncStatus = fniTOFinancierSyncStatusRepository
            .findById(fniTOFinancierSyncStatus.getId())
            .get();
        // Disconnect from session so that the updates on updatedFniTOFinancierSyncStatus are not directly saved in db
        em.detach(updatedFniTOFinancierSyncStatus);
        updatedFniTOFinancierSyncStatus
            .syncDateTimeStamp(UPDATED_SYNC_DATE_TIME_STAMP)
            .syncStatus(UPDATED_SYNC_STATUS)
            .comments(UPDATED_COMMENTS);

        restFniTOFinancierSyncStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFniTOFinancierSyncStatus.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedFniTOFinancierSyncStatus))
            )
            .andExpect(status().isOk());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
        FniTOFinancierSyncStatus testFniTOFinancierSyncStatus = fniTOFinancierSyncStatusList.get(fniTOFinancierSyncStatusList.size() - 1);
        assertThat(testFniTOFinancierSyncStatus.getSyncDateTimeStamp()).isEqualTo(UPDATED_SYNC_DATE_TIME_STAMP);
        assertThat(testFniTOFinancierSyncStatus.getSyncStatus()).isEqualTo(UPDATED_SYNC_STATUS);
        assertThat(testFniTOFinancierSyncStatus.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void putNonExistingFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();
        fniTOFinancierSyncStatus.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fniTOFinancierSyncStatus.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();
        fniTOFinancierSyncStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();
        fniTOFinancierSyncStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFniTOFinancierSyncStatusWithPatch() throws Exception {
        // Initialize the database
        fniTOFinancierSyncStatusRepository.saveAndFlush(fniTOFinancierSyncStatus);

        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();

        // Update the fniTOFinancierSyncStatus using partial update
        FniTOFinancierSyncStatus partialUpdatedFniTOFinancierSyncStatus = new FniTOFinancierSyncStatus();
        partialUpdatedFniTOFinancierSyncStatus.setId(fniTOFinancierSyncStatus.getId());

        restFniTOFinancierSyncStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFniTOFinancierSyncStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFniTOFinancierSyncStatus))
            )
            .andExpect(status().isOk());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
        FniTOFinancierSyncStatus testFniTOFinancierSyncStatus = fniTOFinancierSyncStatusList.get(fniTOFinancierSyncStatusList.size() - 1);
        assertThat(testFniTOFinancierSyncStatus.getSyncDateTimeStamp()).isEqualTo(DEFAULT_SYNC_DATE_TIME_STAMP);
        assertThat(testFniTOFinancierSyncStatus.getSyncStatus()).isEqualTo(DEFAULT_SYNC_STATUS);
        assertThat(testFniTOFinancierSyncStatus.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    void fullUpdateFniTOFinancierSyncStatusWithPatch() throws Exception {
        // Initialize the database
        fniTOFinancierSyncStatusRepository.saveAndFlush(fniTOFinancierSyncStatus);

        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();

        // Update the fniTOFinancierSyncStatus using partial update
        FniTOFinancierSyncStatus partialUpdatedFniTOFinancierSyncStatus = new FniTOFinancierSyncStatus();
        partialUpdatedFniTOFinancierSyncStatus.setId(fniTOFinancierSyncStatus.getId());

        partialUpdatedFniTOFinancierSyncStatus
            .syncDateTimeStamp(UPDATED_SYNC_DATE_TIME_STAMP)
            .syncStatus(UPDATED_SYNC_STATUS)
            .comments(UPDATED_COMMENTS);

        restFniTOFinancierSyncStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFniTOFinancierSyncStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFniTOFinancierSyncStatus))
            )
            .andExpect(status().isOk());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
        FniTOFinancierSyncStatus testFniTOFinancierSyncStatus = fniTOFinancierSyncStatusList.get(fniTOFinancierSyncStatusList.size() - 1);
        assertThat(testFniTOFinancierSyncStatus.getSyncDateTimeStamp()).isEqualTo(UPDATED_SYNC_DATE_TIME_STAMP);
        assertThat(testFniTOFinancierSyncStatus.getSyncStatus()).isEqualTo(UPDATED_SYNC_STATUS);
        assertThat(testFniTOFinancierSyncStatus.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    void patchNonExistingFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();
        fniTOFinancierSyncStatus.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fniTOFinancierSyncStatus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();
        fniTOFinancierSyncStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isBadRequest());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFniTOFinancierSyncStatus() throws Exception {
        int databaseSizeBeforeUpdate = fniTOFinancierSyncStatusRepository.findAll().size();
        fniTOFinancierSyncStatus.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFniTOFinancierSyncStatusMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(fniTOFinancierSyncStatus))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FniTOFinancierSyncStatus in the database
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFniTOFinancierSyncStatus() throws Exception {
        // Initialize the database
        fniTOFinancierSyncStatusRepository.saveAndFlush(fniTOFinancierSyncStatus);

        int databaseSizeBeforeDelete = fniTOFinancierSyncStatusRepository.findAll().size();

        // Delete the fniTOFinancierSyncStatus
        restFniTOFinancierSyncStatusMockMvc
            .perform(delete(ENTITY_API_URL_ID, fniTOFinancierSyncStatus.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FniTOFinancierSyncStatus> fniTOFinancierSyncStatusList = fniTOFinancierSyncStatusRepository.findAll();
        assertThat(fniTOFinancierSyncStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
