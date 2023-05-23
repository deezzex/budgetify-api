package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
import com.budgetify.dto.DepositCreateDto;
import com.budgetify.dto.DepositResponseDto;
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
            DepositCreateDto depositCreateDto = gson.fromJson(request.getBody(), DepositCreateDto.class);

            RequestValidator.validate(depositCreateDto);

            int userId = initializer.getSecurityService().validateRequest(request);
            initializer.getAuthorityService().validateResourceAccess(userId, depositCreateDto.getAccountId(), Resource.ACCOUNT);

            DepositResponseDto depositResponseDto = initializer.getDepositService().createDeposit(depositCreateDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(depositResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}