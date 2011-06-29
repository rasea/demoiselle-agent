package br.gov.frameworkdemoiselle.internal.implementation;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.proxy.MaintenanceV1;

public class MaintenanceService extends Service {

	public MaintenanceService(RaseaAgentConfig config) {
		super(config.getWsdlLocation("Maintenance_v1"), new QName("http://rasea.org/ps/wsdl/" + "Maintenance_v1",
				"MaintenanceService_v1"));
	}

	public MaintenanceV1 getMaintenancePortV1() {
		return super.getPort(new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "MaintenancePort_v1"),
				MaintenanceV1.class);
	}

}
