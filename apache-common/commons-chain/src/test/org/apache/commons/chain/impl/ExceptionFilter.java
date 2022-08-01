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
package org.apache.commons.chain.impl;


import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;


/**
 * <p>Implementation of {@link Filter} that logs its identifier and
 * and throws an Exception.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 480477 $ $Date: 2006-11-29 08:34:52 +0000 (Wed, 29 Nov 2006) $
 */
public class ExceptionFilter extends ExceptionCommand implements Filter {


    // ------------------------------------------------------------- Constructor


    public ExceptionFilter() {
    this("", "");
    }


    // Construct an instance that will log the specified identifier
    public ExceptionFilter(String id1, String id2) {
        super(id1);
        this.id2 = id2;
    }


    // -------------------------------------------------------------- Properties

    protected String id2 = null;
    public String getId2() {
    return (this.id2);
    }
    public void setId2(String id2) {
    this.id2 = id2;
    }


    // --------------------------------------------------------- Command Methods


    // Postprocess command for this Filter
    public boolean postprocess(Context context, Exception exception) {
        log(context, id2);
        return (false);
    }


}
