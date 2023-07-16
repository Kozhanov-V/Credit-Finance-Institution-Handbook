package com.kozhanov.creditFinanceInstitutionHandbook.until;

import com.kozhanov.creditFinanceInstitutionHandbook.config.WebSecurityConfig;
import com.kozhanov.creditFinanceInstitutionHandbook.model.handbook.*;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.Confirmation;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.Role;
import com.kozhanov.creditFinanceInstitutionHandbook.model.users.User;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.*;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.RoleReposiroty;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.auth.UserRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.*;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.AccountsRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.BICDirectoryEntryRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.ElectronicDocumentsRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.handbook.ParticipantInfoRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StartupDataLoader implements

        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountOperationRestrictionRepository accountOperationRestrictionRepository;

    @Autowired
    private ImportService importService;

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Autowired
    private AvailableTransferServiceRepository availableTransferServiceRepository;

    @Autowired
    private ChangeTypeRepository changeTypeRepository;

    @Autowired
    private CreationReasonRepository creationReasonRepository;

    @Autowired
    private ExchangeParticipantRepository exchangeParticipantRepository;

    @Autowired
    private InfoTypeCodeRepository infoTypeCodeRepository;

    @Autowired
    private ParticipantRestrictionRepository participantRestrictionRepository;

    @Autowired
    private ParticipantStatusRepository participantStatusRepository;

    @Autowired
    private ParticipantTypeRepository participantTypeRepository;

    @Autowired
    private RegulationAccountTypeRepository regulationAccountTypeRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private BICDirectoryEntryRepository bicDirectoryEntryRepository;

    @Autowired
    private ElectronicDocumentsRepository electronicDocumentsRepository;

    @Autowired
    private ParticipantInfoRepository participantInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleReposiroty roleReposiroty;


    boolean alreadySetup = false;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;


        insertIntoCodeValues();
        insertData();
        alreadySetup = true;
    }

    public void insertIntoCodeValues() {
        System.out.println("ыфвфыв");
        // AccRstr
        accountOperationRestrictionRepository.save(new AccountOperationRestriction("LMRS", "Временное сохранение счета" +
                " с его функционированием в ограниченном режиме", "ED807"));
        accountOperationRestrictionRepository.save(new AccountOperationRestriction("URRS", "Ограничение предоставления" +
                " сервиса срочного перевода", "ED807"));
        accountOperationRestrictionRepository.save(new AccountOperationRestriction("CLRS", "Закрытие счета", "ED807"));
        accountOperationRestrictionRepository.save(new AccountOperationRestriction("FPRS", "Приостановление предоставления" +
                " сервиса быстрых платежей", "ED807"));
        accountOperationRestrictionRepository.save(new AccountOperationRestriction("SDRS", "Признак использования" +
                " реквизитов филиала кредитной организации после его преобразования во внутреннее структурное подразделение (Признак ВСП)", "ED807"));

        //AccountStatus
        accountStatusRepository.save(new AccountStatus("ACAC", "«Действующий», когда текущая дата операционного" +
                " дня позднее или равна дате активации счета и ранее даты удаления счета, или если дата удаления счета не была установлена", "ED807"));
        accountStatusRepository.save(new AccountStatus("ACDL", "«Удаленный», когда текущая дата операционного" +
                " дня равна дате удаления счета", "ED807"));

        //availableTransferService

        availableTransferServiceRepository.save(new AvailableTransferService(1, "Сервис несрочного перевода", "ED807"));
        availableTransferServiceRepository.save(new AvailableTransferService(2, "Сервис срочного перевода", "ED807"));
        availableTransferServiceRepository.save(new AvailableTransferService(3, "Сервис несрочного перевода " +
                "и сервис срочного перевода", "ED807"));
        availableTransferServiceRepository.save(new AvailableTransferService(4, "Сервис срочного перевода" +
                " и сервис быстрых платежей", "ED807"));
        availableTransferServiceRepository.save(new AvailableTransferService(5, "Сервис срочного перевода, сервис несрочного " +
                "перевода и сервис быстрых платежей", "ED807"));
        availableTransferServiceRepository.save(new AvailableTransferService(6, "Сервис быстрых платежей", "ED807"));

        // changeType

        changeTypeRepository.save(new ChangeType("ADDD", "Запись Справочника БИК была добавлена", "ED807"));
        changeTypeRepository.save(new ChangeType("CHGD", "Запись Справочника БИК была изменена по сравнению с предыдущей " +
                "версией Справочника(под изменением понимается любое изменение информации, относящейся к участнику. В том числе: добавление," +
                " удаление, изменение характеристик счета участника)", "ED807"));
        changeTypeRepository.save(new ChangeType("DLTD", "Запись Справочника БИК была удалена", "ED807"));

        // creationReason
        creationReasonRepository.save(new CreationReason("RQST", "Ответ на ЭС-запрос Участника", "ED807"));
        creationReasonRepository.save(new CreationReason("CIBD", "Регламентное направление изменений в Справочнике БИК в " +
                "течение операционного дня", "ED807"));
        creationReasonRepository.save(new CreationReason("FCBD", "1) Регламентное направлениеизменений к Справочнику БИК." +
                "2) Регламентное направление из ПС СБП в ОПКЦ полного Справочника БИК.", "ED807"));

        // exchangeParticipant

        exchangeParticipantRepository.save(new ExchangeParticipant(0, "Не участник обмена", "ED807"));
        exchangeParticipantRepository.save(new ExchangeParticipant(1, "Участник обмена", "ED807"));

        // infoTypeCode
        infoTypeCodeRepository.save(new InfoTypeCode("FIRR", "Полный Справочник БИК", "ED807"));
        infoTypeCodeRepository.save(new InfoTypeCode("SIRR", "Изменения в Справочнике БИК", "ED807"));

        // ParticipantRestriction

        participantRestrictionRepository.save(new ParticipantRestriction("URRS", "Ограничение предоставления сервиса срочного перевода", "ED807"));
        participantRestrictionRepository.save(new ParticipantRestriction("LWRS", "Отзыв (аннулирование) лицензии", "ED807"));
        participantRestrictionRepository.save(new ParticipantRestriction("MRTR", "Мораторий на удовлетворение требований кредиторов", "ED807"));
        participantRestrictionRepository.save(new ParticipantRestriction("RSIP", "Приостановление доступа к услугам по переводу денежных " +
                "средств в качестве косвенного участника", "ED807"));
        participantRestrictionRepository.save(new ParticipantRestriction("FPIP", "Приостановление предоставления сервиса быстрых платежей" +
                " косвенному участнику", "ED807"));

        // ParticipantStatus

        participantStatusRepository.save(new ParticipantStatus("PSAC", "«Действующий», когда текущая дата операционного" +
                " дня позднее или равна дате активации участника и ранее даты удаления Участника, если дата удаления Участника была установлена", "ED807"));
        participantStatusRepository.save(new ParticipantStatus("PSDL", "«Удаленный», если " +
                "текущая дата операционного дня равна дате вступления в силу удаления Участника (направляется только " +
                "в «короткой» версии ED807 содержащей только изменения в Справочнике БИК. Только в день удаления)", "ED807"));

        // PtType
        participantTypeRepository.save(new ParticipantType(00, "Главное управление Банка России", "ED807"));
        participantTypeRepository.save(new ParticipantType(10, "Расчетно-кассовый центр", "ED807"));
        participantTypeRepository.save(new ParticipantType(12, "Отделение, отделение – национальный банк главного управления Банка России", "ED807"));
        participantTypeRepository.save(new ParticipantType(15, "Структурное подразделение центрального аппарата Банка России", "ED807"));
        participantTypeRepository.save(new ParticipantType(16, "Кассовый центр", "ED807"));
        participantTypeRepository.save(new ParticipantType(20, "Кредитная организация", "ED807"));
        participantTypeRepository.save(new ParticipantType(30, "Филиал кредитной организации", "ED807"));
        participantTypeRepository.save(new ParticipantType(40, "Полевое учреждение Банка России", "ED807"));
        participantTypeRepository.save(new ParticipantType(51, "Федеральное казначейство", "ED807"));
        participantTypeRepository.save(new ParticipantType(52, "Территориальный орган Федерального казначейства", "ED807"));
        participantTypeRepository.save(new ParticipantType(60, "Иностранный банк (иностранная кредитная организация)", "ED807"));
        participantTypeRepository.save(new ParticipantType(65, "Иностранный центральный (национальный) банк", "ED807"));
        participantTypeRepository.save(new ParticipantType(71, "Клиент кредитной организации, являющийся косвенным участником", "ED807"));
        participantTypeRepository.save(new ParticipantType(75, "Клиринговая организация", "ED807"));
        participantTypeRepository.save(new ParticipantType(78, "Внешняя платежная система", "ED807"));
        participantTypeRepository.save(new ParticipantType(90, "Конкурсный управляющий (ликвидатор, ликвидационная комиссия)", "ED807"));
        participantTypeRepository.save(new ParticipantType(99, "Клиент Банка России, не являющийся участником платежной системы", "ED807"));

        // RegulationAccountType

        regulationAccountTypeRepository.save(new RegulationAccountType("CBRA", "Счет Банка России", "ED807"));
        regulationAccountTypeRepository.save(new RegulationAccountType("CRSA", "Корреспондентский счет (субсчет)", "ED807"));
        regulationAccountTypeRepository.save(new RegulationAccountType("BANA", "Банковский счет, не являющийся корреспондентским счетом (субсчетом) или единым казначейским счетом", "ED807"));
        regulationAccountTypeRepository.save(new RegulationAccountType("TRSA", "Счет территориального органа Федерального казначейства", "ED807"));
        regulationAccountTypeRepository.save(new RegulationAccountType("TRUA", "Счет доверительного управления", "ED807"));
        regulationAccountTypeRepository.save(new RegulationAccountType("CLAC", "Клиринговый счет", "ED807"));
        regulationAccountTypeRepository.save(new RegulationAccountType("UTRA", "Единый казначейский счет", "ED807"));


    }

    public void insertData() {
        importService.importFromCB();
        Role client = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        User user = new User();
        user.setUsername("admin");
        user.setActive(true);
        user.setPassword(WebSecurityConfig.passwordEncoder().encode("admin"));
        user.addRole(client);
        user.addRole(admin);
        User user2 = new User();
        user2.setUsername("user");
        user2.setActive(false);
        user2.setPassword(WebSecurityConfig.passwordEncoder().encode("user"));
        user2.addRole(client);
        roleReposiroty.save(client);
        roleReposiroty.save(admin);
        userRepository.save(user);
        userRepository.save(user2);
        Confirmation confirmation = new Confirmation();


    }

}
