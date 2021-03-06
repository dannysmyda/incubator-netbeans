/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.libs.graaljs;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import org.graalvm.polyglot.Context;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.netbeans.api.scripting.Scripting;

public class GraalJSTest {
    @Test
    public void evaluateGraalJS() {
        Context ctx = Context.newBuilder("js").build();
        int fourtyTwo = ctx.eval("js", "6 * 7").asInt();
        assertEquals(42, fourtyTwo);
    }

    @Test
    public void firstJavaScriptEngineIsGraalJS() {
        ScriptEngineManager m = Scripting.createManager();
        StringBuilder sb = new StringBuilder();
        for (ScriptEngineFactory f : m.getEngineFactories()) {
            sb.append("\nf: ").append(f.getEngineName()).append(" ext: ").append(f.getMimeTypes());
        }
        ScriptEngine text = m.getEngineByMimeType("text/javascript");
        assertEquals(sb.toString(), "GraalVM:js", text.getFactory().getEngineName());
        
        ScriptEngine app = m.getEngineByMimeType("application/javascript");
        assertEquals(sb.toString(), "GraalVM:js", app.getFactory().getEngineName());
    }
}
