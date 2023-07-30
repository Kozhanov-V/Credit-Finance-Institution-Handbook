package com.kozhanov.creditFinanceInstitutionHandbook.service;

import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.*;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RepositoryService {

    @Resource
    private CreationReasonRepository creationReasonRepository;

    @Resource
    private AccountOperationRestrictionRepository accountOperationRestrictionRepository;

    @Resource
    private AccountStatusRepository accountStatusRepository;

    @Resource
    private AvailableTransferServiceRepository availableTransferServiceRepository;

    @Resource
    private ChangeTypeRepository changeTypeRepository;

    @Resource
    private ExchangeParticipantRepository exchangeParticipantRepository;

    @Resource
    private InfoTypeCodeRepository infoTypeCodeRepository;

    @Resource
    private ParticipantRestrictionRepository participantRestrictionRepository;

    @Resource
    private ParticipantStatusRepository participantStatusRepository;

    @Resource
    private ParticipantTypeRepository participantTypeRepository;

    @Resource
    private RegulationAccountTypeRepository regulationAccountTypeRepository;

    @Autowired
    private UserRepository userRepository;


    @Getter
    private static final Map<Class<?>, JpaRepository<?, ?>> repos = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        repos.put(AccountOperationRestriction.class, accountOperationRestrictionRepository);
        repos.put(AccountStatus.class, accountStatusRepository);
        repos.put(AvailableTransferService.class, availableTransferServiceRepository);
        repos.put(ChangeType.class, changeTypeRepository);
        repos.put(CreationReason.class, creationReasonRepository);
        repos.put(ExchangeParticipant.class, exchangeParticipantRepository);
        repos.put(InfoTypeCode.class, infoTypeCodeRepository);
        repos.put(ParticipantRestriction.class, participantRestrictionRepository);
        repos.put(ParticipantStatus.class, participantStatusRepository);
        repos.put(ParticipantType.class, participantTypeRepository);
        repos.put(RegulationAccountType.class, regulationAccountTypeRepository);
        repos.put(RegulationAccountType.class, regulationAccountTypeRepository);
        repos.put(UserRepository.class, userRepository);
    }


}
