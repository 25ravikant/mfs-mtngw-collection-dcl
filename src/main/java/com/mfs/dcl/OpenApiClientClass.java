package com.mfs.dcl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class OpenApiClientClass {

    static String baseURL = "http://localhost:8080/api/v1/mtn/payments";



    public String createPayment(String amount, String currencyCode, String mfsTransactionId, String senderMsisdn,
                                 String sendCountry, String callbackUrl) {
        String createPaymentUrl = baseURL + "/";
        String response = "";
        Map<String, Object> jsonResponse1 = null;
        String responseCode = null;
        String[] jsonResponseParams = new String[3];
        String params = "";

        JsonObject transaction = new JsonObject();
        JsonObject additionalPara = new JsonObject();

        try {
            transaction.addProperty("amount", amount);
            transaction.addProperty("currencyCode", currencyCode);
            transaction.addProperty("mfsTransactionId", mfsTransactionId);
            transaction.addProperty("senderMsisdn", senderMsisdn);
            transaction.addProperty("sendCountry", sendCountry);
            transaction.addProperty("callbackUrl", callbackUrl);
            additionalPara.addProperty("transactionType", "Debit");
            additionalPara.addProperty("type", "Mobile Money");
            transaction.add("additionalParameter", additionalPara);
            response = getConnectionjsonResponse(createPaymentUrl, transaction.toString());

            //LOGGER.info("create collection response from partner: "+response);
            if (response != null) {
                jsonResponse1 = jsonToMapObject1(response);
                if (jsonResponse1.containsKey("responseCode")) {
                    responseCode = jsonResponse1.get("responseCode").toString();
                }
            }

            if (response == null) {
                jsonResponseParams[0] = "268";
                jsonResponseParams[2] = "Pending";
                jsonResponseParams[1] = mfsTransactionId;
                params = Arrays.toString(jsonResponseParams);
            } else if (responseCode.equals("268")) {
                jsonResponseParams[0] = "268";
                jsonResponseParams[1] = jsonResponse1.get("etransactionId").toString();
                jsonResponseParams[2] = "Pending";
                params = Arrays.toString(jsonResponseParams);

            } else if (responseCode.equals("100")) {
                jsonResponseParams[0] = "100";
                jsonResponseParams[2] = "Fail-" + jsonResponse1.get("message").toString();
                jsonResponseParams[1] = mfsTransactionId;
                params = Arrays.toString(jsonResponseParams);
            } else if (responseCode.equals("01")) {
                jsonResponseParams[0] = "01";
                jsonResponseParams[2] = "SUCCESS";
                jsonResponseParams[1] = jsonResponse1.get("etransactionId").toString();
                params = Arrays.toString(jsonResponseParams);
            } else {
                jsonResponseParams[0] = "268";
                jsonResponseParams[2] = "Pending";
                jsonResponseParams[1] = mfsTransactionId;
                params = Arrays.toString(jsonResponseParams);
            }
        } catch (Exception e) {
           // LOGGER.error("create collection exception raised from partner: "+e.toString()+", message: "+e.getMessage());
            jsonResponseParams[0] = "268";
            jsonResponseParams[2] = "Pending";
            jsonResponseParams[1] = mfsTransactionId;
            params = Arrays.toString(jsonResponseParams);
        }

        return params;
    }

    public String transactionStatus(String mfsTransactionId){
        String transactionStatusUrl = baseURL +"/"+ mfsTransactionId+"/transaction-status";
        String response = "";
        String[] jsonResponseParams = new String[3];
        Map<String, Object> jsonResponse1 = null;
        String params = "";
        String responseCode = "";

        try {
             response = getConnectionjsonResponseGet(transactionStatusUrl);

            if (response != null) {
                jsonResponse1 = jsonToMapObject1(response);
            }

            if (response == null) {
                jsonResponseParams[0] = "268";
                jsonResponseParams[2] = "Pending";
                jsonResponseParams[1] = mfsTransactionId;
                params = Arrays.toString(jsonResponseParams);
                System.out.println(params.toString().replace("[", "").replace("]", "").replace(", ", ","));
            } else {
                if (jsonResponse1.containsKey("responseCode"))
                {
                    responseCode = jsonResponse1.get("responseCode").toString();
                    if (responseCode.equals("268")) {
                        jsonResponseParams[0] = "268";
                        jsonResponseParams[1] = jsonResponse1.get("etransactionId").toString();
                        jsonResponseParams[2] = "Pending";
                        params = Arrays.toString(jsonResponseParams);

                    } else if (responseCode.equals("100")) {
                        jsonResponseParams[0] = "100";
                        jsonResponseParams[2] = "Fail-" + jsonResponse1.get("message").toString();
                        jsonResponseParams[1] = mfsTransactionId;
                        params = Arrays.toString(jsonResponseParams);
                    } else if (responseCode.equals("01")) {
                        jsonResponseParams[0] = "01";
                        jsonResponseParams[2] = "SUCCESS";
                        jsonResponseParams[1] = jsonResponse1.get("etransactionId").toString();
                        params = Arrays.toString(jsonResponseParams);
                    } else {
                        jsonResponseParams[0] = "268";
                        jsonResponseParams[2] = "Pending";
                        jsonResponseParams[1] = mfsTransactionId;
                        params = Arrays.toString(jsonResponseParams);
                    }
                } else {
                    jsonResponseParams[0] = "268";
                    jsonResponseParams[2] = "Pending";
                    jsonResponseParams[1] = mfsTransactionId;
                    params = Arrays.toString(jsonResponseParams);
                }
            }
        } catch (Exception e) {

            jsonResponseParams[0] = "268";
            jsonResponseParams[2] = "Pending";
            jsonResponseParams[1] = mfsTransactionId;
            params = Arrays.toString(jsonResponseParams);
        }

        return params;

    }

    private String getConnectionjsonResponseGet(String serviceUrl) throws Exception {

        MultiThreadedHttpConnectionManager localMultiThreadedHttpConnectionManager = null;

        HttpClient localHttpClient = null;

        long i = 300000;

        localMultiThreadedHttpConnectionManager = new MultiThreadedHttpConnectionManager();

        HttpConnectionManagerParams localHttpConnectionManagerParams = new HttpConnectionManagerParams();

        localHttpConnectionManagerParams.setDefaultMaxConnectionsPerHost(100);

        localMultiThreadedHttpConnectionManager.setParams(localHttpConnectionManagerParams);

        localHttpClient = new HttpClient(localMultiThreadedHttpConnectionManager);

        GetMethod get = new GetMethod(serviceUrl);

        int status = localHttpClient.executeMethod(get);

        InputStream input = get.getResponseBodyAsStream();

        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader rd = new BufferedReader(isr);
        StringBuilder jsonResponse = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            jsonResponse.append(line);
            jsonResponse.append('\r');
        }
        rd.close();

        return jsonResponse.toString();
    }

    private String getConnectionjsonResponse(String serviceUrl, String request) throws Exception {

        MultiThreadedHttpConnectionManager localMultiThreadedHttpConnectionManager = null;
        StringBuilder jsonResponse = new StringBuilder();
        HttpClient localHttpClient = null;

        long i = 300000;

        localMultiThreadedHttpConnectionManager = new MultiThreadedHttpConnectionManager();

        HttpConnectionManagerParams localHttpConnectionManagerParams = new HttpConnectionManagerParams();

        localHttpConnectionManagerParams.setDefaultMaxConnectionsPerHost(100);

        localMultiThreadedHttpConnectionManager.setParams(localHttpConnectionManagerParams);

        localHttpClient = new HttpClient(localMultiThreadedHttpConnectionManager);

        RequestEntity requestEntity = new StringRequestEntity(request.toString(), "application/json", "UTF-8");

        PostMethod post = new PostMethod(serviceUrl);

        post.setRequestEntity(requestEntity);

        post.setRequestHeader("Content-Type", "application/json");

        post.setRequestHeader("Accept", "application/json");

        int status = localHttpClient.executeMethod(post);

        InputStream input = post.getResponseBodyAsStream();

        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader rd = new BufferedReader(isr);

        String line;

        while ((line = rd.readLine()) != null) {
            jsonResponse.append(line);
            jsonResponse.append('\r');
        }
        rd.close();

        return jsonResponse.toString();

    }

    static Map<String, Object> jsonToMapObject1(String jsonString) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> myObjects = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });

        myObjects = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });

        return myObjects;
    }
}
