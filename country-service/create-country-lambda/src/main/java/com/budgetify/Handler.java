package com.budgetify;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.budgetify.dto.CountryCreateDto;
import com.budgetify.dto.CountryResponseDto;
import com.budgetify.exception.ApiException;
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
            CountryCreateDto countryCreateDto = gson.fromJson(request.getBody(), CountryCreateDto.class);

            RequestValidator.validate(countryCreateDto);

            int userId = initializer.getSecurityService().validateRequest(request);
            boolean isAdmin = initializer.getAuthorityService().validateAdminAccess(userId);

            if (!isAdmin){
                throw new ApiException("Resource requires administrator access.");
            }

            CountryResponseDto countryResponseDto = initializer.getCountryService().createCountry(countryCreateDto);

            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(countryResponseDto))
                    .withStatusCode(201);
        } catch (Exception exception) {
            return new APIGatewayProxyResponseEvent()
                    .withBody(gson.toJson(exception.getMessage()))
                    .withStatusCode(500);
        }
    }
}