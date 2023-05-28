/*
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package foo.guilherme.connector.internal;

import foo.guilherme.connector.ConnectorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.identity.provisioning.AbstractProvisioningConnectorFactory;

/**
 * Service component to consume CarbonRuntime instance which has been registered as an OSGi service
 * by Carbon Kernel.
 *
 * @since 1.0-SNAPSHOT
 */
@Component(
        name = "foo.guilherme.connector.internal.ServiceComponent",
        immediate = true
)
public class ServiceComponent {

    Logger logger = LogManager.getLogger(ServiceComponent.class.getName());
    private ServiceRegistration serviceRegistration;

    /**
     * This is the activation method of ServiceComponent. This will be called when its references are
     * satisfied.
     *
     * @param bundleContext the bundle context instance of this bundle.
     * @throws Exception this will be thrown if an issue occurs while executing the activate method
     */
    @Activate
    protected void start(BundleContext bundleContext) throws Exception {
        try {
            // Register GreeterImpl instance as an OSGi service.
            serviceRegistration = bundleContext.registerService(
                    AbstractProvisioningConnectorFactory.class.getName(),
                    new ConnectorFactory(),
                    null);

            if (serviceRegistration != null) {
                logger.info("Registered service component");
            }
        } catch (Exception e) {
            logger.error("Error setting up Custom Provisioner", e);
        }
    }

    /**
     * This is the deactivation method of ServiceComponent. This will be called when this component
     * is being stopped or references are satisfied during runtime.
     *
     * @throws Exception this will be thrown if an issue occurs while executing the de-activate method
     */
    @Deactivate
    protected void stop() throws Exception {
        logger.info("Service Component is deactivated");

        // Unregister Greeter OSGi service
        serviceRegistration.unregister();
    }


}
