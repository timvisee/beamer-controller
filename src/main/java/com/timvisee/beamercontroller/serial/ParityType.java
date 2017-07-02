/******************************************************************************
 * Copyright (c) Tim Visee 2016-2017. All rights reserved.                    *
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

public enum ParityType {

    PARITY_NONE(0),
    PARITY_ODD(1),
    PARITY_EVEN(2),
    PARITY_MARK(3),
    PARITY_SPACE(4);

    /**
     * Parity type ID.
     */
    public int id;

    /**
     * Constructor.
     *
     * @param id Parity type ID.
     */
    ParityType(int id) {
        this.id = id;
    }

    /**
     * Get the parity type ID.
     *
     * @return Parity type ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the corresponding jSSC value.
     *
     * @return Corresponding jSSC value.
     */
    public int toJsscValue() {
        return this.id;
    }

    /**
     * Get the parity type by it's ID.
     *
     * @param id ID to get the parity type type for.
     * @return The parity type.
     */
    public static ParityType getById(int id) {
        // Loop through the parity types and find the correct one
        for(ParityType type : values())
            if(type.getId() == id)
                return type;

        // We haven't found anything, throw an exception
        throw new RuntimeException("Invalid parity type ID");
    }
}
