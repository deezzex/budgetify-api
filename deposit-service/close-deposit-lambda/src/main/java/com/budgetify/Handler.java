package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
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
            int userId = initializer.getSecurityService().validateRequest(request);

            Integer depositId = Integer.valueOf(request.getPathParameters().get("id"));

            initializer.getAuthorityService().validateResourceAccess(userId, depositId, Resource.DEPOSIT);

            initializer.getDepositService().closeDeposit(depositId);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson("Deposit has been successfully closed."))
                    .withStatusCode(200);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}