package com.egoramel.cafe.service;

import com.egoramel.cafe.model.entity.AppUser;

public interface AppUserService {
    boolean isLoginExist(final String login);
    boolean createNewAppUser(final String login, final String password);
    AppUser authenticate(final String login, final String password);
}