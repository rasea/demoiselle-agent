package br.gov.frameworkdemoiselle.internal.implementation;

import static br.gov.frameworkdemoiselle.config.ModeType.DEFAULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.event.AfterLoginSuccessful;
import br.gov.frameworkdemoiselle.exception.RaseaException;
import br.gov.frameworkdemoiselle.security.Authorizator;
import br.gov.frameworkdemoiselle.security.Permission;
import br.gov.frameworkdemoiselle.security.RaseaContext;

@Mode
@SessionScoped
public class DefaultRaseaAuthorizator implements Authorizator {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaContext service;

	@Inject
	private RaseaAgentConfig config;

	@Inject
	private RaseaCredentialImpl credential;

	private Map<String, Map<String, String>> permissions;

	public void loadPermissions(@Observes AfterLoginSuccessful event) {
		try {
			if (config.getMode() == DEFAULT) {
				this.permissions = new HashMap<String, Map<String, String>>();
				List<Permission> perm = service.getPermissions(credential.getUsername());

				if (perm != null) {
					String resource;
					String operation;

					for (final Permission permission : perm) {
						resource = permission.getResource().getName();
						operation = permission.getOperation().getName();

						addPermission(resource, operation);
					}
				}
			}
		} catch (RaseaException e) {
			// TODO Colocar uma mensagem amigável para o programador saber o que ocorreu.
			e.printStackTrace();
		}
	}

	@Override
	public boolean hasRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		return this.permissions.containsKey(resource)
				&& this.permissions.get(resource.toString()).containsKey(operation);
	}

	private void addPermission(final String resource, final String operation) {
		if (this.permissions.containsKey(resource)) {
			final Map<String, String> actions = this.permissions.get(resource);

			if (!actions.containsKey(operation)) {
				actions.put(operation, operation);
			}
		} else {
			final Map<String, String> actions = new HashMap<String, String>();
			actions.put(operation, operation);

			this.permissions.put(resource, actions);
		}
	}
}
