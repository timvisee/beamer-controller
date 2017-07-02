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

package com.timvisee.beamercontroller.beamer.iface;

public abstract class BeamerInterface {

    /**
     * Beamer interface type.
     */
    private InterfaceType type;

    /**
     * Interface type.
     *
     * @param type Interface type.
     */
    BeamerInterface(InterfaceType type) {
        this.type = type;
    }

    /**
     * Get the beamer interface type.
     *
     * @return Beamer interface type.
     */
    public InterfaceType getType() {
        return this.type;
    }
}
