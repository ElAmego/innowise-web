package com.egoramel.cafe.util;

import java.security.NoSuchAlgorithmException;

public interface PasswordEncryptor {
    String encrypt(final String password) throws NoSuchAlgorithmException;
}
