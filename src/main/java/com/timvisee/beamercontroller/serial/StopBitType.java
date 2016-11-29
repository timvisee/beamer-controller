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

public enum StopBitType {

    STOPBIT_1(1),
    STOPBIT_2(2),
    STOPBIT_1_5(3);

    /**
     * Type ID.
     */
    public int id;

    /**
     * Constructor.
     *
     * @param id Stop bit ID.
     */
    StopBitType(int id) {
        this.id = id;
    }

    /**
     * Get the stop bit ID.
     *
     * @return Stop bit ID
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
     * Find the stop bit type by it's ID.
     *
     * @param id Stop bit type ID.
     *
     * @return Stop bit type.
     */
    public static StopBitType getById(int id) {
        // Loop through the types and return the one with the same ID
        for(StopBitType type : values())
            if(type.getId() == id)
                return type;

        // We haven't found anything, throw an exception
        throw new RuntimeException("Invalid stop bit type ID");
    }
}