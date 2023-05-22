package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
import com.budgetify.dto.BudgetResponseDto;
import com.budgetify.security.AuthorityService;
import com.budgetify.service.BudgetService;
import com.budgetify.service.RequestValidator;
import com.budgetify.util.Initializer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Initializer initializer = Initializer.getInstance();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            AuthorityService authorityService = initializer.getAuthorityService();
            BudgetService budgetService = initializer.getBudgetService();

            int userId = initializer.getSecurityService().validateRequest(request);
            boolean isAdmin = authorityService.validateAdminAccess(userId);

            List<BudgetResponseDto> responseDtoList;

            if (!isAdmin) {
                int accountId = RequestValidator.getAccountIdFromQuery(request);
                authorityService.validateResourceAccess(userId, accountId, Resource.ACCOUNT);
                responseDtoList = budgetService.getAllBudgetsByAccountId(accountId);
            } else {
                responseDtoList = budgetService.getAllBudgets();
            }

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(responseDtoList))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}