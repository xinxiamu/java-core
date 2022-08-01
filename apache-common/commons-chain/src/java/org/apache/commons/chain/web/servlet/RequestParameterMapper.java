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


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.generic.LookupCommand;


/**
 * <p>{@link Command} that uses a specified request parameter
 * to select a {@link Command} from the appropriate {@link Catalog}, and
 * execute it.  To use this command, you would typically map an instance
 * of {@link ChainProcessor} to a wildcard pattern like "*.execute" and
 * then arrange that this is the default command to be executed.  In such
 * an environment, a request for the context-relative path
 * "/foo.execute?command=bar" would cause the "/bar" command to be loaded
 * and executed.</p>
 *
 * @author Craig R. McClanahan
 */

public class RequestParameterMapper extends LookupCommand implements Command {


    // ------------------------------------------------------ Instance Variables


    private String catalogKey = ChainProcessor.CATALOG_DEFAULT;
    private String parameter = "command";


    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the context key under which our {@link Catalog} has been
     * stored.</p>
     *
     * @return The context key for the Catalog.
     */
    public String getCatalogKey() {

        return (this.catalogKey);

    }


    /**
     * <p>Set the context key under which our {@link Catalog} has been
     * stored.</p>
     *
     * @param catalogKey The new catalog key
     *
     * @deprecated Use catalogName to specify the name of the catalog in the
     *  catalog factory
     */
    public void setCatalogKey(String catalogKey) {

        this.catalogKey = catalogKey;

    }


    /**
     * <p>Return the name of the request parameter to use for
     * selecting the {@link Command} to be executed.</p>
     *
     * @return The name of the request parameter.
     *
     * @deprecated Use catalogName to specify the name of the catalog in the
     *  catalog factory
     */
    public String getParameter() {

        return (this.parameter);

    }


    /**
     * <p>Set the name of the request parameter to use for
     * selecting the {@link Command} to be executed.</p>
     *
     * @param parameter The new parameter name
     */
    public void setParameter(String parameter) {

        this.parameter = parameter;

    }


    // --------------------------------------------------------- Command Methods


    /**
     * <p>Look up the specified request paramater for this request, and use it
     * to select an appropriate {@link Command} to be executed.
     *
     * @param context Context for the current request
     * @return The name of the {@link Command} instance
     *
     * @since Chain 1.2
     */
    protected String getCommandName(Context context) {

        // Look up the specified request parameter for this request
        ServletWebContext swcontext = (ServletWebContext) context;
        HttpServletRequest request = swcontext.getRequest();
        String value = request.getParameter(getParameter());
        return value;

    }


    /**
     * <p>Return the {@link Catalog} to look up the {@link Command} in.</p>
     *
     * @param context {@link Context} for this request
     * @return The catalog.
     * @exception IllegalArgumentException if no {@link Catalog}
     *  can be found
     *
     * @since Chain 1.2
     */
    protected Catalog getCatalog(Context context) {
        Catalog catalog = (Catalog) context.get(getCatalogKey());
        if (catalog == null) {
            catalog = super.getCatalog(context);
        }
        return catalog;
    }


}
