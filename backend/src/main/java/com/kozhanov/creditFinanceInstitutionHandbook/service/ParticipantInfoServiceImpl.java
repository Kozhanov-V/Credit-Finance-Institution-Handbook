package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.ParticipantInfo;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.ParticipantStatusRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.ParticipantInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantInfoServiceImpl implements ParticipantInfoService{

    @Autowired
    ParticipantInfoRepository participantInfoRepository;

    @Autowired
    ParticipantStatusRepository participantStatusRepository;
    @Override
    public void save(ParticipantInfo participantInfo) {
        try {
            if (participantInfo.getDateIn().getTime() < System.currentTimeMillis() && participantInfo.getDateOut().getTime() > System.currentTimeMillis()) {
                participantInfo.setParticipantStatus(participantStatusRepository.findByCode("PSAC").get());
            } else {
                participantInfo.setParticipantStatus(participantStatusRepository.findByCode("PSDL").get());
            }
        }catch (NullPointerException nullPointerException){
            if (participantInfo.getDateIn().getTime() < System.currentTimeMillis()) {
                participantInfo.setParticipantStatus(participantStatusRepository.findByCode("PSAC").get());
            } else {
                participantInfo.setParticipantStatus(participantStatusRepository.findByCode("PSDL").get());
            }
        }
        participantInfoRepository.save(participantInfo);
    }
}
