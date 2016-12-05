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

package com.timvisee.beamercontroller.beamer;

public class Beamer {

    /**
     * Beamer ID.
     */
    private String id;

    /**
     * Beamer type name.
     */
    private String name;

    /**
     * Constructor.
     *
     * @param id Beamer id.
     * @param name Beamer name.
     */
    public Beamer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Beamer ID.
     *
     * @return Beamer ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the beamer type name.
     *
     * @return Beamer type name.
     */
    public String getName() {
        return this.name;
    }
}
