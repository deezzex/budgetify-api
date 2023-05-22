package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
import com.budgetify.dto.BudgetCreateDto;
import com.budgetify.dto.BudgetResponseDto;
import com.budgetify.service.RequestValidator;
import com.budgetify.util.Initializer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Initializer initializer = Initializer.getInstance();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        Gson gson = new Gson();

        try {
            BudgetCreateDto budgetCreateDto = gson.fromJson(request.getBody(), BudgetCreateDto.class);

            RequestValidator.validate(budgetCreateDto);

            int userId = initializer.getSecurityService().validateRequest(request);
            initializer.getAuthorityService().validateResourceAccess(userId, budgetCreateDto.getAccountId(), Resource.ACCOUNT);

            BudgetResponseDto budgetResponseDto = initializer.getBudgetService().createBudget(budgetCreateDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(budgetResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}
