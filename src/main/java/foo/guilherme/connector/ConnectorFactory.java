package foo.guilherme.connector;

import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.provisioning.AbstractOutboundProvisioningConnector;
import org.wso2.carbon.identity.provisioning.AbstractProvisioningConnectorFactory;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningException;

import java.util.ArrayList;
import java.util.List;
/**
* Connector
*/
public class ConnectorFactory extends AbstractProvisioningConnectorFactory {
    private static final String CONNECTOR_TYPE = "Firebase";
    @Override
    protected AbstractOutboundProvisioningConnector buildConnector(Property[] properties)
            throws IdentityProvisioningException {
        Connector conn = new Connector();
        conn.init(properties);
        return conn;
    }

    @Override
    public String getConnectorType() {
        return CONNECTOR_TYPE;
    }

    @Override
    public List<Property> getConfigurationProperties() {
        Property clientId = new Property();
        clientId.setName(ConnectorConstants.CLIENT_ID);
        clientId.setDisplayName("Client ID");
        clientId.setDisplayOrder(1);
        clientId.setRequired(true);

        Property clientSecret = new Property();
        clientSecret.setName(ConnectorConstants.CLIENT_SECRET);
        clientSecret.setDisplayName("Client Secret");
        clientSecret.setDisplayOrder(2);
        clientSecret.setRequired(true);

        List<Property> properties = new ArrayList<Property>();
        properties.add(clientId);
        properties.add(clientSecret);
        return properties;
    }
}
