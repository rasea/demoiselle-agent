package br.gov.frameworkdemoiselle.internal.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.config.ApplicationConfig;
import br.gov.frameworkdemoiselle.exception.ConfigurationException;
import br.gov.frameworkdemoiselle.exception.RaseaException;
import br.gov.frameworkdemoiselle.internal.proxy.AccessControlV1;
import br.gov.frameworkdemoiselle.internal.proxy.ApplicationNameRequest;
import br.gov.frameworkdemoiselle.internal.proxy.ApplicationRequest;
import br.gov.frameworkdemoiselle.internal.proxy.ApplicationType;
import br.gov.frameworkdemoiselle.internal.proxy.GrantPermissionRequest;
import br.gov.frameworkdemoiselle.internal.proxy.MaintenanceV1;
import br.gov.frameworkdemoiselle.internal.proxy.ManagementV1;
import br.gov.frameworkdemoiselle.internal.proxy.OperationNameRequest;
import br.gov.frameworkdemoiselle.internal.proxy.OperationRequest;
import br.gov.frameworkdemoiselle.internal.proxy.OperationType;
import br.gov.frameworkdemoiselle.internal.proxy.OperationsResponse;
import br.gov.frameworkdemoiselle.internal.proxy.PermissionRequest;
import br.gov.frameworkdemoiselle.internal.proxy.PermissionType;
import br.gov.frameworkdemoiselle.internal.proxy.PermissionsResponse;
import br.gov.frameworkdemoiselle.internal.proxy.ResourceNameRequest;
import br.gov.frameworkdemoiselle.internal.proxy.ResourceRequest;
import br.gov.frameworkdemoiselle.internal.proxy.ResourceType;
import br.gov.frameworkdemoiselle.internal.proxy.RoleRequest;
import br.gov.frameworkdemoiselle.internal.proxy.RoleType;
import br.gov.frameworkdemoiselle.internal.proxy.RolesResponse;
import br.gov.frameworkdemoiselle.internal.proxy.SimpleUserNameRequest;
import br.gov.frameworkdemoiselle.internal.proxy.UserNameRequest;
import br.gov.frameworkdemoiselle.internal.proxy.UserRoleRequest;
import br.gov.frameworkdemoiselle.internal.proxy.UserType;
import br.gov.frameworkdemoiselle.security.Application;
import br.gov.frameworkdemoiselle.security.Operation;
import br.gov.frameworkdemoiselle.security.Permission;
import br.gov.frameworkdemoiselle.security.RaseaContext;
import br.gov.frameworkdemoiselle.security.Resource;
import br.gov.frameworkdemoiselle.security.Role;
import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
public class RaseaContextImpl implements RaseaContext {

	private static final long serialVersionUID = 1L;

	@Inject
	private ApplicationConfig appConfig;

	@Inject
	private AccessControlV1 accessControlPort;

	@Inject
	private ManagementV1 managementPort;

	@Inject
	private MaintenanceV1 maintenancePort;

	@Inject
	private RaseaCredentialImpl credential;

	private void handleException(Exception cause) throws RaseaException {
		throw new ConfigurationException(cause.getMessage(), cause);
	}

