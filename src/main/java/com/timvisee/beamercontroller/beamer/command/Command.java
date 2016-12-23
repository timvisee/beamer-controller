/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software")  *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.beamercontroller.beamer.command;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class Command {

    /**
     * Unique command ID.
     */
    private String id;

    /**
     * Command name.
     */
    private String name;

    /**
     * Command type.
     */
    private CommandType commandType;

    /**
     * The actual command to send.
     */
    private String command;

    /**
     * Constructor.
     *
     * @param id Command ID.
     * @param name Command name.
     * @param type Command type.
     * #param command Command.
     */
    public Command(String id, String name, CommandType type, String command) {
        this.id = id;
        this.name = name;
        this.commandType = type;
        this.command = command;
    }

    /**
     * Get the command ID.
     *
     * @return Command ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the command ID.
     *
     * @param id Command ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the command name.
     *
     * @return Command name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the command name.
     *
     * @param name Command name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the command type.
     *
     * @return Command type.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }

    /**
     * Set the command type.
     *
     * @param commandType Command type.
     */
    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Get the command.
     *
     * @return Command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Set the command.
     *
     * @param command Command.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Load a command from the given configuration section.
     *
     * @param commandSection Configuration section of the command.
     * @return Loaded command instance.
     */
    public static Command load(ConfigurationSection commandSection) {
        // Get the command ID
        final String id = commandSection.getName();

        // Fetch the name, type and command properties
        final String name = commandSection.getString("name", "Unknown");
        final CommandType commandType = CommandType.getById(commandSection.getString("type"));
        final String command = commandSection.getString("command", "");

        // Create a new command instance, and return it
        return new Command(id, name, commandType, command);
    }
}
