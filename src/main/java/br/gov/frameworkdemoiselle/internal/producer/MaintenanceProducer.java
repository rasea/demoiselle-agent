package br.gov.frameworkdemoiselle.internal.producer;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;

import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.implementation.MaintenanceService;
import br.gov.frameworkdemoiselle.internal.proxy.MaintenanceV1;

public class MaintenanceProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaAgentConfig config;

	@Produces
	@ApplicationScoped
	public MaintenanceV1 create() {
		final MaintenanceService service = new MaintenanceService(config);
		final MaintenanceV1 port = service.getMaintenancePortV1();
		final BindingProvider bindingProvider = (BindingProvider) port;

		final Map<String, Object> requestContext = bindingProvider.getRequestContext();

		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.getEndpointAddress("Maintenance_v1"));

		return port;
	}

	private String getEndpointAddress(final String endPoint) {
		return config.getProtocol().toString().toLowerCase() + "://" + config.getHost() + ":" + config.getPort()
				+ config.getContext() + "/services/" + endPoint;
	}
}
