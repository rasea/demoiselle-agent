package br.gov.frameworkdemoiselle.internal.producer;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;

import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.implementation.ManagementService;
import br.gov.frameworkdemoiselle.internal.proxy.ManagementV1;

public class ManagementProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaAgentConfig config;

	@Produces
	@ApplicationScoped
	public ManagementV1 create() {
		final ManagementService service = new ManagementService(config);
		final ManagementV1 port = service.getManagementPortV1();
		final BindingProvider bindingProvider = (BindingProvider) port;

		final Map<String, Object> requestContext = bindingProvider.getRequestContext();

		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getEndpointAddress("Management_v1"));

		return port;
	}

	private String getEndpointAddress(final String endPoint) {
		return config.getProtocol().toString().toLowerCase() + "://" + config.getHost() + ":" + config.getPort()
				+ config.getContext() + "/services/" + endPoint;
	}
}
