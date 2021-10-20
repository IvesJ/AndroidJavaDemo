package com.acecoder.javalib.testbean;

import com.acecoder.javalib.annotation.UseCase;

import java.util.List;

public class PassWordUtils {

    @UseCase(id = 47, deecription = "Passwords must contain at least one numeric")
    public boolean validatePassWord(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassWord(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, deecription = "New passwords can not equal previously used ones")
    public boolean checkForNewPassWord(List<String> prevPassWords, String password) {
        return !prevPassWords.contains(password);
    }
}
