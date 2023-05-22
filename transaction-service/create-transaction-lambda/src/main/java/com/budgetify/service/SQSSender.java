package com.budgetify.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.budgetify.entity.Transaction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SQSSender {
    private static final String AWS_REGION = System.getenv("AWS_REGION");
    private static final String ACCOUNT_ID = "262391914540";
    private static final String QUEUE_NAME = String.format("https://sqs.%s.amazonaws.com/%s/test", AWS_REGION, ACCOUNT_ID);

    public static void sendMessage(Transaction transaction) {
        String message = buildMessage(transaction);
        sendMessage(message);
    }

    private static void sendMessage(String msg) {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .build();

        log.info("Sending message: {} to the Queue: {}", msg, SQSSender.QUEUE_NAME);

        SendMessageResult result = sqs.sendMessage(new SendMessageRequest()
                .withQueueUrl(SQSSender.QUEUE_NAME)
                .withMessageGroupId("budgets")
                .withMessageDeduplicationId("test")
                .withMessageBody(msg));

        log.info("Send message succeeded with messageId: {}, sequence number: {}", result.getMessageId(), result.getSequenceNumber());
    }

    private static String buildMessage(Transaction transaction) {
        JSONObject payloadObject = new JSONObject();

        payloadObject.put("id", transaction.getId());
        payloadObject.put("accountId", transaction.getAccountId());
        payloadObject.put("settledAt", transaction.getSettledAt());
        payloadObject.put("amount", transaction.getAmount());
        payloadObject.put("description", transaction.getDescription());
        payloadObject.put("categoryId", transaction.getCategoryId());

        return payloadObject.toString();
    }
}
