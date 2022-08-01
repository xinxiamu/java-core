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


import java.util.Map;
import org.apache.commons.chain.impl.ContextBase;


/**
 * <p>Abstract base implementation of {@link org.apache.commons.chain.Context} that
 * provides web based applications that use it a "generic" view of HTTP related
 * requests and responses, without tying the application to a particular underlying
 * Java API (such as servlets).  It is expected that a concrete subclass
 * of {@link WebContext} for each API (such as
 * {@link org.apache.commons.chain.web.servlet.ServletWebContext})
 * will support adapting that particular API's implementation of request
 * and response objects into this generic framework.</p>
 *
 * <p>The characteristics of a web request/response are made visible via
 * a series of JavaBeans properties (and mapped to read-only attributes
 * of the same name, as supported by {@link ContextBase}.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 480477 $ $Date: 2006-11-29 08:34:52 +0000 (Wed, 29 Nov 2006) $
 */

public abstract class WebContext extends ContextBase {


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Return a mutable <code>Map</code> that maps application scope
     * attribute names to their values.</p>
     *
     * @return Application scope Map.
     */
    public abstract Map getApplicationScope();


    /**
     * <p>Return an immutable <code>Map</code> that maps header names to
     * the first (or only) header value (as a String).  Header names must
     * be matched in a case-insensitive manner.</p>
     *
     * @return Header values Map.
     */
    public abstract Map getHeader();


    /**
     * <p>Return an immutable <code>Map</code> that maps header names to
     * the set of all values specified in the request (as a String array).
     * Header names must be matched in a case-insensitive manner.</p>
     *
     * @return Header values Map.
     */
    public abstract Map getHeaderValues();


    /**
     * <p>Return an immutable <code>Map</code> that maps context application
     * initialization parameters to their values.</p>
     *
     * @return Initialization parameter Map.
     */
    public abstract Map getInitParam();


    /**
     * <p>Return an immutable <code>Map</code> that maps request parameter
     * names to the first (or only) value (as a String).</p>
     *
     * @return Request parameter Map.
     */
    public abstract Map getParam();


    /**
     * <p>Return an immutable <code>Map</code> that maps request parameter
     * names to the set of all values (as a String array).</p>
     *
     * @return Request parameter Map.
     */
    public abstract Map getParamValues();


    /**
     * <p>Return an immutable <code>Map</code> that maps cookie names to
     * the set of cookies specified in the request.
     *
     * @return Map of Cookies.
     * @since Chain 1.1
     */
    public abstract Map getCookies();


    /**
     * <p>Return a mutable <code>Map</code> that maps request scope
     * attribute names to their values.</p>
     *
     * @return Request scope Map.
     */
    public abstract Map getRequestScope();


    /**
     * <p>Return a mutable <code>Map</code> that maps session scope
     * attribute names to their values.</p>
     *
     * @return Session scope Map.
     */
    public abstract Map getSessionScope();


}
