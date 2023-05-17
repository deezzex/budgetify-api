package com.budgetify.service;

import com.budgetify.dao.SessionDao;
import com.budgetify.dto.LogoutDto;
import com.budgetify.entity.Session;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SessionService {
    private final SessionDao sessionDao;

    public void inactivateSession(LogoutDto logoutDto) {
        RequestValidator.validate(logoutDto);

        Session session = sessionDao.findById(logoutDto.getSession());

        if (!session.getStatus().equals("A") || session.getClosedAt() != null) {
            throw new ApiException("Session is already inactive.");
        }

        session.setClosedAt(LocalDateTime.now());
        session.setStatus("I");

        sessionDao.update(session);
    }
}
