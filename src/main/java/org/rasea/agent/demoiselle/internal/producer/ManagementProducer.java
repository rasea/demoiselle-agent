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
package org.rasea.agent.demoiselle.internal.producer;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.xml.ws.BindingProvider;

import org.rasea.agent.demoiselle.config.RaseaAgentConfig;
import org.rasea.agent.demoiselle.internal.implementation.ManagementService;
import org.rasea.agent.demoiselle.internal.proxy.ManagementV1;


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
