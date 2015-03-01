/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 */

package org.opensolaris.opengrok.search.context;

import java.util.logging.Level;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.opensolaris.opengrok.OpenGrokLogger;

/**
 *
 * @author dobrou@gmail.com
 */
class RegexpMatcher extends LineMatcher {
    private final Pattern termRegexp;

    public RegexpMatcher(String term, boolean caseInsensitive) {
        super(caseInsensitive); 
        Pattern regexp;
        try {
            regexp = Pattern.compile(term, caseInsensitive ? Pattern.CASE_INSENSITIVE : 0 );
        } catch ( PatternSyntaxException  e) {
            regexp = null;
            OpenGrokLogger.getLogger().log(Level.WARNING, "RegexpMatcher: {0}", e.getMessage() );
        }
        this.termRegexp = regexp;
    }

    @Override
    public int match(String line) {
        return (termRegexp != null && termRegexp.matcher(line).matches() )
            ? MATCHED 
            : NOT_MATCHED
        ;
    }
    
}
