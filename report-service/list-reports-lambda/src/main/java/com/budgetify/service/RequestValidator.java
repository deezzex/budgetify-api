package com.budgetify.service;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.budgetify.exception.ApiException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestValidator {
    public static int getBudgetIdFromParameters(APIGatewayProxyRequestEvent request) {
        Map<String, String> queryStringParameters = request.getQueryStringParameters();

        if (queryStringParameters == null) {
            throw new ApiException("budgetId is required in query parameters.");
        }

        String budgetId = queryStringParameters.get("budgetId");

        if (budgetId == null) {
            throw new ApiException("budgetId is required in query parameters.");
        }

        return Integer.parseInt(budgetId);
    }
}
