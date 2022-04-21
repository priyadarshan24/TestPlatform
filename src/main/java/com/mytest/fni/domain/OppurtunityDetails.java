package com.mytest.fni.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A OppurtunityDetails.
 */
@Entity
@Table(name = "oppurtunity_details")
public class OppurtunityDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "crm_oppurtunity_id")
    private String crmOppurtunityID;

    @Column(name = "oppurtunity_created_date")
    private LocalDate oppurtunityCreatedDate;

    @Column(name = "crm_customer_id")
    private Long crmCustomerID;

    @Column(name = "bdm_name")
    private String bdmName;

    @Column(name = "bdm_id")
    private String bdmID;

    @Column(name = "dse_id")
    private String dseID;

    @Column(name = "dse_name")
    private String dseName;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "account_site")
    private String accountSite;

    @Column(name = "vehicle_class")
    private String vehicleClass;

    @Column(name = "vehicle_variant")
    private String vehicleVariant;

    @Column(name = "engine_capacity")
    private String engineCapacity;

    @Column(name = "fuel_tank_capacity")
    private String fuelTankCapacity;

    @Column(name = "wheel_base")
    private String wheelBase;

    @Column(name = "power")
    private String power;

    @Column(name = "gvw_weight")
    private String gvwWeight;

    @Column(name = "payload_weight")
    private String payloadWeight;

    @Column(name = "ex_show_room_price")
    private Long exShowRoomPrice;

    @Column(name = "on_road_price")
    private Long onRoadPrice;

    @Column(name = "lob")
    private String lob;

    @Column(name = "ppl")
    private String ppl;

    @Column(name = "pl")
    private String pl;

    @OneToOne
    @JoinColumn(unique = true)
    private FniTOFinancierSyncStatus financierSyncStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OppurtunityDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrmOppurtunityID() {
        return this.crmOppurtunityID;
    }

    public OppurtunityDetails crmOppurtunityID(String crmOppurtunityID) {
        this.setCrmOppurtunityID(crmOppurtunityID);
        return this;
    }

    public void setCrmOppurtunityID(String crmOppurtunityID) {
        this.crmOppurtunityID = crmOppurtunityID;
    }

    public LocalDate getOppurtunityCreatedDate() {
        return this.oppurtunityCreatedDate;
    }

    public OppurtunityDetails oppurtunityCreatedDate(LocalDate oppurtunityCreatedDate) {
        this.setOppurtunityCreatedDate(oppurtunityCreatedDate);
        return this;
    }

    public void setOppurtunityCreatedDate(LocalDate oppurtunityCreatedDate) {
        this.oppurtunityCreatedDate = oppurtunityCreatedDate;
    }

    public Long getCrmCustomerID() {
        return this.crmCustomerID;
    }

    public OppurtunityDetails crmCustomerID(Long crmCustomerID) {
        this.setCrmCustomerID(crmCustomerID);
        return this;
    }

    public void setCrmCustomerID(Long crmCustomerID) {
        this.crmCustomerID = crmCustomerID;
    }

    public String getBdmName() {
        return this.bdmName;
    }

    public OppurtunityDetails bdmName(String bdmName) {
        this.setBdmName(bdmName);
        return this;
    }

    public void setBdmName(String bdmName) {
        this.bdmName = bdmName;
    }

    public String getBdmID() {
        return this.bdmID;
    }

    public OppurtunityDetails bdmID(String bdmID) {
        this.setBdmID(bdmID);
        return this;
    }

    public void setBdmID(String bdmID) {
        this.bdmID = bdmID;
    }

    public String getDseID() {
        return this.dseID;
    }

    public OppurtunityDetails dseID(String dseID) {
        this.setDseID(dseID);
        return this;
    }

    public void setDseID(String dseID) {
        this.dseID = dseID;
    }

    public String getDseName() {
        return this.dseName;
    }

    public OppurtunityDetails dseName(String dseName) {
        this.setDseName(dseName);
        return this;
    }

    public void setDseName(String dseName) {
        this.dseName = dseName;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public OppurtunityDetails accountType(String accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public OppurtunityDetails accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountSite() {
        return this.accountSite;
    }

    public OppurtunityDetails accountSite(String accountSite) {
        this.setAccountSite(accountSite);
        return this;
    }

    public void setAccountSite(String accountSite) {
        this.accountSite = accountSite;
    }

    public String getVehicleClass() {
        return this.vehicleClass;
    }

    public OppurtunityDetails vehicleClass(String vehicleClass) {
        this.setVehicleClass(vehicleClass);
        return this;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getVehicleVariant() {
        return this.vehicleVariant;
    }

    public OppurtunityDetails vehicleVariant(String vehicleVariant) {
        this.setVehicleVariant(vehicleVariant);
        return this;
    }

    public void setVehicleVariant(String vehicleVariant) {
        this.vehicleVariant = vehicleVariant;
    }

    public String getEngineCapacity() {
        return this.engineCapacity;
    }

    public OppurtunityDetails engineCapacity(String engineCapacity) {
        this.setEngineCapacity(engineCapacity);
        return this;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getFuelTankCapacity() {
        return this.fuelTankCapacity;
    }

    public OppurtunityDetails fuelTankCapacity(String fuelTankCapacity) {
        this.setFuelTankCapacity(fuelTankCapacity);
        return this;
    }

    public void setFuelTankCapacity(String fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getWheelBase() {
        return this.wheelBase;
    }

    public OppurtunityDetails wheelBase(String wheelBase) {
        this.setWheelBase(wheelBase);
        return this;
    }

    public void setWheelBase(String wheelBase) {
        this.wheelBase = wheelBase;
    }

    public String getPower() {
        return this.power;
    }

    public OppurtunityDetails power(String power) {
        this.setPower(power);
        return this;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getGvwWeight() {
        return this.gvwWeight;
    }

    public OppurtunityDetails gvwWeight(String gvwWeight) {
        this.setGvwWeight(gvwWeight);
        return this;
    }

    public void setGvwWeight(String gvwWeight) {
        this.gvwWeight = gvwWeight;
    }

    public String getPayloadWeight() {
        return this.payloadWeight;
    }

    public OppurtunityDetails payloadWeight(String payloadWeight) {
        this.setPayloadWeight(payloadWeight);
        return this;
    }

    public void setPayloadWeight(String payloadWeight) {
        this.payloadWeight = payloadWeight;
    }

    public Long getExShowRoomPrice() {
        return this.exShowRoomPrice;
    }

    public OppurtunityDetails exShowRoomPrice(Long exShowRoomPrice) {
        this.setExShowRoomPrice(exShowRoomPrice);
        return this;
    }

    public void setExShowRoomPrice(Long exShowRoomPrice) {
        this.exShowRoomPrice = exShowRoomPrice;
    }

    public Long getOnRoadPrice() {
        return this.onRoadPrice;
    }

    public OppurtunityDetails onRoadPrice(Long onRoadPrice) {
        this.setOnRoadPrice(onRoadPrice);
        return this;
    }

    public void setOnRoadPrice(Long onRoadPrice) {
        this.onRoadPrice = onRoadPrice;
    }

    public String getLob() {
        return this.lob;
    }

    public OppurtunityDetails lob(String lob) {
        this.setLob(lob);
        return this;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getPpl() {
        return this.ppl;
    }

    public OppurtunityDetails ppl(String ppl) {
        this.setPpl(ppl);
        return this;
    }

    public void setPpl(String ppl) {
        this.ppl = ppl;
    }

    public String getPl() {
        return this.pl;
    }

    public OppurtunityDetails pl(String pl) {
        this.setPl(pl);
        return this;
    }

    public void setPl(String pl) {
        this.pl = pl;
    }

    public FniTOFinancierSyncStatus getFinancierSyncStatus() {
        return this.financierSyncStatus;
    }

    public void setFinancierSyncStatus(FniTOFinancierSyncStatus fniTOFinancierSyncStatus) {
        this.financierSyncStatus = fniTOFinancierSyncStatus;
    }

    public OppurtunityDetails financierSyncStatus(FniTOFinancierSyncStatus fniTOFinancierSyncStatus) {
        this.setFinancierSyncStatus(fniTOFinancierSyncStatus);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OppurtunityDetails)) {
            return false;
        }
        return id != null && id.equals(((OppurtunityDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OppurtunityDetails{" +
            "id=" + getId() +
            ", crmOppurtunityID='" + getCrmOppurtunityID() + "'" +
            ", oppurtunityCreatedDate='" + getOppurtunityCreatedDate() + "'" +
            ", crmCustomerID=" + getCrmCustomerID() +
            ", bdmName='" + getBdmName() + "'" +
            ", bdmID='" + getBdmID() + "'" +
            ", dseID='" + getDseID() + "'" +
            ", dseName='" + getDseName() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", accountSite='" + getAccountSite() + "'" +
            ", vehicleClass='" + getVehicleClass() + "'" +
            ", vehicleVariant='" + getVehicleVariant() + "'" +
            ", engineCapacity='" + getEngineCapacity() + "'" +
            ", fuelTankCapacity='" + getFuelTankCapacity() + "'" +
            ", wheelBase='" + getWheelBase() + "'" +
            ", power='" + getPower() + "'" +
            ", gvwWeight='" + getGvwWeight() + "'" +
            ", payloadWeight='" + getPayloadWeight() + "'" +
            ", exShowRoomPrice=" + getExShowRoomPrice() +
            ", onRoadPrice=" + getOnRoadPrice() +
            ", lob='" + getLob() + "'" +
            ", ppl='" + getPpl() + "'" +
            ", pl='" + getPl() + "'" +
            "}";
    }
}
