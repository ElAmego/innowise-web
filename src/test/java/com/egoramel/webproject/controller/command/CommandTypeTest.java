package com.egoramel.webproject.controller.command;

import com.egoramel.webproject.controller.command.impl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CommandTypeTest {
    @Test
    void shouldReturnLoginCommand() {
        final Command result = CommandType.define("LOGIN");
        assertInstanceOf(LoginCommand.class, result);
    }

    @Test
    void shouldReturnRegistrationCommand() {
        final Command result = CommandType.define("REGISTRATION");
        assertInstanceOf(RegistrationCommand.class, result);
    }

    @Test
    void shouldReturnLogoutCommand() {
        final Command result = CommandType.define("LOGOUT");
        assertInstanceOf(LogoutCommand.class, result);
    }

    @Test
    void shouldReturnAllUsersCommand() {
        final Command result = CommandType.define("ALL_USERS");
        assertInstanceOf(AllUsersCommand.class, result);
    }

    @Test
    void shouldReturnDeleteCommand() {
        final Command result = CommandType.define("DELETE");
        assertInstanceOf(DeleteCommand.class, result);
    }

    @Test
    void shouldReturnDefaultCommand() {
        final Command result = CommandType.define("DEFAULT");
        assertInstanceOf(DefaultCommand.class, result);
    }

    @Test
    void shouldBeCaseInsensitive() {
        final Command result = CommandType.define("login");
        assertInstanceOf(LoginCommand.class, result);
    }
}