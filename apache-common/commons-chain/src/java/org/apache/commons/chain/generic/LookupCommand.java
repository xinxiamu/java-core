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
package org.apache.commons.chain.generic;


import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;


/**
 * <p>Look up a specified {@link Command} (which could also be a
 * {@link org.apache.commons.chain.Chain})
 * in a {@link Catalog}, and delegate execution to it.  If the delegated-to
 * {@link Command} is also a {@link Filter}, its <code>postprocess()</code>
 * method will also be invoked at the appropriate time.</p>
 *
 * <p>The name of the {@link Command} can be specified either directly (via
 * the <code>name</code> property) or indirectly (via the <code>nameKey</code>
 * property).  Exactly one of these must be set.</p>
 *
 * <p>If the <code>optional</code> property is set to <code>true</code>,
 * failure to find the specified command in the specified catalog will be
 * silently ignored.  Otherwise, a lookup failure will trigger an
 * <code>IllegalArgumentException</code>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 532951 $ $Date: 2007-04-27 04:59:00 +0100 (Fri, 27 Apr 2007) $
 */

public class LookupCommand implements Filter {


    // -------------------------------------------------------------- Constructors

    /**
     * Create an instance, setting its <code>catalogFactory</code> property to the
     * value of <code>CatalogFactory.getInstance()</code>.
     *
     * @since Chain 1.1
     */
    public LookupCommand() {
        this(CatalogFactory.getInstance());
    }

    /**
     * Create an instance and initialize the <code>catalogFactory</code> property
     * to given <code>factory</code>/
     *
     * @param factory The Catalog Factory.
     *
     * @since Chain 1.1
     */
    public LookupCommand(CatalogFactory factory) {
        this.catalogFactory = factory;
    }


    // -------------------------------------------------------------- Properties

    private CatalogFactory catalogFactory = null;

    /**
     * <p>Set the {@link CatalogFactory} from which lookups will be
     * performed.</p>
     *
     * @param catalogFactory The Catalog Factory.
     *
     * @since Chain 1.1
     */
    public void setCatalogFactory(CatalogFactory catalogFactory) {
        this.catalogFactory = catalogFactory;
    }

    /**
     * Return the {@link CatalogFactory} from which lookups will be performed.
     * @return The Catalog factory.
     *
     * @since Chain 1.1
     */
    public CatalogFactory getCatalogFactory() {

        return this.catalogFactory;
    }


    private String catalogName = null;

    /**
     * <p>Return the name of the {@link Catalog} to be searched, or
     * <code>null</code> to search the default {@link Catalog}.</p>
     * @return The Catalog name.
     */
    public String getCatalogName() {

        return (this.catalogName);

    }


    /**
     * <p>Set the name of the {@link Catalog} to be searched, or
     * <code>null</code> to search the default {@link Catalog}.</p>
     *
     * @param catalogName The new {@link Catalog} name or <code>null</code>
     */
    public void setCatalogName(String catalogName) {

        this.catalogName = catalogName;

    }


    private String name = null;


    /**
     * <p>Return the name of the {@link Command} that we will look up and
     * delegate execution to.</p>
     * @return The name of the Command.
     */
    public String getName() {

        return (this.name);

    }


    /**
     * <p>Set the name of the {@link Command} that we will look up and
     * delegate execution to.</p>
     *
     * @param name The new command name
     */
    public void setName(String name) {

        this.name = name;

    }


    private String nameKey = null;


    /**
     * <p>Return the context attribute key under which the {@link Command}
     * name is stored.</p>
     * @return The context key of the Command.
     */
    public String getNameKey() {

        return (this.nameKey);

    }


    /**
     * <p>Set the context attribute key under which the {@link Command}
     * name is stored.</p>
     *
     * @param nameKey The new context attribute key
     */
    public void setNameKey(String nameKey) {

        this.nameKey = nameKey;

    }


    private boolean optional = false;


    /**
     * <p>Return <code>true</code> if locating the specified command
     * is optional.</p>
     * @return <code>true</code> if the Command is optional.
     */
    public boolean isOptional() {

        return (this.optional);

    }


    /**
     * <p>Set the optional flag for finding the specified command.</p>
     *
     * @param optional The new optional flag
     */
    public void setOptional(boolean optional) {

        this.optional = optional;

    }

    private boolean ignoreExecuteResult = false;

    /**
     * <p>Return <code>true</code> if this command should ignore
     * the return value from executing the looked-up command.
     * Defaults to <code>false</code>, which means that the return result
     * of executing this lookup will be whatever is returned from that
     * command.</p>
     * @return <code>true</code> if result of the looked up Command
     * should be ignored.
     *
     * @since Chain 1.1
     */
    public boolean isIgnoreExecuteResult() {
        return ignoreExecuteResult;
    }

    /**
     * <p>Set the rules for whether or not this class will ignore or
     * pass through the value returned from executing the looked up
     * command.</p>
     * <p>If you are looking up a chain which may be "aborted" and
     * you do not want this class to stop chain processing, then this
     * value should be set to <code>true</code></p>
     * @param ignoreReturn <code>true</code> if result of the
     * looked up Command should be ignored.
     *
     * @since Chain 1.1
     */
    public void setIgnoreExecuteResult(boolean ignoreReturn) {
        this.ignoreExecuteResult = ignoreReturn;
    }

