package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
import com.budgetify.dto.BudgetCategoryCreateDto;
import com.budgetify.dto.BudgetCategoryResponseDto;
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
            BudgetCategoryCreateDto budgetCategoryCreateDto = gson.fromJson(request.getBody(), BudgetCategoryCreateDto.class);

            RequestValidator.validate(budgetCategoryCreateDto);

            int userId = initializer.getSecurityService().validateRequest(request);
            initializer.getAuthorityService().validateResourceAccess(userId, budgetCategoryCreateDto.getBudgetId(), Resource.BUDGET);

            BudgetCategoryResponseDto budgetCategoryResponseDto = initializer.getBudgetCategoryService().createBudgetCategory(budgetCategoryCreateDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(budgetCategoryResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}
