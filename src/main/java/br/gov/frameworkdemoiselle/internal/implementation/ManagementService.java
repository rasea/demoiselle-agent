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
package br.gov.frameworkdemoiselle.internal.implementation;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.proxy.ManagementV1;

public class ManagementService extends Service {

	public ManagementService(RaseaAgentConfig config) {
		super(config.getWsdlLocation("Management_v1"), new QName("http://rasea.org/ps/wsdl/" + "Management_v1",
				"ManagementService_v1"));
	}

	public ManagementV1 getManagementPortV1() {
		return super.getPort(new QName("http://rasea.org/ps/wsdl/Management_v1", "ManagementPort_v1"),
				ManagementV1.class);
	}

}
