package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.budgetify.dto.TransactionDto;
import com.budgetify.util.Initializer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler implements RequestHandler<SQSEvent, APIGatewayProxyResponseEvent> {

    private static final Initializer initializer = Initializer.getInstance();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(SQSEvent request, Context context) {
        Gson gson = new Gson();

        try {
            SQSEvent.SQSMessage message = request.getRecords().get(0);
            TransactionDto transactionDto = gson.fromJson(message.getBody(), TransactionDto.class);

            initializer.getBudgetService().updateBudgetSpent(transactionDto);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}