package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.config.DataSourceConfig;
import com.budgetify.dao.BaseAccountDao;
import com.budgetify.dao.BaseCurrencyDao;
import com.budgetify.dao.BaseSessionDao;
import com.budgetify.dao.BaseUserDao;
import com.budgetify.dto.AccountResponseDto;
import com.budgetify.security.SecurityService;
import com.budgetify.service.AccountService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final DataSource dataSource;
    private static final BaseSessionDao baseSessionDao;
    private static final SecurityService securityService;

    private static final BaseAccountDao accountDao;
    private static final BaseUserDao baseUserDao;
    private static final BaseCurrencyDao currencyDao;
    private static final AccountService accountService;

    static {
        dataSource = DataSourceConfig.getDataSource();
        baseSessionDao = new BaseSessionDao(dataSource);
        securityService = new SecurityService(baseSessionDao);

        accountDao = new BaseAccountDao(dataSource);
        baseUserDao = new BaseUserDao(dataSource);
        currencyDao = new BaseCurrencyDao(dataSource);

        accountService = new AccountService(accountDao, baseUserDao, currencyDao);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            securityService.validateRequest(request);

            String id = request.getPathParameters().get("id");

            AccountResponseDto accountResponseDto = accountService.getAccount(Integer.valueOf(id));

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(accountResponseDto))
                    .withStatusCode(200);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}