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
package br.gov.frameworkdemoiselle.internal.producer;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Strategy;
import br.gov.frameworkdemoiselle.config.RaseaAgentConfig;
import br.gov.frameworkdemoiselle.internal.implementation.DefaultRaseaAuthorizer;
import br.gov.frameworkdemoiselle.internal.implementation.LoadRaseaAuthorizer;
import br.gov.frameworkdemoiselle.internal.implementation.MockRaseaAuthorizer;
import br.gov.frameworkdemoiselle.internal.implementation.Mode;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.util.Beans;

public class AuthorizerStrategyProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RaseaAgentConfig agentConfig;

	@Strategy
	@Produces
	@SuppressWarnings("serial")
	public Authorizer create() {
		Class<? extends Authorizer> authorizerClass;

		switch (agentConfig.getMode()) {
		case LOAD:
			authorizerClass = LoadRaseaAuthorizer.class;
			break;

		case MOCK:
			authorizerClass = MockRaseaAuthorizer.class;
			break;

		default:
			authorizerClass = DefaultRaseaAuthorizer.class;
		}

		return Beans.getReference(authorizerClass, new AnnotationLiteral<Mode>() {
		});
	}
}
