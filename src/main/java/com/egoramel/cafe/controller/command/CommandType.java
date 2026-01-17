package com.egoramel.cafe.controller.command;

import com.egoramel.cafe.controller.command.impl.DefaultCommand;
import com.egoramel.cafe.controller.command.impl.LoginCommand;
import com.egoramel.cafe.controller.command.impl.RegistrationCommand;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    DEFAULT(new DefaultCommand());

    private static final Logger LOGGER = LogManager.getLogger();
    final Command command;

    public static Command define(final String cmd) {
        Command current = DEFAULT.command;

        if (cmd == null || cmd.isBlank()) {
            return current;
        }

        try {
            final CommandType currentCommandType = CommandType.valueOf(cmd.toUpperCase());
            current = currentCommandType.command;
        } catch (final IllegalArgumentException e) {
            LOGGER.error("Undefined command '{}': {}",  cmd, e.getMessage());
        }

        return current;
    }
}