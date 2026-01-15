package com.egoramel.cafe.utils;

import java.security.NoSuchAlgorithmException;

public interface PasswordEncryptor {
    String encrypt(final String password) throws NoSuchAlgorithmException;
}
