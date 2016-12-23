/*
 * Copyright (c) Tim Visee 2016. All rights reserved.
 *
 * @author Tim Visee
 *
 * Open Source != No Copyright
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software")
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 *
 */

package com.timvisee.beamercontroller.beamer.command;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    /**
     * List of loaded commands.
     */
    private List<Command> commands = new ArrayList<>();

    /**
     * Constructor.
     */
    public CommandManager() {}

    /**
     * Get the list of commands.
     *
     * @return List of commands.
     */
    public List<Command> getCommands() {
        return this.commands;
    }

    /**
     * Get the number of commands that is loaded.
     *
     * @return Number of loaded commands.
     */
    public int getCommandsCount() {
        return this.commands.size();
    }

    /**
     * Load the commands from the configuration.
     */
    public void load(ConfigurationSection commandsSection) {
        // Clear the list of loaded commands
        this.commands.clear();

        // Loop through the keys available in the commands section
        for(String key : commandsSection.getKeys(""))
            // Get the command section, load the command, and add it to the list
            this.commands.add(Command.load(commandsSection.getConfigurationSection(key)));
    }
}
