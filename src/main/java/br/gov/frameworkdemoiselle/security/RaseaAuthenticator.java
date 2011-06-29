/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 * 
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 * 
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
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
