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

package com.timvisee.beamercontroller.serial;

public enum FlowControlType {

    NONE(0),
    RTSCTS_IN(1),
    RTSCTS_OUT(2),
    XONXOFF_IN(4),
    XONXOFF_OUT(8);

    /**
     * Flow control ID.
     */
    public int id;

    /**
     * Constructor.
     *
     * @param id Flow control type ID.
     */
    FlowControlType(int id) {
        this.id = id;
    }

    /**
     * Get the flow control ID.
     *
     * @return Flow control type ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the value for jSSC.
     *
     * @return Value for jSSC.
     */
    public int getSerialConnectorValue() {
        return this.id;
    }

    /**
     * Get the flow control type by it's ID.
     *
     * @param id ID to get the flow control type for.
     *
     * @return The flow control type.
     */
    public static FlowControlType getById(int id) {
        // Loop through the flow control types and find the correct one
        for (FlowControlType type : values())
            if(type.getId() == id)
                return type;

        // We haven't found anything, throw an exception
        throw new RuntimeException("Invalid flow control type ID");
    }
}