	@Override
	public boolean authenticate() throws RaseaException {
		boolean result = false;

		try {
			result = accessControlPort.authenticate(credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public List<Permission> getPermissions(String username) throws RaseaException {
		
		List<Permission> result = new ArrayList<Permission>();

		try {
			UserNameRequest request = new UserNameRequest();
			request.setUsername(username);
			request.setApplicationName(appConfig.getName());

			PermissionsResponse response = accessControlPort.userPermissions(request, credential.getDelegate());

			Resource resource;
			Operation operation;
			for (PermissionType type : response.getPermission()) {
				resource = new Resource(type.getResourceName());
				operation = new Operation(type.getOperationName());

				result.add(new Permission(resource, operation));
			}
		} catch (Exception ex) {
			handleException(ex);			
		}

		return result;
	}

	public Resource getResource(String name) throws RaseaException {
		Resource result = null;

		ResourceNameRequest request = new ResourceNameRequest();
		request.setApplicationName(appConfig.getName());
		request.setResourceName(name);

		try {
			ResourceType response = maintenancePort.resourceDetail(request, credential.getDelegate());

			if (response != null) {
				result = new Resource(response.getName(), response.getDisplayName());
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addResource(Resource resource) throws RaseaException {
		try {
			ResourceType type = new ResourceType();
			type.setName(resource.getName());
			type.setDisplayName(resource.getDescription());

			ResourceRequest request = new ResourceRequest();
			request.setResource(type);
			request.setApplicationName(this.appConfig.getName());

			maintenancePort.addResource(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public Application getApplication(String name) throws RaseaException {
		Application result = null;

		try {
			ApplicationNameRequest request = new ApplicationNameRequest();
			request.setApplicationName(name);

			ApplicationType response = maintenancePort.applicationDetail(request, credential.getDelegate());
			result = new Application(response.getName(), response.getDisplayName());

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addApplication(Application application) throws RaseaException {
		ApplicationType type = new ApplicationType();
		type.setName(application.getName());
		type.setDisplayName(application.getDescription());

		try {
			ApplicationRequest request = new ApplicationRequest();
			request.setApplication(type);

			maintenancePort.addApplication(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public void assignOwner(String username, Application application) throws RaseaException {
		try {
			UserNameRequest request = new UserNameRequest();
			request.setApplicationName(application.getName());
			request.setUsername(username);
			maintenancePort.assignOwner(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public Operation getOperation(String name) throws RaseaException {
		Operation result = null;

		OperationNameRequest request = new OperationNameRequest();
		request.setApplicationName(appConfig.getName());
		request.setOperationName(name);

		try {
			OperationType response = maintenancePort.operationDetail(request, credential.getDelegate());

			if (response != null) {
				result = new Operation(response.getName());
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addOperation(Operation operation) throws RaseaException {
		OperationType type = new OperationType();
		type.setName(operation.getName());
		type.setDisplayName(operation.getName());

		OperationRequest request = new OperationRequest();
		request.setApplicationName(appConfig.getName());
		request.setOperation(type);

		try {
			maintenancePort.addOperation(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public List<Operation> getOperations(Resource resource, Application application) throws RaseaException {
		List<Operation> result = new ArrayList<Operation>();

		ResourceNameRequest request = new ResourceNameRequest();
		request.setApplicationName(application.getName());
		request.setResourceName(resource.getName());

		try {
			OperationsResponse response = maintenancePort.resourceOperations(request, credential.getDelegate());

			if (response.getOperation() != null) {
				for (OperationType type : response.getOperation()) {
					result.add(new Operation(type.getName()));
				}
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public void addPermission(Permission permission) throws RaseaException {
		PermissionType type = new PermissionType();

		type.setOperationName(permission.getOperation().getName());
		type.setResourceName(permission.getResource().getName());

		PermissionRequest request = new PermissionRequest();
		request.setApplicationName(appConfig.getName());
		request.setPermission(type);

		try {
			maintenancePort.addPermission(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public void grantPermission(Permission permission, Role role) throws RaseaException {
		PermissionType type = new PermissionType();
		type.setResourceName(permission.getResource().getName());
		type.setOperationName(permission.getOperation().getName());

		GrantPermissionRequest request = new GrantPermissionRequest();
		request.setApplicationName(appConfig.getName());
		request.setRoleName(role.getName());
		request.setPermission(type);
		request.setAllowed(true);

		try {
			managementPort.grantPermission(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	@Override
	public User getUser(String name) throws RaseaException {
		User result = null;

		try {
			SimpleUserNameRequest request = new SimpleUserNameRequest();
			request.setUsername(name);

			UserType response = accessControlPort.userDetail(request, credential.getDelegate());
			result = new UserImpl(response);

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;
	}

	public List<Role> getRoles(String username, Application application) throws RaseaException {
		List<Role> result = new ArrayList<Role>();

		UserNameRequest request = new UserNameRequest();
		request.setUsername(username);
		request.setApplicationName(application.getName());

		try {
			RolesResponse response = accessControlPort.assignedRoles(request, credential.getDelegate());

			if (response.getRole() != null) {
				for (RoleType type : response.getRole()) {
					result.add(new Role(type.getName(), type.getDisplayName()));
				}
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;

	}

	public List<Role> getRoles(Application application) throws RaseaException {
		List<Role> result = new ArrayList<Role>();

		ApplicationNameRequest request = new ApplicationNameRequest();
		request.setApplicationName(application.getName());

		try {
			RolesResponse response = accessControlPort.listRoles(request, credential.getDelegate());

			if (response.getRole() != null) {
				for (RoleType type : response.getRole()) {
					result.add(new Role(type.getName(), type.getDisplayName()));
				}
			}

		} catch (Exception cause) {
			handleException(cause);
		}

		return result;

	}

	public void addRole(Role role, Application application) throws RaseaException {
		RoleType type = new RoleType();
		type.setName(role.getName());
		type.setDisplayName(role.getDescription());

		RoleRequest request = new RoleRequest();
		request.setApplicationName(application.getName());
		request.setRole(type);

		try {
			managementPort.addRole(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}

	public void assignUser(String username, Role role, Application application) throws RaseaException {
		UserRoleRequest request = new UserRoleRequest();
		request.setApplicationName(application.getName());
		request.setUsername(username);
		request.setRoleName(role.getName());

		try {
			managementPort.assignUser(request, credential.getDelegate());
		} catch (Exception cause) {
			handleException(cause);
		}
	}
}
