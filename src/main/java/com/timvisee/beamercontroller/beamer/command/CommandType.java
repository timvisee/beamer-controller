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

public enum CommandType {

    WRITE("write"),
    READ("read");

    /**
     * Command type ID.
     */
    public String id;

    /**
     * Command ID.
     *
     * @param id Command ID.
     */
    CommandType(String id) {
        this.id = id;
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
     * Get the command type by it's ID.
     *
     * @param id Command type ID.
     *
     * @return Command type.
     */
    public static CommandType getById(String id) {
        // Loop through the commands and return the one with the same ID
        for(CommandType type : values())
            if(type.getId().equals(id))
                return type;

        // No command type found, throw an error
        throw new RuntimeException("unknown command type ID");
    }
}
