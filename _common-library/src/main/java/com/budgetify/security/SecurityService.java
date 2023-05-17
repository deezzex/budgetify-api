package com.budgetify.security;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.entity.Session;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SecurityService {
    private final BaseSessionDao baseSessionDao;

    public int validateRequest(APIGatewayProxyRequestEvent request) {
        return validateSession(request);
    }

    private int validateSession(APIGatewayProxyRequestEvent request) {
        String sessionHeader = request.getHeaders().get("session");

        if (sessionHeader == null) {
            throw new ApiException("Session is not set.");
        }

        Session session = baseSessionDao.findById(sessionHeader);

        if (!session.getStatus().equals("A") || session.getClosedAt() != null) {
            throw new ApiException("Session is inactive.");
        }

        LocalDateTime now = LocalDateTime.now();

        if (session.getUpdatedAt().plusMinutes(5).isBefore(now)) {
            session.setClosedAt(now);
            session.setStatus("I");
            baseSessionDao.update(session);
            throw new ApiException("Session is expired.");
        }

        session.setUpdatedAt(now);
        baseSessionDao.update(session);

        return session.getUserId();
    }
}
