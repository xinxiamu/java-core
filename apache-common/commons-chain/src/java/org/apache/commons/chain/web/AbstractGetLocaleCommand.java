/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.chain.web;


import java.util.Locale;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;


/**
 * <p>Abstract base {@link Command} implementation for retrieving the
 * requested Locale from our {@link Context}, and storing it under the
 * context attribute key returned by the <code>localeKey</code> property.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 480477 $ $Date: 2006-11-29 08:34:52 +0000 (Wed, 29 Nov 2006) $
 */

public abstract class AbstractGetLocaleCommand implements Command {


    // -------------------------------------------------------------- Properties


    /**
     * <p>The context attribute key used to store the <code>Locale</code>.</p>
     */
    private String localeKey = "locale";


    /**
     * <p>Return the context attribute key under which we will store
     * the request <code>Locale</code>.</p>
     *
     * @return The context attribute key of the request <code>Locale</code>.
     */
    public String getLocaleKey() {

    return (this.localeKey);

    }


    /**
     * <p>Set the context attribute key under which we will store
     * the request <code>Locale</code>.</p>
     *
     * @param localeKey The new context attribute key
     */
    public void setLocaleKey(String localeKey) {

    this.localeKey = localeKey;

    }


    // --------------------------------------------------------- Command Methods


    /**
     * <p>Retrieve the <code>Locale</code> for this request, and store it
     * under the specified context attribute.</p>
     *
     * @param context The {@link Context} we are operating on
     *
     * @return <code>false</code> so that processng will continue
     * @throws Exception If an error occurs during execution.
     */
    public boolean execute(Context context) throws Exception {

    context.put(getLocaleKey(), getLocale(context));
    return (false);

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Retrieve and return the <code>Locale</code> for this request.</p>
     * @param context The {@link Context} we are operating on.
     * @return The Locale for the request.
     */
    protected abstract Locale getLocale(Context context);


}
