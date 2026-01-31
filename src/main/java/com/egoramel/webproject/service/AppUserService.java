package com.egoramel.webproject.service;

import com.egoramel.webproject.model.entity.AppUser;

public interface AppUserService {
    boolean isLoginExist(final String login);
    boolean createNewAppUser(final String login, final String password);
    boolean deleteAppUser(final Object userIdObj);
    AppUser authenticate(final String login, final String password);
}