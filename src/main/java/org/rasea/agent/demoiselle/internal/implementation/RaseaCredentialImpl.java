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
package org.rasea.agent.demoiselle.internal.implementation;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.rasea.agent.demoiselle.internal.proxy.Credentials;
import org.rasea.agent.demoiselle.security.RaseaCredential;


@SessionScoped
@Named("credential")
public class RaseaCredentialImpl implements RaseaCredential {

	private static final long serialVersionUID = 1L;

	private final Credentials delegate;

	public RaseaCredentialImpl() {
		delegate = new Credentials();
	}

	public String getUsername() {
		return delegate.getUsername();
	}

	public void setUsername(String username) {
		delegate.setUsername(username);
	}

	public Credentials getDelegate() {
		return delegate;
	}

	public String getPassword() {
		return delegate.getPassword();
	}

	public void setPassword(String password) {
		delegate.setPassword(password);
	}
}
