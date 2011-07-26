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

import java.io.Serializable;
import java.util.List;

import br.gov.frameworkdemoiselle.exception.RaseaException;

public interface RaseaContext extends Serializable {

	boolean authenticate() throws RaseaException;

	List<Permission> getPermissions(String username) throws RaseaException;

	User getUser(String name) throws RaseaException;
	
	Resource getResource(String name) throws RaseaException;

	void addResource(Resource resource) throws RaseaException;

	Application getApplication(String name) throws RaseaException;

	void addApplication(Application application) throws RaseaException;

	void assignOwner(String username, Application application) throws RaseaException;

	Operation getOperation(String name) throws RaseaException;

	void addOperation(Operation operation) throws RaseaException;

	List<Operation> getOperations(Resource resource, Application application) throws RaseaException;

	void addPermission(Permission permission) throws RaseaException;

	void grantPermission(Permission permission, Role role) throws RaseaException;

	List<Role> getRoles(String username, Application application) throws RaseaException;

	List<Role> getRoles(Application application) throws RaseaException;

	void addRole(Role role, Application application) throws RaseaException;

	void assignUser(String username, Role role, Application application) throws RaseaException;

}
