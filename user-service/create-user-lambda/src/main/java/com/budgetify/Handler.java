package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseCountryDao;
import com.budgetify.dao.UserDao;
import com.budgetify.dto.UserResponseDto;
import com.budgetify.dto.UserSaveDto;
import com.budgetify.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final DataSource dataSource;
    private static final UserDao userDao;
    private static final BaseCountryDao countryDao;
    private static final UserService userService;

    static {
        dataSource = DataSourceConfig.getDataSource();
        userDao = new UserDao(dataSource);
        countryDao = new BaseCountryDao(dataSource);
        userService = new UserService(userDao, countryDao);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            UserSaveDto userSaveDto = gson.fromJson(request.getBody(), UserSaveDto.class);

            UserResponseDto userResponseDto = userService.createUser(userSaveDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(userResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}
