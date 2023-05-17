package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.SessionDao;
import com.budgetify.dto.LogoutDto;
import com.budgetify.service.SessionService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final DataSource dataSource;
    private static final SessionDao sessionDao;
    private static final SessionService sessionService;

    static {
        dataSource = DataSourceConfig.getDataSource();
        sessionDao = new SessionDao(dataSource);
        sessionService = new SessionService(sessionDao);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            LogoutDto logoutDto = gson.fromJson(request.getBody(), LogoutDto.class);

            sessionService.inactivateSession(logoutDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson("Logged out."))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}
