package br.gov.frameworkdemoiselle.internal.implementation;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.proxy.AccessControlV1;

public class AccessControlService extends Service {

	public AccessControlService(RaseaAgentConfig config) {
		super(config.getWsdlLocation("AccessControl_v1"), new QName("http://rasea.org/ps/wsdl/" + "AccessControl_v1",
				"AccessControlService_v1"));
	}

	public AccessControlV1 getAccessControlPortV1() {
		return super.getPort(new QName("http://rasea.org/ps/wsdl/AccessControl_v1", "AccessControlPort_v1"),
				AccessControlV1.class);
	}
}
