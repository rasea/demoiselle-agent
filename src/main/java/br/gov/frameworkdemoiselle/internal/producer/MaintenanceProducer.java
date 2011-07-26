/*
 * Rasea Agent Demoiselle
 * 
 * Copyright (c) 2008, Rasea <http://rasea.org>. All rights reserved.
 *
 * Rasea Extensions is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, see <http://gnu.org/licenses>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 */
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
