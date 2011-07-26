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
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.security.Permission;
import br.gov.frameworkdemoiselle.security.RaseaContext;

@Mode
@SessionScoped
public class DefaultRaseaAuthorizer implements Authorizer {

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
