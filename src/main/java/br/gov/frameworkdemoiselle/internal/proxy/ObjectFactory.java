
package br.gov.frameworkdemoiselle.internal.proxy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.gov.frameworkdemoiselle.internal.proxy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AssignOwner_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "assignOwner");
    private final static QName _AddOperation_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "addOperation");
    private final static QName _OperationDetail_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "operationDetail");
    private final static QName _Operation_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "operation");
    private final static QName _AddPermission_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "addPermission");
    private final static QName _ResourceOperations_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "resourceOperations");
    private final static QName _DeassignOwner_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "deassignOwner");
    private final static QName _Operations_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "operations");
    private final static QName _DeleteApplication_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "deleteApplication");
    private final static QName _WebServiceFault_QNAME = new QName("http://rasea.org/http://rasea.org/ps/wsdl/WebServiceException_v1", "WebServiceFault");
    private final static QName _ResourceDetail_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "resourceDetail");
    private final static QName _DeleteResource_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "deleteResource");
    private final static QName _Application_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "application");
    private final static QName _DeleteOperation_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "deleteOperation");
    private final static QName _ApplicationDetail_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "applicationDetail");
    private final static QName _Arg1_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "arg1");
    private final static QName _DeletePermission_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "deletePermission");
    private final static QName _Resource_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "resource");
    private final static QName _AddResource_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "addResource");
    private final static QName _AddApplication_QNAME = new QName("http://rasea.org/ps/wsdl/Maintenance_v1", "addApplication");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.gov.frameworkdemoiselle.internal.proxy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WebServiceFaultBean }
     * 
     */
    public WebServiceFaultBean createWebServiceFaultBean() {
        return new WebServiceFaultBean();
    }

    /**
     * Create an instance of {@link OperationNameRequest }
     * 
     */
    public OperationNameRequest createOperationNameRequest() {
        return new OperationNameRequest();
    }

    /**
     * Create an instance of {@link ApplicationType }
     * 
     */
    public ApplicationType createApplicationType() {
        return new ApplicationType();
    }

    /**
     * Create an instance of {@link ResourceNameRequest }
     * 
     */
    public ResourceNameRequest createResourceNameRequest() {
        return new ResourceNameRequest();
    }

    /**
     * Create an instance of {@link Credentials }
     * 
     */
    public Credentials createCredentials() {
        return new Credentials();
    }

    /**
     * Create an instance of {@link ApplicationNameRequest }
     * 
     */
    public ApplicationNameRequest createApplicationNameRequest() {
        return new ApplicationNameRequest();
    }

    /**
     * Create an instance of {@link ResourceType }
     * 
     */
    public ResourceType createResourceType() {
        return new ResourceType();
    }

    /**
     * Create an instance of {@link PermissionRequest }
     * 
     */
    public PermissionRequest createPermissionRequest() {
        return new PermissionRequest();
    }

    /**
     * Create an instance of {@link ApplicationRequest }
     * 
     */
    public ApplicationRequest createApplicationRequest() {
        return new ApplicationRequest();
    }

    /**
     * Create an instance of {@link ResourceRequest }
     * 
     */
    public ResourceRequest createResourceRequest() {
        return new ResourceRequest();
    }

    /**
     * Create an instance of {@link OperationType }
     * 
     */
    public OperationType createOperationType() {
        return new OperationType();
    }

    /**
     * Create an instance of {@link OperationRequest }
     * 
     */
    public OperationRequest createOperationRequest() {
        return new OperationRequest();
    }

    /**
     * Create an instance of {@link UserNameRequest }
     * 
     */
    public UserNameRequest createUserNameRequest() {
        return new UserNameRequest();
    }

    /**
     * Create an instance of {@link OperationsResponse }
     * 
     */
    public OperationsResponse createOperationsResponse() {
        return new OperationsResponse();
    }

    /**
     * Create an instance of {@link PermissionType }
     * 
     */
    public PermissionType createPermissionType() {
        return new PermissionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "assignOwner")
    public JAXBElement<UserNameRequest> createAssignOwner(UserNameRequest value) {
        return new JAXBElement<UserNameRequest>(_AssignOwner_QNAME, UserNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "addOperation")
    public JAXBElement<OperationRequest> createAddOperation(OperationRequest value) {
        return new JAXBElement<OperationRequest>(_AddOperation_QNAME, OperationRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "operationDetail")
    public JAXBElement<OperationNameRequest> createOperationDetail(OperationNameRequest value) {
        return new JAXBElement<OperationNameRequest>(_OperationDetail_QNAME, OperationNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "operation")
    public JAXBElement<OperationType> createOperation(OperationType value) {
        return new JAXBElement<OperationType>(_Operation_QNAME, OperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "addPermission")
    public JAXBElement<PermissionRequest> createAddPermission(PermissionRequest value) {
        return new JAXBElement<PermissionRequest>(_AddPermission_QNAME, PermissionRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "resourceOperations")
    public JAXBElement<ResourceNameRequest> createResourceOperations(ResourceNameRequest value) {
        return new JAXBElement<ResourceNameRequest>(_ResourceOperations_QNAME, ResourceNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "deassignOwner")
    public JAXBElement<UserNameRequest> createDeassignOwner(UserNameRequest value) {
        return new JAXBElement<UserNameRequest>(_DeassignOwner_QNAME, UserNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "operations")
    public JAXBElement<OperationsResponse> createOperations(OperationsResponse value) {
        return new JAXBElement<OperationsResponse>(_Operations_QNAME, OperationsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "deleteApplication")
    public JAXBElement<ApplicationNameRequest> createDeleteApplication(ApplicationNameRequest value) {
        return new JAXBElement<ApplicationNameRequest>(_DeleteApplication_QNAME, ApplicationNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebServiceFaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/http://rasea.org/ps/wsdl/WebServiceException_v1", name = "WebServiceFault")
    public JAXBElement<WebServiceFaultBean> createWebServiceFault(WebServiceFaultBean value) {
        return new JAXBElement<WebServiceFaultBean>(_WebServiceFault_QNAME, WebServiceFaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "resourceDetail")
    public JAXBElement<ResourceNameRequest> createResourceDetail(ResourceNameRequest value) {
        return new JAXBElement<ResourceNameRequest>(_ResourceDetail_QNAME, ResourceNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "deleteResource")
    public JAXBElement<ResourceNameRequest> createDeleteResource(ResourceNameRequest value) {
        return new JAXBElement<ResourceNameRequest>(_DeleteResource_QNAME, ResourceNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "application")
    public JAXBElement<ApplicationType> createApplication(ApplicationType value) {
        return new JAXBElement<ApplicationType>(_Application_QNAME, ApplicationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "deleteOperation")
    public JAXBElement<OperationNameRequest> createDeleteOperation(OperationNameRequest value) {
        return new JAXBElement<OperationNameRequest>(_DeleteOperation_QNAME, OperationNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationNameRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "applicationDetail")
    public JAXBElement<ApplicationNameRequest> createApplicationDetail(ApplicationNameRequest value) {
        return new JAXBElement<ApplicationNameRequest>(_ApplicationDetail_QNAME, ApplicationNameRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Credentials }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "arg1")
    public JAXBElement<Credentials> createArg1(Credentials value) {
        return new JAXBElement<Credentials>(_Arg1_QNAME, Credentials.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissionRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "deletePermission")
    public JAXBElement<PermissionRequest> createDeletePermission(PermissionRequest value) {
        return new JAXBElement<PermissionRequest>(_DeletePermission_QNAME, PermissionRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "resource")
    public JAXBElement<ResourceType> createResource(ResourceType value) {
        return new JAXBElement<ResourceType>(_Resource_QNAME, ResourceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "addResource")
    public JAXBElement<ResourceRequest> createAddResource(ResourceRequest value) {
        return new JAXBElement<ResourceRequest>(_AddResource_QNAME, ResourceRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApplicationRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rasea.org/ps/wsdl/Maintenance_v1", name = "addApplication")
    public JAXBElement<ApplicationRequest> createAddApplication(ApplicationRequest value) {
        return new JAXBElement<ApplicationRequest>(_AddApplication_QNAME, ApplicationRequest.class, null, value);
    }

}
