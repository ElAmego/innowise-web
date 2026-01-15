package com.egoramel.cafe;

import com.egoramel.cafe.model.dao.impl.AppUserDaoImpl;
import com.egoramel.cafe.exception.CustomException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running...");
        final AppUserDaoImpl appUserDaoImpl = new AppUserDaoImpl();
        try {
            var s = appUserDaoImpl.findById(1L);
            System.out.println(s);
        } catch (CustomException e) {
            throw new RuntimeException(e);
        }
    }
}
