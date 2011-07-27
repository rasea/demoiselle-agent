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
package org.rasea.agent.demoiselle.config;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;
import br.gov.frameworkdemoiselle.exception.ConfigurationException;

@Configuration(resource = "rasea-agent")
public class RaseaAgentConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("protocol")
	private String protocol = "http";

	@Name("host")
	private String host = "localhost";

	@Name("port")
	private Integer port = null;

	@Name("context")
	private String context = "/rasea";

	@Name("mode")
	private String mode = "default";

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		Integer result = null;

		if (this.port == null) {
			if ("https".equals(protocol)) {
				result = 443;

			} else {
				result = 80;
			}
		} else {
			result = this.port;
		}

		return result;
	}

	public String getContext() {
		return context;
	}

	public ModeType getMode() {
		return ModeType.valueOf(mode.toUpperCase());
	}

	public URL getWsdlLocation(String endPoint) {
		String wsdlLocation = getProtocol().toString().toLowerCase() + "://" + getHost() + ":" + getPort()
				+ getContext() + "/services/" + endPoint + "?wsdl";

		URL url = null; 
		
		try {
			url = new URL(wsdlLocation);
		} catch (MalformedURLException cause) {
			// TODO colocar mensagem correta
			throw new ConfigurationException("colocar mensagem", cause);
		}
		
		return url;
	}
}
