package com.egoramel.webproject.model.dao;

import com.egoramel.webproject.model.entity.AppUser;
import com.egoramel.webproject.exception.CustomException;

import java.util.Optional;

public interface AppUserDao extends Dao<AppUser> {
    Optional<AppUser> findById(final Long id) throws CustomException;
    boolean existsByLogin(final String login) throws CustomException;
    Optional<AppUser> findByLogin(final String login) throws CustomException;
}