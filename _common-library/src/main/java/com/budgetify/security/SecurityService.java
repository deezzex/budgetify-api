package com.budgetify.security;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.budgetify.entity.Session;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class SecurityService {
    private final SessionDao sessionDao;

    public void validateRequest(APIGatewayProxyRequestEvent request) {
        validateSession(request);
    }

    private void validateSession(APIGatewayProxyRequestEvent request) {
        String sessionHeader = request.getHeaders().get("session");

        if (sessionHeader == null) {
            throw new ApiException("Session is not set.");
        }

        Session session = sessionDao.findById(sessionHeader);

        if (!session.getStatus().equals("A") || session.getClosedAt() != null) {
            throw new ApiException("Session is inactive.");
        }

        LocalDateTime now = LocalDateTime.now();

        if (session.getUpdatedAt().plusMinutes(5).isBefore(now)) {
            session.setClosedAt(now);
            session.setStatus("I");
            sessionDao.update(session);
            throw new ApiException("Session is expired.");
        }

        session.setUpdatedAt(now);
        sessionDao.update(session);
    }
}
