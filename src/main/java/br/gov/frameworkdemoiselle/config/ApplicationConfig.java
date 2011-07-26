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
package br.gov.frameworkdemoiselle.config;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

//@SessionScoped
@Configuration(prefix = "frameworkdemoiselle.application")
public class ApplicationConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("name")
	private String name = "default-app-name";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
