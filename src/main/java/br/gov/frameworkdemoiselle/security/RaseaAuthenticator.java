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
package br.gov.frameworkdemoiselle.security;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.config.ModeType;
import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.exception.RaseaException;
import br.gov.frameworkdemoiselle.internal.implementation.RaseaCredentialImpl;

@Alternative
public class RaseaAuthenticator implements Authenticator {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaAgentConfig config;

	@Inject
	private Instance<RaseaContext> service;

	@Inject
	private RaseaCredentialImpl credential;

	@Override
	public boolean authenticate() {
		boolean result = false;

		try {
			if (config.getMode() == ModeType.MOCK) {
				result = true;
			} else {
				result = service.get().authenticate();
			}

		} catch (RaseaException e) {
			// TODO Colocar uma mensagem amigável para o programador saber o que ocorreu.
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void unAuthenticate() {
		credential.setUsername(null);
		credential.setPassword(null);
	}

	@Override
	public User getUser() {
		User user = null;

		try {
			if (config.getMode() == ModeType.MOCK) {
				user = new User() {

					private static final long serialVersionUID = 1L;

					@Override
					public String getId() {
						return "mockuser";
					}

					@Override
					public Object getAttribute(Object key) {
						return null;
					}

					@Override
					public void setAttribute(Object key, Object value) {
					}
				};
			} else {
				user = service.get().getUser(credential.getUsername());
			}
			

		} catch (RaseaException e) {
			// TODO Colocar uma mensagem amigável para o programador saber o que ocorreu.
			e.printStackTrace();
		}

		return user;
	}
}
