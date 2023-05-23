package com.budgetify.service;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.budgetify.exception.ApiException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestValidator {
    public static int getAccountIdFromQuery(APIGatewayProxyRequestEvent request) {
        Map<String, String> queryStringParameters = request.getQueryStringParameters();

        if (queryStringParameters == null) {
            throw new ApiException("accountId is required in query parameters.");
        }

        String accountId = queryStringParameters.get("accountId");

        if (accountId == null) {
            throw new ApiException("accountId is required in query parameters.");
        }

        return Integer.parseInt(accountId);
    }
}
