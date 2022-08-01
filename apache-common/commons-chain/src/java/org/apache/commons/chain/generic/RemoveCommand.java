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


import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;


/**
 * <p>Remove any context attribute stored under the <code>fromKey</code>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 480477 $ $Date: 2006-11-29 08:34:52 +0000 (Wed, 29 Nov 2006) $
 */

public class RemoveCommand implements Command {


    // -------------------------------------------------------------- Properties


    private String fromKey = null;


    /**
     * <p>Return the context attribute key for the attribute.</p>
     * @return The context attribute key.
     */
    public String getFromKey() {

    return (this.fromKey);

    }


    /**
     * <p>Set the context attribute key for the attribute.</p>
     *
     * @param fromKey The new key
     */
    public void setFromKey(String fromKey) {

    this.fromKey = fromKey;

    }


    // ---------------------------------------------------------- Filter Methods


    /**
     * <p>Copy the specified source attribute to the specified destination
     * attribute.</p>
     *
     * @param context {@link Context} in which we are operating
     *
     * @return <code>false</code> so that processing will continue
     * @throws Exception if and error occurs.
     */
    public boolean execute(Context context) throws Exception {

    context.remove(getFromKey());
    return (false);

    }


}
