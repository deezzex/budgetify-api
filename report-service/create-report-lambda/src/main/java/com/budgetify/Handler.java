package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.constant.Resource;
import com.budgetify.dto.ReportCreateDto;
import com.budgetify.dto.ReportResponseDto;
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
            ReportCreateDto reportCreateDto = gson.fromJson(request.getBody(), ReportCreateDto.class);

            RequestValidator.validate(reportCreateDto);

            int userId = initializer.getSecurityService().validateRequest(request);
            initializer.getAuthorityService().validateResourceAccess(userId, reportCreateDto.getBudgetId(), Resource.BUDGET);

            ReportResponseDto reportResponseDto = initializer.getReportService().createReport(reportCreateDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(reportResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}