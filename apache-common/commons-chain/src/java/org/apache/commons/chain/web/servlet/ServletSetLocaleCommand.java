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
package org.apache.commons.chain.web.servlet;


import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.AbstractSetLocaleCommand;


/**
 * <p>Concrete implementation of {@link AbstractSetLocaleCommand} for
 * the Servlet API.</p>
 */

public class ServletSetLocaleCommand extends AbstractSetLocaleCommand {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Establish the specified <code>Locale</code> for this response.</p>
     *
     * @param context The {@link Context} we are operating on.
     * @param locale The Locale for the request.
     */
    protected void setLocale(Context context, Locale locale) {

    HttpServletResponse response = (HttpServletResponse)
        context.get("response");
    response.setLocale(locale);

    }


}
