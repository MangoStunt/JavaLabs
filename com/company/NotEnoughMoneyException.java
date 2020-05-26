package com.company;

public class NotEnoughMoneyException extends Exception {
    @Override
    public String getMessage() {
        return "Less money on your account";
    }
}
