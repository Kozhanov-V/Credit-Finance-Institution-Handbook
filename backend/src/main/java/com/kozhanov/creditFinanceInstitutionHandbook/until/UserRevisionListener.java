package com.kozhanov.creditFinanceInstitutionHandbook.until;

import com.kozhanov.creditFinanceInstitutionHandbook.model.users.UserRevisionEntity;
import com.kozhanov.creditFinanceInstitutionHandbook.service.CurrentUserService;
import org.hibernate.envers.RevisionListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UserRevisionListener implements RevisionListener {

    private final CurrentUserService currentUserService;

    public UserRevisionListener(@Lazy CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @Override
    public void newRevision(Object revisionEntity) {
        UserRevisionEntity userRevEntity = (UserRevisionEntity) revisionEntity;
      //  userRevEntity.setUsername(currentUserService.getCurrentUser().get().getUsername());
    }
}

