/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.rampart;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.modules.Module;
import org.apache.neethi.Assertion;
import org.apache.neethi.Policy;
import org.apache.rampart.policy.model.RampartConfig;
import org.apache.ws.secpolicy.SP11Constants;
import org.apache.ws.secpolicy.SP12Constants;

public class Rampart implements Module /* , ModulePolicyExtension */  {

    public void init(ConfigurationContext configContext, AxisModule module)
            throws AxisFault {
    }

    public void engageNotify(AxisDescription axisDescription) throws AxisFault {
        //Nothing to do here, since RampartMessageData will pick up the 
        //effective policy from the message context 
    }

    public void shutdown(ConfigurationContext configurationContext) throws AxisFault {
        // at the moment, nothing needs to be done ..
    }

//    public PolicyExtension getPolicyExtension() {
//        throw new UnsupportedOperationException("TODO");
//    }

    public void applyPolicy(Policy policy, AxisDescription axisDescription) throws AxisFault {
        //Do not do anything
    }

    public boolean canSupportAssertion(Assertion assertion) {
        if(assertion == null) {
            return false;
        }

        String ns = assertion.getName().getNamespaceURI();

        // Rampart module can handle WS-SecurityPolicy 1.1 & 1.2 and RampartConfig assertions 
        if (SP11Constants.SP_NS.equals(ns) || SP12Constants.SP_NS.equals(ns) || RampartConfig.NS.equals(ns)) {
            return true;
        } else {
            return false;
        }

    }
}
