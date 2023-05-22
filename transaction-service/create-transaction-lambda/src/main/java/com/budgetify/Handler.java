package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
import com.budgetify.dto.TransactionCreateDto;
import com.budgetify.dto.TransactionResponseDto;
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
            TransactionCreateDto transactionCreateDto = gson.fromJson(request.getBody(), TransactionCreateDto.class);

            RequestValidator.validate(transactionCreateDto);

            int userId = initializer.getSecurityService().validateRequest(request);
            initializer.getAuthorityService().validateResourceAccess(userId, transactionCreateDto.getAccountId(), Resource.ACCOUNT);

            TransactionResponseDto transactionResponseDto = initializer.getTransactionService().createTransaction(transactionCreateDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(transactionResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}