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
package org.apache.commons.chain.web.portlet;


import java.util.Locale;
import javax.portlet.PortletRequest;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.AbstractGetLocaleCommand;


/**
 * <p>Concrete implementation of {@link AbstractGetLocaleCommand} for
 * the Portlet API.</p>
 */

public class PortletGetLocaleCommand extends AbstractGetLocaleCommand {


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Retrieve and return the <code>Locale</code> for this request.</p>
     *
     * @param context The {@link Context} we are operating on.
     * @return The Locale for the request.
     */
    protected Locale getLocale(Context context) {

    PortletRequest request = (PortletRequest)
        context.get("request");
    return (request.getLocale());

    }


}
