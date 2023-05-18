package com.nemirovsky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TelegramBotTestController {

    @GetMapping("test/**")
    @Operation(summary = "Returns test message")
    public String getTestMessage() {

        return "This is a Telegram Bot service response test message!";
    }
}
