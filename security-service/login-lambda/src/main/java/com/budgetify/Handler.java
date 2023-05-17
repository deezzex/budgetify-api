package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.SessionDao;
import com.budgetify.dao.UserDao;
import com.budgetify.dto.LoginDto;
import com.budgetify.dto.LoginResponseDto;
import com.budgetify.service.SessionService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final DataSource dataSource;
    private static final UserDao userDao;
    private static final SessionDao sessionDao;
    private static final SessionService sessionService;

    static {
        dataSource = DataSourceConfig.getDataSource();
        userDao = new UserDao(dataSource);
        sessionDao = new SessionDao(dataSource);
        sessionService = new SessionService(userDao, sessionDao);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            LoginDto loginDto = gson.fromJson(request.getBody(), LoginDto.class);

            LoginResponseDto loginResponseDto = sessionService.createSession(loginDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(loginResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}
