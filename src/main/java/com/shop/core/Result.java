package com.shop.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meg on 7/19/17.
 */

public class Result {

    String message;
    List<?> result = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getResult() {
        return result;
    }

    public void setResult(List<?> result) {
        this.result = result;
    }
}
