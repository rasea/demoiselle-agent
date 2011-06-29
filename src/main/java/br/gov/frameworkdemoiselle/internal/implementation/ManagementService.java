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
