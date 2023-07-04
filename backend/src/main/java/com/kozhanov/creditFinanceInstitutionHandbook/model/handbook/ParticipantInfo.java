package com.kozhanov.creditFinanceInstitutionHandbook.model.handbook;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.AvailableTransferServiceDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.ExchangeParticipantDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.deserialization.ParticipantTypeDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.*;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

@Entity
@Table(name = "participant_info")
public class ParticipantInfo {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_participant",nullable = false)
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

    @Column(name = "date_in",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateIn;

    @Column(name = "date_out")
    @Temporal(TemporalType.DATE)
    private Date dateOut;

    @ManyToOne
    @JoinColumn(name = "participant_type_code",nullable = false)
    @JsonDeserialize(using = ParticipantTypeDeserializer.class)
    private ParticipantType participantType;

    @ManyToOne
    @JoinColumn(name = "available_transfer_service_code",nullable = false)
    @JsonDeserialize(using = AvailableTransferServiceDeserializer.class)
    private AvailableTransferService availableTransferService;

    @ManyToOne
    @JoinColumn(name = "exchange_participant_code",nullable = false)
    @JsonDeserialize(using = ExchangeParticipantDeserializer.class)
    private ExchangeParticipant exchangeParticipant;

    @Column(name = "uid")
    @Min(1000000000L)
    @Max(9999999999L)
    private long UID;

    @ManyToOne
    @JoinColumn(name = "participant_status_code")
    private ParticipantStatus participantStatus;

    @OneToOne(mappedBy = "participantInfo", cascade = CascadeType.ALL)
    @JsonBackReference
    private BICDirectoryEntry bicDirectoryEntry;


    public ParticipantInfo(String nameParticipant, Date dateIn, ParticipantType participantType, AvailableTransferService availableTransferService, ExchangeParticipant exchangeParticipant) {
        this.nameParticipant = nameParticipant;
        this.dateIn = dateIn;
        this.participantType = participantType;
        this.availableTransferService = availableTransferService;
        this.exchangeParticipant = exchangeParticipant;
    }

    public ParticipantInfo(Integer id, String nameParticipant, String engNameParticipant, String registrationNumber, String countryCode, String regionCode, String index, String typeLocation, String nameLocation, String address, int parentBIC, Date dateIn, Date dateOut, ParticipantType participantType, AvailableTransferService availableTransferService, ExchangeParticipant exchangeParticipant, int UID, ParticipantStatus participantStatus) {
        this.id = id;
        this.nameParticipant = nameParticipant;
        this.engNameParticipant = engNameParticipant;
        this.registrationNumber = registrationNumber;
        this.countryCode = countryCode;
        this.regionCode = regionCode;
        this.index = index;
        this.typeLocation = typeLocation;
        this.nameLocation = nameLocation;
        this.address = address;
        this.parentBIC = parentBIC;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.participantType = participantType;
        this.availableTransferService = availableTransferService;
        this.exchangeParticipant = exchangeParticipant;
        this.UID = UID;
        this.participantStatus = participantStatus;
    }



    public ParticipantInfo() {
    }

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

    public long getUID() {
        return UID;
    }

    public void setUID(long UID) {
        this.UID = UID;
    }

    public ParticipantStatus getParticipantStatus() {
        return participantStatus;
    }

    public void setParticipantStatus(ParticipantStatus participantStatus) {
        this.participantStatus = participantStatus;
    }

    @Override
    public String toString() {
        return "ParticipantInfo{" +
                "id=" + id +
                ", nameParticipant='" + nameParticipant + '\'' +
                ", engNameParticipant='" + engNameParticipant + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", index='" + index + '\'' +
                ", typeLocation='" + typeLocation + '\'' +
                ", nameLocation='" + nameLocation + '\'' +
                ", address='" + address + '\'' +
                ", parentBIC=" + parentBIC +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", participantType=" + participantType +
                ", availableTransferService=" + availableTransferService +
                ", exchangeParticipant=" + exchangeParticipant +
                ", UID=" + UID +
                ", participantStatus=" + participantStatus +
                '}';
    }
    public static class Builder {
        private String nameParticipant;
        private String engNameParticipant;
        private String registrationNumber;
        private String countryCode;
        private String regionCode;
        private String index;
        private String typeLocation;
        private String nameLocation;
        private String address;
        private int parentBIC;
        private Date dateIn;
        private Date dateOut;
        private ParticipantType participantType;
        private AvailableTransferService availableTransferService;
        private ExchangeParticipant exchangeParticipant;
        private long UID;
        private ParticipantStatus participantStatus;

        public Builder nameParticipant(String nameParticipant) {
            this.nameParticipant = nameParticipant;
            return this;
        } public Builder engNameParticipant(String engNameParticipant) {
            this.engNameParticipant = engNameParticipant;
            return this;
        } public Builder registrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        } public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        } public Builder regionCode(String regionCode) {
            this.regionCode = regionCode;
            return this;
        } public Builder index(String index) {
            this.index = index;
            return this;
        } public Builder typeLocation(String typeLocation) {
            this.typeLocation = typeLocation;
            return this;
        } public Builder nameLocation(String nameLocation) {
            this.nameLocation = nameLocation;
            return this;
        } public Builder address(String address) {
            this.address = address;
            return this;
        } public Builder parentBIC(int parentBIC) {
            this.parentBIC = parentBIC;
            return this;
        } public Builder dateIn(Date dateIn) {
            this.dateIn = dateIn;
            return this;
        } public Builder dateOut(Date dateOut) {
            this.dateOut = dateOut;
            return this;
        } public Builder participantType(ParticipantType participantType) {
            this.participantType = participantType;
            return this;
        }public Builder availableTransferService(AvailableTransferService availableTransferService) {
            this.availableTransferService = availableTransferService;
            return this;
        } public Builder exchangeParticipant(ExchangeParticipant exchangeParticipant) {
            this.exchangeParticipant = exchangeParticipant;
            return this;
        } public Builder UID(long UID) {
            this.UID = UID;
            return this;
        } public Builder participantStatus(ParticipantStatus participantStatus) {
            this.participantStatus = participantStatus;
            return this;
        }

        public ParticipantInfo build() {
            ParticipantInfo participantInfo = new ParticipantInfo();
            participantInfo.nameParticipant = this.nameParticipant;
            participantInfo.engNameParticipant = this.engNameParticipant;
            participantInfo.registrationNumber = this.registrationNumber;
            participantInfo.countryCode = this.countryCode;
            participantInfo.regionCode = this.regionCode;
            participantInfo.index = this.index;
            participantInfo.typeLocation = this.typeLocation;
            participantInfo.nameLocation = this.nameLocation;
            participantInfo.address = this.address;
            participantInfo.parentBIC = this.parentBIC;
            participantInfo.dateIn = this.dateIn;
            participantInfo.dateOut = this.dateOut;
            participantInfo.participantType = this.participantType;
            participantInfo.availableTransferService = this.availableTransferService;
            participantInfo.exchangeParticipant = this.exchangeParticipant;
            participantInfo.UID = this.UID;
            participantInfo.participantStatus = this.participantStatus;

            return participantInfo;
        }

    }
}

