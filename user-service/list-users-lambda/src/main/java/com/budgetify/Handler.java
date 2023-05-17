package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dto.UserResponseDto;
import com.budgetify.security.AuthorityService;
import com.budgetify.security.SecurityService;
import com.budgetify.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final DataSource dataSource;
    private static final BaseUserDao userDao;
    private static final UserService userService;
    private static final BaseSessionDao sessionDao;
    private static final SecurityService securityService;
    private static final AuthorityService authorityService;

    static {
        dataSource = DataSourceConfig.getDataSource();
        userDao = new BaseUserDao(dataSource);
        userService = new UserService(userDao);
        sessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(sessionDao);
        authorityService = new AuthorityService(userDao);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            int userId = securityService.validateRequest(request);
            authorityService.validateAdminAccess(userId);

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
