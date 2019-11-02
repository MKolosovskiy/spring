package com.shop.service;

public interface MailService {
    void sendCode(String code, String email);
}