    private boolean ignorePostprocessResult = false;

    /**
     * <p>Return <code>true</code> if this command is a Filter and
     * should ignore the return value from executing the looked-up Filter's
     * <code>postprocess()</code> method.
     * Defaults to <code>false</code>, which means that the return result
     * of executing this lookup will be whatever is returned from that
     * Filter.</p>
     * @return <code>true</code> if result of the looked up Filter's
     * <code>postprocess()</code> method should be ignored.
     *
     * @since Chain 1.1
     */
    public boolean isIgnorePostprocessResult() {
        return ignorePostprocessResult;
    }

    /**
     * <p>Set the rules for whether or not this class will ignore or
     * pass through the value returned from executing the looked up
     * Filter's <code>postprocess()</code> method.</p>
     * <p>If you are looking up a Filter which may be "aborted" and
     * you do not want this class to stop chain processing, then this
     * value should be set to <code>true</code></p>
     * @param ignorePostprocessResult <code>true</code> if result of the
     * looked up Filter's <code>postprocess()</code> method should be ignored.
     *
     * @since Chain 1.1
     */
    public void setIgnorePostprocessResult(boolean ignorePostprocessResult) {
        this.ignorePostprocessResult = ignorePostprocessResult;
    }
    // ---------------------------------------------------------- Filter Methods


    /**
     * <p>Look up the specified command, and (if found) execute it.
     * Unless <code>ignoreExecuteResult</code> is set to <code>true</code>,
     * return the result of executing the found command.  If no command
     * is found, return <code>false</code>, unless the <code>optional</code>
     * property is <code>false</code>, in which case an <code>IllegalArgumentException</code>
     * will be thrown.
     * </p>
     *
     * @param context The context for this request
     *
     * @exception IllegalArgumentException if no such {@link Command}
     *  can be found and the <code>optional</code> property is set
     *  to <code>false</code>
     * @return the result of executing the looked-up command, or
     * <code>false</code> if no command is found or if the command
     * is found but the <code>ignoreExecuteResult</code> property of this
     * instance is <code>true</code>
     * @throws Exception if and error occurs in the looked-up Command.
     */
    public boolean execute(Context context) throws Exception {

        Command command = getCommand(context);
        if (command != null) {
            boolean result = (command.execute(context));
            if (isIgnoreExecuteResult()) {
                return false;
            }
            return result;
        } else {
            return (false);
        }

    }


    /**
     * <p>If the executed command was itself a {@link Filter}, call the
     * <code>postprocess()</code> method of that {@link Filter} as well.</p>
     *
     * @param context The context for this request
     * @param exception Any <code>Exception</code> thrown by command execution
     *
     * @return the result of executing the <code>postprocess</code> method
     * of the looked-up command, unless <code>ignorePostprocessResult</code> is
     * <code>true</code>.  If no command is found, return <code>false</code>,
     * unless the <code>optional</code> property is <code>false</code>, in which
     * case <code>IllegalArgumentException</code> will be thrown.
     */
    public boolean postprocess(Context context, Exception exception) {

        Command command = getCommand(context);
        if (command != null) {
            if (command instanceof Filter) {
                boolean result = (((Filter) command).postprocess(context, exception));
                if (isIgnorePostprocessResult()) {
                    return false;
                }
                return result;
            }
        }
        return (false);

    }


    // --------------------------------------------------------- Private Methods


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
        CatalogFactory lookupFactory = this.catalogFactory;
        if (lookupFactory == null) {
            lookupFactory = CatalogFactory.getInstance();
        }

        String catalogName = getCatalogName();
        Catalog catalog = null;
        if (catalogName == null) {
            // use default catalog
            catalog = lookupFactory.getCatalog();
        } else {
            catalog = lookupFactory.getCatalog(catalogName);
        }
        if (catalog == null) {
            if (catalogName == null) {
                throw new IllegalArgumentException
                    ("Cannot find default catalog");
            } else {
                throw new IllegalArgumentException
                    ("Cannot find catalog '" + catalogName + "'");
            }
        }

        return catalog;
    }

    /**
     * <p>Return the {@link Command} instance to be delegated to.</p>
     *
     * @param context {@link Context} for this request
     * @return The looked-up Command.
     * @exception IllegalArgumentException if no such {@link Command}
     *  can be found and the <code>optional</code> property is set
     *  to <code>false</code>
     */
    protected Command getCommand(Context context) {

        Catalog catalog = getCatalog(context);

        Command command = null;
        String name = getCommandName(context);
        if (name != null) {
            command = catalog.getCommand(name);
            if ((command == null) && !isOptional()) {
                if (catalogName == null) {
                    throw new IllegalArgumentException
                        ("Cannot find command '" + name
                         + "' in default catalog");
                } else {
                    throw new IllegalArgumentException
                        ("Cannot find command '" + name
                         + "' in catalog '" + catalogName + "'");
                }
            }
            return (command);
        } else {
            throw new IllegalArgumentException("No command name");
        }

    }

    /**
     * <p>Return the name of the {@link Command} instance to be delegated to.</p>
     *
     * @param context {@link Context} for this request
     * @return The name of the {@link Command} instance
     *
     * @since Chain 1.2
     */
    protected String getCommandName(Context context) {

        String name = getName();
        if (name == null) {
            name = (String) context.get(getNameKey());
        }
        return name;

    }

}
