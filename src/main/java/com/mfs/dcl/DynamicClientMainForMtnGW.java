package com.mfs.dcl;

import com.google.gson.Gson;
import com.mfs.dcl.dtos.C2BPostVerifyCollectionRequestDto;
import com.mfs.dcl.dtos.GenericDclResponseDto;
import com.mfs.dcl.model.BeyonicSystemConfigModel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

/*
 * DynamicClientMainForMtnGW  Main Class
 *
 * @since 28-04-2022
 */


public class DynamicClientMainForMtnGW {

    private static final Logger LOGGER = Logger.getLogger(DynamicClientMainForMtnGW.class);

    public static void main(String[] args) {

        int key = Integer.parseInt(args[0]);

        C2BPostVerifyCollectionRequestDto parsedDto = new C2BPostVerifyCollectionRequestDto();

        //Check key for collection
        if (key == 1 || key == 0) {
            parsedDto = new Gson().fromJson(args[2], C2BPostVerifyCollectionRequestDto.class);
        }


        String jsonstring = "";

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties"));

        switch (key) {
            case 0:
                LOGGER.info("Request Dto : From Request " + args[0] + " Target jar location is : " + args[1]
                        + " Here is Request : " + parsedDto.toString());
                jsonstring = getResponseForGetStatus(parsedDto);
                break;

            case 1:
                LOGGER.info("Request Dto : From Request " + args[0] + " Target jar location is : " + args[1]
                        + " Here is Request : " + parsedDto.toString());
                jsonstring = getResponseForPostCollection(parsedDto);
                break;
        }

        LOGGER.info("Response from Mtn GW DCL : " + jsonstring);
        System.out.println(jsonstring);
        System.exit(0);
    }

    //For getstatus response
    public static String getResponseForGetStatus(C2BPostVerifyCollectionRequestDto data) {

        String[] jsonResponseParams = new String[3];
        String response = null;
        String params = "";
        String jsonresponse = "";

        try {
            response = new OpenApiClientClass().transactionStatus(data.getCollection().getCollection_request_id());

        } catch (Exception e) {
            LOGGER.error("Exception raise in getStatus Dynamic class: " + e.toString() + ", message: " + e.getMessage());
            jsonResponseParams[0] = "268";
            jsonResponseParams[2] = "Pending";
            jsonResponseParams[1] = data.getCollection().getCollection_request_id();
            response = Arrays.toString(jsonResponseParams);

        }
        jsonresponse = response.replace("[", "").replace("]", "").replace(", ", ",");
        jsonresponse = converttogeneric(jsonresponse, "2");

        return jsonresponse;

    }

    //get Response for post collection
    public static String getResponseForPostCollection(C2BPostVerifyCollectionRequestDto data) {
        String[] jsonResponseParams = new String[3];
        String response = null;
        String params = "";
        String jsonresponse = "";
        try {
            BeyonicSystemConfigModel systemconfig = null;
            DBConnect dbcon = DBConnect.getInstance();
            systemconfig = dbcon.geturl();
            response = new OpenApiClientClass().createPayment( data.getCollection().getAmount(), data.getCollection().getCurrency_code(), data.getCollection().getCollection_request_id(), data.getSender().getAccount_number(),data.getCollection().getCountry_code(), data.getCollection().getReference());

        } catch (Exception e) {
            LOGGER.error("Exception raise in post collection Dynamic class: " + e.toString() + ", message: " + e.getMessage());
            jsonResponseParams[0] = "100";
            jsonResponseParams[2] = "Fail";
            jsonResponseParams[1] = data.getCollection().getCollection_request_id();
            response = Arrays.toString(jsonResponseParams);
        }
        jsonresponse = response.replace("[", "").replace("]", "").replace(", ", ",");
        jsonresponse = converttogeneric(jsonresponse, "1");

        return jsonresponse;
    }

    //for verification

    private static String converttogeneric(String retString, String method) {

        GenericDclResponseDto genericDclResponse = null;
        retString = retString.replaceFirst(",", "@");

        retString = retString.replaceFirst(",", "@");

        String[] splitArr = retString.split("@");
        String param1 = splitArr[0];
        String param2 = splitArr[1];
        param2 = (null != param2 && !param2.trim().isEmpty()) ? param2.replaceAll("\n", "") : "";

        String param3 = splitArr[2];
        param3 = (null != param3 && !param3.trim().isEmpty()) ? param3.replaceAll("\n", "") : "";

        String message = splitArr[2];
        String messageStr = (null != message && !message.trim().isEmpty()) ? message.replaceAll("\n", "") : "";
        genericDclResponse = new GenericDclResponseDto(splitArr[0], splitArr[1], messageStr, null);
        genericDclResponse = new GenericDclResponseDto(param1, param3, param2, null);
        if (method.equals("2")) {
            if (retString.contains("01")) {
                genericDclResponse = new GenericDclResponseDto(param1, param3, param2, null);
            } else if (retString.toUpperCase().contains("FAIL")) {
                genericDclResponse = new GenericDclResponseDto(param1, param3, param2, null);
            }
        }
        if (method.equals("1")) {
            if (retString.contains("268")) {
                genericDclResponse = new GenericDclResponseDto(param1, param3, param2, null);
            } else if (retString.toLowerCase().contains("fail")) {
                genericDclResponse = new GenericDclResponseDto(param1, param3, param2, null);
            } else if (retString.toUpperCase().contains("SUCCESS") || retString.contains("01")) {
                genericDclResponse = new GenericDclResponseDto(param1, param3, param2, null);
            }
        }

        String jsonInString = new Gson().toJson(genericDclResponse);
        LOGGER.info(" response from Mtn GW Jar : " + retString);

        LOGGER.info(" Mtn GW Jar Json Response : " + jsonInString);

        return jsonInString;

    }
}
