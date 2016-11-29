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

public enum BaudRateType {

    BAUDRATE_110(110),
    BAUDRATE_300(300),
    BAUDRATE_600(600),
    BAUDRATE_1200(1200),
    BAUDRATE_4800(4800),
    BAUDRATE_9600(9600),
    BAUDRATE_14400(14400),
    BAUDRATE_19200(19200),
    BAUDRATE_38400(38400),
    BAUDRATE_57600(57600),
    BAUDRATE_115200(115200),
    BAUDRATE_128000(128000),
    BAUDRATE_256000(256000);

    /**
     * Baud rate type ID.
     */
    public int id;

    /**
     * Constructor.
     *
     * @param id Type ID.
     */
    BaudRateType(int id) {
        this.id = id;
    }

    /**
     * Get the type ID.
     *
     * @return Type ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get the actual baud rate.
     *
     * @return Actual baud rate.
     */
    public int getBaudRate() {
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
     * Get the baud rate type by it's ID.
     *
     * @param id ID of the baud rate type.
     *
     * @return Baud rate type.
     */
    public static BaudRateType getById(int id) {
        // Loop through the types and return the one with the same ID
        for(BaudRateType type : values())
            if(type.getId() == id)
                return type;

        // We haven't found anything, throw an exception
        throw new RuntimeException("Invalid data bit type ID");
    }

    /**
     * Get the proper baud rate type by it's baud rate value.
     *
     * @param baudRate Baud rate.
     *
     * @return Baud rate type.
     */
    public static BaudRateType getByRate(int baudRate) {
        return getById(baudRate);
    }
}
