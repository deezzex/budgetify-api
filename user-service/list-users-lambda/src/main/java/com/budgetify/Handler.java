package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.UserDao;
import com.budgetify.dto.UserResponseDto;
import com.budgetify.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final DataSource dataSource;
    private static final UserDao userDao;
    private static final UserService userService;

    static {
        dataSource = DataSourceConfig.getDataSource();
        userDao = new UserDao(dataSource);
        userService = new UserService(userDao);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            List<UserResponseDto> responseDtoList = userService.getAllUsers();

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(responseDtoList))
                    .withStatusCode(200);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}
