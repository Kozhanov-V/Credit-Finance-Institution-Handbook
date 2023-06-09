package com.kozhanov.creditFinanceInstitutionHandbook.model.xml;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import javax.validation.constraints.*;

@Entity
@Table(name = "participant_info")
public class ParticipantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name_participant")
    @Size(max = 160)
    private String nameParticipant;

    @Column(name = "eng_name_participant")
    @Size(max = 140)
    private String engNameParticipant;

    @Column(name = "registration_number")
    @Size(max = 9)
    private String registrationNumber;

    @Column(name = "country_code")
    @Size(min = 2,max = 2)
    private String countryCode;

    @Column(name = "region_code")
    @Size(max = 2)
    private String regionCode;

    @Column(name = "index")
    @Size(max = 6)
    private String index;

    @Column(name = "type_location")
    @Size(max = 5)
    private String typeLocation;

    @Column(name = "name_location")
    @Size(max = 25)
    private String nameLocation;

    @Column(name = "address")
    @Size(max = 160)
    private String address;

    @Column(name = "parent_BIC")
    @Min(100000000)
    @Max(999999999)
    private int parentBIC;

    @Column(name = "date_in")
    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @Column(name = "date_out")
    @Temporal(TemporalType.DATE)
    private Date dateOut;

    @ManyToOne
    @JoinColumn(name = "participant_type_code")
    private ParticipantType participantType;

    @ManyToOne
    @JoinColumn(name = "available_transfer_service_code")
    private AvailableTransferService availableTransferService;

    @ManyToOne
    @JoinColumn(name = "exchange_participant_code")
    private ExchangeParticipant exchangeParticipant;

    @Column(name = "uid")
    @Min(1000000000L)
    @Max(9999999999L)
    private int UID;

    @ManyToOne
    @JoinColumn(name = "participant_status_code")
    private ParticipantStatus participantStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameParticipant() {
        return nameParticipant;
    }

    public void setNameParticipant(String nameParticipant) {
        this.nameParticipant = nameParticipant;
    }

    public String getEngNameParticipant() {
        return engNameParticipant;
    }

    public void setEngNameParticipant(String engNameParticipant) {
        this.engNameParticipant = engNameParticipant;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTypeLocation() {
        return typeLocation;
    }

    public void setTypeLocation(String typeLocation) {
        this.typeLocation = typeLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getParentBIC() {
        return parentBIC;
    }

    public void setParentBIC(int parentBIC) {
        this.parentBIC = parentBIC;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public AvailableTransferService getAvailableTransferService() {
        return availableTransferService;
    }

    public void setAvailableTransferService(AvailableTransferService availableTransferService) {
        this.availableTransferService = availableTransferService;
    }

    public ExchangeParticipant getExchangeParticipant() {
        return exchangeParticipant;
    }

    public void setExchangeParticipant(ExchangeParticipant exchangeParticipant) {
        this.exchangeParticipant = exchangeParticipant;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public ParticipantStatus getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(ParticipantStatus participantStatus) {
        this.participantStatus = participantStatus;
    }
}

