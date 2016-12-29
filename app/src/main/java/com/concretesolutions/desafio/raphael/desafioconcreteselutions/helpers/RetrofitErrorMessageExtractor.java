package com.concretesolutions.desafio.raphael.desafioconcreteselutions.helpers;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import retrofit.RetrofitError;

/**
 * Created by Raphael on 11/10/2016.
 */

public class RetrofitErrorMessageExtractor {

    public static String buildErrorMessage(RetrofitError retrofitError) {
        String errorMessage = "";
        JsonObject body = new JsonObject();
        try {
            body = (JsonObject) retrofitError.getBodyAs(JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            body.addProperty("message", "Não foi possível processar a solicitação, por favor tente novamente mais tarde.");
        }

        if (body != null) {
            JsonElement messagesElement = body.get("message");

            if (messagesElement != null) {
                Gson gson = new Gson();
                errorMessage = "";

                try {
                    if (messagesElement.isJsonArray()) {
                        for (JsonElement messageElement : messagesElement.getAsJsonArray())
                            errorMessage += messageElement.getAsString().toString() + "\n";
                    } else {
                        errorMessage += gson.fromJson(messagesElement, String.class);
                    }
                } catch (JsonSyntaxException e) {
                    HashMap<String, List<String>> errorMessages = gson.fromJson(messagesElement, new TypeToken<HashMap<String, List<String>>>() {
                    }.getType());
                    Set<String> keys = errorMessages.keySet();

                    for (String key : keys) {
                        List<String> values = errorMessages.get(key);

                        for (String value : values) {
                            errorMessage += value + "\n";
                        }
                    }
                }
            }
        } else {
            errorMessage = "Houve um erro ao processar a sua requisição.";
        }

        return errorMessage;
    }
}
