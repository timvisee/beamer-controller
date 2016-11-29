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

public enum DataBitType {

    DATABIT_5(5),
    DATABIT_6(6),
    DATABIT_7(7),
    DATABIT_8(8);

    /**
     * Data bit type ID.
     */
    public int id;

    /**
     * Constructor.
     *
     * @param id Data bit type ID.
     */
    DataBitType(int id) {
        this.id = id;
    }

    /**
     * Get the data bit type ID.
     *
     * @return Type ID.
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
     * Get the data bit type by it's ID.
     *
     * @param id ID of the data bit type.
     *
     * @return Data bit type.
     */
    public static DataBitType getById(int id) {
        // Loop through the values and return the one with the same ID
        for (DataBitType type : values())
            if(type.getId() == id)
                return type;

        // We haven't found anything, throw an exception
        throw new RuntimeException("Invalid data bit type ID");
    }
}
