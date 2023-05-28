package foo.guilherme.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.provisioning.AbstractOutboundProvisioningConnector;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;
import org.wso2.carbon.identity.provisioning.ProvisionedIdentifier;
import org.wso2.carbon.identity.provisioning.ProvisioningEntity;

import java.util.Properties;
import java.util.UUID;

/**
 * Connector
 */
public class Connector extends AbstractOutboundProvisioningConnector {
    Logger log = LogManager.getLogger(Connector.class.getName());
    private ConnectorConfig configs;
    @Override
    public void init(Property[] properties) throws IdentityProvisioningException {
        try {
            Properties configs = new Properties();
            log.info("Reading configuration for custom provisioner connector");
            if (properties != null) {
                for (Property prop : properties) {
                    configs.put(prop.getName(), prop.getValue());
                }
            }

            this.configs = new ConnectorConfig(configs);
        } catch (Exception e) {
            log.error("Something went wrong", e);
        }
    }

    @Override
    public ProvisionedIdentifier provision(ProvisioningEntity provisioningEntity) throws IdentityProvisioningException {
        String provisionedId = UUID.randomUUID().toString();
        ProvisionedIdentifier id = new ProvisionedIdentifier();
        if (provisioningEntity == null) {
            return null;
        }
        log.info(
                "Start provisioning with clientId=" + configs.getValue(ConnectorConstants.CLIENT_ID)
                + " and clientSecret=" + configs.getValue(ConnectorConstants.CLIENT_SECRET)
        );
        id.setIdentifier(provisionedId);
        return id;
    }
}
