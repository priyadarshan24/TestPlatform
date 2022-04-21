package com.mytest.fni.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mytest.fni.IntegrationTest;
import com.mytest.fni.domain.OppurtunityDetails;
import com.mytest.fni.repository.OppurtunityDetailsRepository;
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
 * Integration tests for the {@link OppurtunityDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OppurtunityDetailsResourceIT {

    private static final String DEFAULT_CRM_OPPURTUNITY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRM_OPPURTUNITY_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OPPURTUNITY_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OPPURTUNITY_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_CRM_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CRM_CUSTOMER_ID = 2L;

    private static final String DEFAULT_BDM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BDM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BDM_ID = "AAAAAAAAAA";
    private static final String UPDATED_BDM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DSE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DSE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_SITE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLE_VARIANT = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_VARIANT = "BBBBBBBBBB";

    private static final String DEFAULT_ENGINE_CAPACITY = "AAAAAAAAAA";
    private static final String UPDATED_ENGINE_CAPACITY = "BBBBBBBBBB";

    private static final String DEFAULT_FUEL_TANK_CAPACITY = "AAAAAAAAAA";
    private static final String UPDATED_FUEL_TANK_CAPACITY = "BBBBBBBBBB";

    private static final String DEFAULT_WHEEL_BASE = "AAAAAAAAAA";
    private static final String UPDATED_WHEEL_BASE = "BBBBBBBBBB";

    private static final String DEFAULT_POWER = "AAAAAAAAAA";
    private static final String UPDATED_POWER = "BBBBBBBBBB";

    private static final String DEFAULT_GVW_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_GVW_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_PAYLOAD_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_PAYLOAD_WEIGHT = "BBBBBBBBBB";

    private static final Long DEFAULT_EX_SHOW_ROOM_PRICE = 1L;
    private static final Long UPDATED_EX_SHOW_ROOM_PRICE = 2L;

    private static final Long DEFAULT_ON_ROAD_PRICE = 1L;
    private static final Long UPDATED_ON_ROAD_PRICE = 2L;

    private static final String DEFAULT_LOB = "AAAAAAAAAA";
    private static final String UPDATED_LOB = "BBBBBBBBBB";

    private static final String DEFAULT_PPL = "AAAAAAAAAA";
    private static final String UPDATED_PPL = "BBBBBBBBBB";

    private static final String DEFAULT_PL = "AAAAAAAAAA";
    private static final String UPDATED_PL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/oppurtunity-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OppurtunityDetailsRepository oppurtunityDetailsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOppurtunityDetailsMockMvc;

    private OppurtunityDetails oppurtunityDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OppurtunityDetails createEntity(EntityManager em) {
        OppurtunityDetails oppurtunityDetails = new OppurtunityDetails()
            .crmOppurtunityID(DEFAULT_CRM_OPPURTUNITY_ID)
            .oppurtunityCreatedDate(DEFAULT_OPPURTUNITY_CREATED_DATE)
            .crmCustomerID(DEFAULT_CRM_CUSTOMER_ID)
            .bdmName(DEFAULT_BDM_NAME)
            .bdmID(DEFAULT_BDM_ID)
            .dseID(DEFAULT_DSE_ID)
            .dseName(DEFAULT_DSE_NAME)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .accountSite(DEFAULT_ACCOUNT_SITE)
            .vehicleClass(DEFAULT_VEHICLE_CLASS)
            .vehicleVariant(DEFAULT_VEHICLE_VARIANT)
            .engineCapacity(DEFAULT_ENGINE_CAPACITY)
            .fuelTankCapacity(DEFAULT_FUEL_TANK_CAPACITY)
            .wheelBase(DEFAULT_WHEEL_BASE)
            .power(DEFAULT_POWER)
            .gvwWeight(DEFAULT_GVW_WEIGHT)
            .payloadWeight(DEFAULT_PAYLOAD_WEIGHT)
            .exShowRoomPrice(DEFAULT_EX_SHOW_ROOM_PRICE)
            .onRoadPrice(DEFAULT_ON_ROAD_PRICE)
            .lob(DEFAULT_LOB)
            .ppl(DEFAULT_PPL)
            .pl(DEFAULT_PL);
        return oppurtunityDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OppurtunityDetails createUpdatedEntity(EntityManager em) {
        OppurtunityDetails oppurtunityDetails = new OppurtunityDetails()
            .crmOppurtunityID(UPDATED_CRM_OPPURTUNITY_ID)
            .oppurtunityCreatedDate(UPDATED_OPPURTUNITY_CREATED_DATE)
            .crmCustomerID(UPDATED_CRM_CUSTOMER_ID)
            .bdmName(UPDATED_BDM_NAME)
            .bdmID(UPDATED_BDM_ID)
            .dseID(UPDATED_DSE_ID)
            .dseName(UPDATED_DSE_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountSite(UPDATED_ACCOUNT_SITE)
            .vehicleClass(UPDATED_VEHICLE_CLASS)
            .vehicleVariant(UPDATED_VEHICLE_VARIANT)
            .engineCapacity(UPDATED_ENGINE_CAPACITY)
            .fuelTankCapacity(UPDATED_FUEL_TANK_CAPACITY)
            .wheelBase(UPDATED_WHEEL_BASE)
            .power(UPDATED_POWER)
            .gvwWeight(UPDATED_GVW_WEIGHT)
            .payloadWeight(UPDATED_PAYLOAD_WEIGHT)
            .exShowRoomPrice(UPDATED_EX_SHOW_ROOM_PRICE)
            .onRoadPrice(UPDATED_ON_ROAD_PRICE)
            .lob(UPDATED_LOB)
            .ppl(UPDATED_PPL)
            .pl(UPDATED_PL);
        return oppurtunityDetails;
    }

    @BeforeEach
    public void initTest() {
        oppurtunityDetails = createEntity(em);
    }

    @Test
    @Transactional
    void createOppurtunityDetails() throws Exception {
        int databaseSizeBeforeCreate = oppurtunityDetailsRepository.findAll().size();
        // Create the OppurtunityDetails
        restOppurtunityDetailsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isCreated());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        OppurtunityDetails testOppurtunityDetails = oppurtunityDetailsList.get(oppurtunityDetailsList.size() - 1);
        assertThat(testOppurtunityDetails.getCrmOppurtunityID()).isEqualTo(DEFAULT_CRM_OPPURTUNITY_ID);
        assertThat(testOppurtunityDetails.getOppurtunityCreatedDate()).isEqualTo(DEFAULT_OPPURTUNITY_CREATED_DATE);
        assertThat(testOppurtunityDetails.getCrmCustomerID()).isEqualTo(DEFAULT_CRM_CUSTOMER_ID);
        assertThat(testOppurtunityDetails.getBdmName()).isEqualTo(DEFAULT_BDM_NAME);
        assertThat(testOppurtunityDetails.getBdmID()).isEqualTo(DEFAULT_BDM_ID);
        assertThat(testOppurtunityDetails.getDseID()).isEqualTo(DEFAULT_DSE_ID);
        assertThat(testOppurtunityDetails.getDseName()).isEqualTo(DEFAULT_DSE_NAME);
        assertThat(testOppurtunityDetails.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testOppurtunityDetails.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testOppurtunityDetails.getAccountSite()).isEqualTo(DEFAULT_ACCOUNT_SITE);
        assertThat(testOppurtunityDetails.getVehicleClass()).isEqualTo(DEFAULT_VEHICLE_CLASS);
        assertThat(testOppurtunityDetails.getVehicleVariant()).isEqualTo(DEFAULT_VEHICLE_VARIANT);
        assertThat(testOppurtunityDetails.getEngineCapacity()).isEqualTo(DEFAULT_ENGINE_CAPACITY);
        assertThat(testOppurtunityDetails.getFuelTankCapacity()).isEqualTo(DEFAULT_FUEL_TANK_CAPACITY);
        assertThat(testOppurtunityDetails.getWheelBase()).isEqualTo(DEFAULT_WHEEL_BASE);
        assertThat(testOppurtunityDetails.getPower()).isEqualTo(DEFAULT_POWER);
        assertThat(testOppurtunityDetails.getGvwWeight()).isEqualTo(DEFAULT_GVW_WEIGHT);
        assertThat(testOppurtunityDetails.getPayloadWeight()).isEqualTo(DEFAULT_PAYLOAD_WEIGHT);
        assertThat(testOppurtunityDetails.getExShowRoomPrice()).isEqualTo(DEFAULT_EX_SHOW_ROOM_PRICE);
        assertThat(testOppurtunityDetails.getOnRoadPrice()).isEqualTo(DEFAULT_ON_ROAD_PRICE);
        assertThat(testOppurtunityDetails.getLob()).isEqualTo(DEFAULT_LOB);
        assertThat(testOppurtunityDetails.getPpl()).isEqualTo(DEFAULT_PPL);
        assertThat(testOppurtunityDetails.getPl()).isEqualTo(DEFAULT_PL);
    }

    @Test
    @Transactional
    void createOppurtunityDetailsWithExistingId() throws Exception {
        // Create the OppurtunityDetails with an existing ID
        oppurtunityDetails.setId(1L);

        int databaseSizeBeforeCreate = oppurtunityDetailsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOppurtunityDetailsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOppurtunityDetails() throws Exception {
        // Initialize the database
        oppurtunityDetailsRepository.saveAndFlush(oppurtunityDetails);

        // Get all the oppurtunityDetailsList
        restOppurtunityDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oppurtunityDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].crmOppurtunityID").value(hasItem(DEFAULT_CRM_OPPURTUNITY_ID)))
            .andExpect(jsonPath("$.[*].oppurtunityCreatedDate").value(hasItem(DEFAULT_OPPURTUNITY_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].crmCustomerID").value(hasItem(DEFAULT_CRM_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].bdmName").value(hasItem(DEFAULT_BDM_NAME)))
            .andExpect(jsonPath("$.[*].bdmID").value(hasItem(DEFAULT_BDM_ID)))
            .andExpect(jsonPath("$.[*].dseID").value(hasItem(DEFAULT_DSE_ID)))
            .andExpect(jsonPath("$.[*].dseName").value(hasItem(DEFAULT_DSE_NAME)))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].accountSite").value(hasItem(DEFAULT_ACCOUNT_SITE)))
            .andExpect(jsonPath("$.[*].vehicleClass").value(hasItem(DEFAULT_VEHICLE_CLASS)))
            .andExpect(jsonPath("$.[*].vehicleVariant").value(hasItem(DEFAULT_VEHICLE_VARIANT)))
            .andExpect(jsonPath("$.[*].engineCapacity").value(hasItem(DEFAULT_ENGINE_CAPACITY)))
            .andExpect(jsonPath("$.[*].fuelTankCapacity").value(hasItem(DEFAULT_FUEL_TANK_CAPACITY)))
            .andExpect(jsonPath("$.[*].wheelBase").value(hasItem(DEFAULT_WHEEL_BASE)))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER)))
            .andExpect(jsonPath("$.[*].gvwWeight").value(hasItem(DEFAULT_GVW_WEIGHT)))
            .andExpect(jsonPath("$.[*].payloadWeight").value(hasItem(DEFAULT_PAYLOAD_WEIGHT)))
            .andExpect(jsonPath("$.[*].exShowRoomPrice").value(hasItem(DEFAULT_EX_SHOW_ROOM_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].onRoadPrice").value(hasItem(DEFAULT_ON_ROAD_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].lob").value(hasItem(DEFAULT_LOB)))
            .andExpect(jsonPath("$.[*].ppl").value(hasItem(DEFAULT_PPL)))
            .andExpect(jsonPath("$.[*].pl").value(hasItem(DEFAULT_PL)));
    }

    @Test
    @Transactional
    void getOppurtunityDetails() throws Exception {
        // Initialize the database
        oppurtunityDetailsRepository.saveAndFlush(oppurtunityDetails);

        // Get the oppurtunityDetails
        restOppurtunityDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, oppurtunityDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(oppurtunityDetails.getId().intValue()))
            .andExpect(jsonPath("$.crmOppurtunityID").value(DEFAULT_CRM_OPPURTUNITY_ID))
            .andExpect(jsonPath("$.oppurtunityCreatedDate").value(DEFAULT_OPPURTUNITY_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.crmCustomerID").value(DEFAULT_CRM_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.bdmName").value(DEFAULT_BDM_NAME))
            .andExpect(jsonPath("$.bdmID").value(DEFAULT_BDM_ID))
            .andExpect(jsonPath("$.dseID").value(DEFAULT_DSE_ID))
            .andExpect(jsonPath("$.dseName").value(DEFAULT_DSE_NAME))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.accountSite").value(DEFAULT_ACCOUNT_SITE))
            .andExpect(jsonPath("$.vehicleClass").value(DEFAULT_VEHICLE_CLASS))
            .andExpect(jsonPath("$.vehicleVariant").value(DEFAULT_VEHICLE_VARIANT))
            .andExpect(jsonPath("$.engineCapacity").value(DEFAULT_ENGINE_CAPACITY))
            .andExpect(jsonPath("$.fuelTankCapacity").value(DEFAULT_FUEL_TANK_CAPACITY))
            .andExpect(jsonPath("$.wheelBase").value(DEFAULT_WHEEL_BASE))
            .andExpect(jsonPath("$.power").value(DEFAULT_POWER))
            .andExpect(jsonPath("$.gvwWeight").value(DEFAULT_GVW_WEIGHT))
            .andExpect(jsonPath("$.payloadWeight").value(DEFAULT_PAYLOAD_WEIGHT))
            .andExpect(jsonPath("$.exShowRoomPrice").value(DEFAULT_EX_SHOW_ROOM_PRICE.intValue()))
            .andExpect(jsonPath("$.onRoadPrice").value(DEFAULT_ON_ROAD_PRICE.intValue()))
            .andExpect(jsonPath("$.lob").value(DEFAULT_LOB))
            .andExpect(jsonPath("$.ppl").value(DEFAULT_PPL))
            .andExpect(jsonPath("$.pl").value(DEFAULT_PL));
    }

    @Test
    @Transactional
    void getNonExistingOppurtunityDetails() throws Exception {
        // Get the oppurtunityDetails
        restOppurtunityDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOppurtunityDetails() throws Exception {
        // Initialize the database
        oppurtunityDetailsRepository.saveAndFlush(oppurtunityDetails);

        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();

        // Update the oppurtunityDetails
        OppurtunityDetails updatedOppurtunityDetails = oppurtunityDetailsRepository.findById(oppurtunityDetails.getId()).get();
        // Disconnect from session so that the updates on updatedOppurtunityDetails are not directly saved in db
        em.detach(updatedOppurtunityDetails);
        updatedOppurtunityDetails
            .crmOppurtunityID(UPDATED_CRM_OPPURTUNITY_ID)
            .oppurtunityCreatedDate(UPDATED_OPPURTUNITY_CREATED_DATE)
            .crmCustomerID(UPDATED_CRM_CUSTOMER_ID)
            .bdmName(UPDATED_BDM_NAME)
            .bdmID(UPDATED_BDM_ID)
            .dseID(UPDATED_DSE_ID)
            .dseName(UPDATED_DSE_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountSite(UPDATED_ACCOUNT_SITE)
            .vehicleClass(UPDATED_VEHICLE_CLASS)
            .vehicleVariant(UPDATED_VEHICLE_VARIANT)
            .engineCapacity(UPDATED_ENGINE_CAPACITY)
            .fuelTankCapacity(UPDATED_FUEL_TANK_CAPACITY)
            .wheelBase(UPDATED_WHEEL_BASE)
            .power(UPDATED_POWER)
            .gvwWeight(UPDATED_GVW_WEIGHT)
            .payloadWeight(UPDATED_PAYLOAD_WEIGHT)
            .exShowRoomPrice(UPDATED_EX_SHOW_ROOM_PRICE)
            .onRoadPrice(UPDATED_ON_ROAD_PRICE)
            .lob(UPDATED_LOB)
            .ppl(UPDATED_PPL)
            .pl(UPDATED_PL);

        restOppurtunityDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOppurtunityDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOppurtunityDetails))
            )
            .andExpect(status().isOk());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
        OppurtunityDetails testOppurtunityDetails = oppurtunityDetailsList.get(oppurtunityDetailsList.size() - 1);
        assertThat(testOppurtunityDetails.getCrmOppurtunityID()).isEqualTo(UPDATED_CRM_OPPURTUNITY_ID);
        assertThat(testOppurtunityDetails.getOppurtunityCreatedDate()).isEqualTo(UPDATED_OPPURTUNITY_CREATED_DATE);
        assertThat(testOppurtunityDetails.getCrmCustomerID()).isEqualTo(UPDATED_CRM_CUSTOMER_ID);
        assertThat(testOppurtunityDetails.getBdmName()).isEqualTo(UPDATED_BDM_NAME);
        assertThat(testOppurtunityDetails.getBdmID()).isEqualTo(UPDATED_BDM_ID);
        assertThat(testOppurtunityDetails.getDseID()).isEqualTo(UPDATED_DSE_ID);
        assertThat(testOppurtunityDetails.getDseName()).isEqualTo(UPDATED_DSE_NAME);
        assertThat(testOppurtunityDetails.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testOppurtunityDetails.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testOppurtunityDetails.getAccountSite()).isEqualTo(UPDATED_ACCOUNT_SITE);
        assertThat(testOppurtunityDetails.getVehicleClass()).isEqualTo(UPDATED_VEHICLE_CLASS);
        assertThat(testOppurtunityDetails.getVehicleVariant()).isEqualTo(UPDATED_VEHICLE_VARIANT);
        assertThat(testOppurtunityDetails.getEngineCapacity()).isEqualTo(UPDATED_ENGINE_CAPACITY);
        assertThat(testOppurtunityDetails.getFuelTankCapacity()).isEqualTo(UPDATED_FUEL_TANK_CAPACITY);
        assertThat(testOppurtunityDetails.getWheelBase()).isEqualTo(UPDATED_WHEEL_BASE);
        assertThat(testOppurtunityDetails.getPower()).isEqualTo(UPDATED_POWER);
        assertThat(testOppurtunityDetails.getGvwWeight()).isEqualTo(UPDATED_GVW_WEIGHT);
        assertThat(testOppurtunityDetails.getPayloadWeight()).isEqualTo(UPDATED_PAYLOAD_WEIGHT);
        assertThat(testOppurtunityDetails.getExShowRoomPrice()).isEqualTo(UPDATED_EX_SHOW_ROOM_PRICE);
        assertThat(testOppurtunityDetails.getOnRoadPrice()).isEqualTo(UPDATED_ON_ROAD_PRICE);
        assertThat(testOppurtunityDetails.getLob()).isEqualTo(UPDATED_LOB);
        assertThat(testOppurtunityDetails.getPpl()).isEqualTo(UPDATED_PPL);
        assertThat(testOppurtunityDetails.getPl()).isEqualTo(UPDATED_PL);
    }

    @Test
    @Transactional
    void putNonExistingOppurtunityDetails() throws Exception {
        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();
        oppurtunityDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOppurtunityDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, oppurtunityDetails.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOppurtunityDetails() throws Exception {
        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();
        oppurtunityDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOppurtunityDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOppurtunityDetails() throws Exception {
        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();
        oppurtunityDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOppurtunityDetailsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOppurtunityDetailsWithPatch() throws Exception {
        // Initialize the database
        oppurtunityDetailsRepository.saveAndFlush(oppurtunityDetails);

        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();

        // Update the oppurtunityDetails using partial update
        OppurtunityDetails partialUpdatedOppurtunityDetails = new OppurtunityDetails();
        partialUpdatedOppurtunityDetails.setId(oppurtunityDetails.getId());

        partialUpdatedOppurtunityDetails
            .oppurtunityCreatedDate(UPDATED_OPPURTUNITY_CREATED_DATE)
            .bdmID(UPDATED_BDM_ID)
            .dseID(UPDATED_DSE_ID)
            .dseName(UPDATED_DSE_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountSite(UPDATED_ACCOUNT_SITE)
            .vehicleClass(UPDATED_VEHICLE_CLASS)
            .power(UPDATED_POWER)
            .gvwWeight(UPDATED_GVW_WEIGHT)
            .payloadWeight(UPDATED_PAYLOAD_WEIGHT)
            .exShowRoomPrice(UPDATED_EX_SHOW_ROOM_PRICE)
            .ppl(UPDATED_PPL)
            .pl(UPDATED_PL);

        restOppurtunityDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOppurtunityDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOppurtunityDetails))
            )
            .andExpect(status().isOk());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
        OppurtunityDetails testOppurtunityDetails = oppurtunityDetailsList.get(oppurtunityDetailsList.size() - 1);
        assertThat(testOppurtunityDetails.getCrmOppurtunityID()).isEqualTo(DEFAULT_CRM_OPPURTUNITY_ID);
        assertThat(testOppurtunityDetails.getOppurtunityCreatedDate()).isEqualTo(UPDATED_OPPURTUNITY_CREATED_DATE);
        assertThat(testOppurtunityDetails.getCrmCustomerID()).isEqualTo(DEFAULT_CRM_CUSTOMER_ID);
        assertThat(testOppurtunityDetails.getBdmName()).isEqualTo(DEFAULT_BDM_NAME);
        assertThat(testOppurtunityDetails.getBdmID()).isEqualTo(UPDATED_BDM_ID);
        assertThat(testOppurtunityDetails.getDseID()).isEqualTo(UPDATED_DSE_ID);
        assertThat(testOppurtunityDetails.getDseName()).isEqualTo(UPDATED_DSE_NAME);
        assertThat(testOppurtunityDetails.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testOppurtunityDetails.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testOppurtunityDetails.getAccountSite()).isEqualTo(UPDATED_ACCOUNT_SITE);
        assertThat(testOppurtunityDetails.getVehicleClass()).isEqualTo(UPDATED_VEHICLE_CLASS);
        assertThat(testOppurtunityDetails.getVehicleVariant()).isEqualTo(DEFAULT_VEHICLE_VARIANT);
        assertThat(testOppurtunityDetails.getEngineCapacity()).isEqualTo(DEFAULT_ENGINE_CAPACITY);
        assertThat(testOppurtunityDetails.getFuelTankCapacity()).isEqualTo(DEFAULT_FUEL_TANK_CAPACITY);
        assertThat(testOppurtunityDetails.getWheelBase()).isEqualTo(DEFAULT_WHEEL_BASE);
        assertThat(testOppurtunityDetails.getPower()).isEqualTo(UPDATED_POWER);
        assertThat(testOppurtunityDetails.getGvwWeight()).isEqualTo(UPDATED_GVW_WEIGHT);
        assertThat(testOppurtunityDetails.getPayloadWeight()).isEqualTo(UPDATED_PAYLOAD_WEIGHT);
        assertThat(testOppurtunityDetails.getExShowRoomPrice()).isEqualTo(UPDATED_EX_SHOW_ROOM_PRICE);
        assertThat(testOppurtunityDetails.getOnRoadPrice()).isEqualTo(DEFAULT_ON_ROAD_PRICE);
        assertThat(testOppurtunityDetails.getLob()).isEqualTo(DEFAULT_LOB);
        assertThat(testOppurtunityDetails.getPpl()).isEqualTo(UPDATED_PPL);
        assertThat(testOppurtunityDetails.getPl()).isEqualTo(UPDATED_PL);
    }

    @Test
    @Transactional
    void fullUpdateOppurtunityDetailsWithPatch() throws Exception {
        // Initialize the database
        oppurtunityDetailsRepository.saveAndFlush(oppurtunityDetails);

        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();

        // Update the oppurtunityDetails using partial update
        OppurtunityDetails partialUpdatedOppurtunityDetails = new OppurtunityDetails();
        partialUpdatedOppurtunityDetails.setId(oppurtunityDetails.getId());

        partialUpdatedOppurtunityDetails
            .crmOppurtunityID(UPDATED_CRM_OPPURTUNITY_ID)
            .oppurtunityCreatedDate(UPDATED_OPPURTUNITY_CREATED_DATE)
            .crmCustomerID(UPDATED_CRM_CUSTOMER_ID)
            .bdmName(UPDATED_BDM_NAME)
            .bdmID(UPDATED_BDM_ID)
            .dseID(UPDATED_DSE_ID)
            .dseName(UPDATED_DSE_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountSite(UPDATED_ACCOUNT_SITE)
            .vehicleClass(UPDATED_VEHICLE_CLASS)
            .vehicleVariant(UPDATED_VEHICLE_VARIANT)
            .engineCapacity(UPDATED_ENGINE_CAPACITY)
            .fuelTankCapacity(UPDATED_FUEL_TANK_CAPACITY)
            .wheelBase(UPDATED_WHEEL_BASE)
            .power(UPDATED_POWER)
            .gvwWeight(UPDATED_GVW_WEIGHT)
            .payloadWeight(UPDATED_PAYLOAD_WEIGHT)
            .exShowRoomPrice(UPDATED_EX_SHOW_ROOM_PRICE)
            .onRoadPrice(UPDATED_ON_ROAD_PRICE)
            .lob(UPDATED_LOB)
            .ppl(UPDATED_PPL)
            .pl(UPDATED_PL);

        restOppurtunityDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOppurtunityDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOppurtunityDetails))
            )
            .andExpect(status().isOk());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
        OppurtunityDetails testOppurtunityDetails = oppurtunityDetailsList.get(oppurtunityDetailsList.size() - 1);
        assertThat(testOppurtunityDetails.getCrmOppurtunityID()).isEqualTo(UPDATED_CRM_OPPURTUNITY_ID);
        assertThat(testOppurtunityDetails.getOppurtunityCreatedDate()).isEqualTo(UPDATED_OPPURTUNITY_CREATED_DATE);
        assertThat(testOppurtunityDetails.getCrmCustomerID()).isEqualTo(UPDATED_CRM_CUSTOMER_ID);
        assertThat(testOppurtunityDetails.getBdmName()).isEqualTo(UPDATED_BDM_NAME);
        assertThat(testOppurtunityDetails.getBdmID()).isEqualTo(UPDATED_BDM_ID);
        assertThat(testOppurtunityDetails.getDseID()).isEqualTo(UPDATED_DSE_ID);
        assertThat(testOppurtunityDetails.getDseName()).isEqualTo(UPDATED_DSE_NAME);
        assertThat(testOppurtunityDetails.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testOppurtunityDetails.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testOppurtunityDetails.getAccountSite()).isEqualTo(UPDATED_ACCOUNT_SITE);
        assertThat(testOppurtunityDetails.getVehicleClass()).isEqualTo(UPDATED_VEHICLE_CLASS);
        assertThat(testOppurtunityDetails.getVehicleVariant()).isEqualTo(UPDATED_VEHICLE_VARIANT);
        assertThat(testOppurtunityDetails.getEngineCapacity()).isEqualTo(UPDATED_ENGINE_CAPACITY);
        assertThat(testOppurtunityDetails.getFuelTankCapacity()).isEqualTo(UPDATED_FUEL_TANK_CAPACITY);
        assertThat(testOppurtunityDetails.getWheelBase()).isEqualTo(UPDATED_WHEEL_BASE);
        assertThat(testOppurtunityDetails.getPower()).isEqualTo(UPDATED_POWER);
        assertThat(testOppurtunityDetails.getGvwWeight()).isEqualTo(UPDATED_GVW_WEIGHT);
        assertThat(testOppurtunityDetails.getPayloadWeight()).isEqualTo(UPDATED_PAYLOAD_WEIGHT);
        assertThat(testOppurtunityDetails.getExShowRoomPrice()).isEqualTo(UPDATED_EX_SHOW_ROOM_PRICE);
        assertThat(testOppurtunityDetails.getOnRoadPrice()).isEqualTo(UPDATED_ON_ROAD_PRICE);
        assertThat(testOppurtunityDetails.getLob()).isEqualTo(UPDATED_LOB);
        assertThat(testOppurtunityDetails.getPpl()).isEqualTo(UPDATED_PPL);
        assertThat(testOppurtunityDetails.getPl()).isEqualTo(UPDATED_PL);
    }

    @Test
    @Transactional
    void patchNonExistingOppurtunityDetails() throws Exception {
        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();
        oppurtunityDetails.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOppurtunityDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, oppurtunityDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOppurtunityDetails() throws Exception {
        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();
        oppurtunityDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOppurtunityDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isBadRequest());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOppurtunityDetails() throws Exception {
        int databaseSizeBeforeUpdate = oppurtunityDetailsRepository.findAll().size();
        oppurtunityDetails.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOppurtunityDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oppurtunityDetails))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OppurtunityDetails in the database
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOppurtunityDetails() throws Exception {
        // Initialize the database
        oppurtunityDetailsRepository.saveAndFlush(oppurtunityDetails);

        int databaseSizeBeforeDelete = oppurtunityDetailsRepository.findAll().size();

        // Delete the oppurtunityDetails
        restOppurtunityDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, oppurtunityDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OppurtunityDetails> oppurtunityDetailsList = oppurtunityDetailsRepository.findAll();
        assertThat(oppurtunityDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
