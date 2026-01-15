package com.egoramel.cafe.service;

public interface AppUserService {
    boolean isLoginExist(final String login);
    boolean createNewAppUser(final String login, final String password);
    boolean authenticate(final String login, final String password);
}