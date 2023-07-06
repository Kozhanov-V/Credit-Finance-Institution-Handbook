package com.kozhanov.creditFinanceInstitutionHandbook.deserialization.codeValue;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.kozhanov.creditFinanceInstitutionHandbook.model.codeValue.AccountOperationRestriction;
import com.kozhanov.creditFinanceInstitutionHandbook.repository.codeValue.AccountOperationRestrictionRepository;
import com.kozhanov.creditFinanceInstitutionHandbook.service.RepositoryService;

import java.io.IOException;

public class AccountOperationRestrictionDeserializer extends JsonDeserializer<AccountOperationRestriction> {


    private AccountOperationRestrictionRepository accountOperationRestrictionRepository= (AccountOperationRestrictionRepository) RepositoryService.getRepos().get(AccountOperationRestriction.class);

    @Override
    public AccountOperationRestriction deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String code = jsonParser.getValueAsString();
       AccountOperationRestriction accountOperationRestriction= accountOperationRestrictionRepository.findById(code).get();
        return accountOperationRestriction;
    }
}
