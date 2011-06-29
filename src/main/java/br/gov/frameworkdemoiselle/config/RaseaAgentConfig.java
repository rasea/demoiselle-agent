/*
 * Demoiselle Framework Copyright (C) 2010 SERPRO
 * ---------------------------------------------------------------------------- This file is part of Demoiselle
 * Framework. Demoiselle Framework is free software; you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License version 3 as published by the Free Software Foundation. This program is distributed in
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a
 * copy of the GNU Lesser General Public License version 3 along with this program; if not, see
 * <http://www.gnu.org/licenses/> or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA. ---------------------------------------------------------------------------- Este arquivo
 * é parte do Framework Demoiselle. O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação do Software Livre (FSF). Este
 * programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA GARANTIA; sem uma garantia implícita de
 * ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português para
 * maiores detalhes. Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título "LICENCA.txt", junto com esse
 * programa. Se não, acesse <http://www.gnu.org/licenses/> ou escreva para a Fundação do Software Livre (FSF) Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.config;

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
