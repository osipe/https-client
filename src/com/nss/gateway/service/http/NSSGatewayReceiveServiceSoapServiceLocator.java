/**
 * NSSGatewayReceiveServiceSoapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nss.gateway.service.http;

public class NSSGatewayReceiveServiceSoapServiceLocator extends org.apache.axis.client.Service implements com.nss.gateway.service.http.NSSGatewayReceiveServiceSoapService {

    public NSSGatewayReceiveServiceSoapServiceLocator() {
    }


    public NSSGatewayReceiveServiceSoapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NSSGatewayReceiveServiceSoapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Plugin_NSSGateway_NSSGatewayReceiveService
    private java.lang.String Plugin_NSSGateway_NSSGatewayReceiveService_address = "http://localhost:8081/gw-portlet/api/axis/Plugin_NSSGateway_NSSGatewayReceiveService";

    public java.lang.String getPlugin_NSSGateway_NSSGatewayReceiveServiceAddress() {
        return Plugin_NSSGateway_NSSGatewayReceiveService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String Plugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName = "Plugin_NSSGateway_NSSGatewayReceiveService";

    public java.lang.String getPlugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName() {
        return Plugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName;
    }

    public void setPlugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName(java.lang.String name) {
        Plugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName = name;
    }

    public com.nss.gateway.service.http.NSSGatewayReceiveServiceSoap getPlugin_NSSGateway_NSSGatewayReceiveService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Plugin_NSSGateway_NSSGatewayReceiveService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPlugin_NSSGateway_NSSGatewayReceiveService(endpoint);
    }

    public com.nss.gateway.service.http.NSSGatewayReceiveServiceSoap getPlugin_NSSGateway_NSSGatewayReceiveService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.nss.gateway.service.http.NSSGatewayReceiveServiceSoapBindingStub _stub = new com.nss.gateway.service.http.NSSGatewayReceiveServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getPlugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPlugin_NSSGateway_NSSGatewayReceiveServiceEndpointAddress(java.lang.String address) {
        Plugin_NSSGateway_NSSGatewayReceiveService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.nss.gateway.service.http.NSSGatewayReceiveServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.nss.gateway.service.http.NSSGatewayReceiveServiceSoapBindingStub _stub = new com.nss.gateway.service.http.NSSGatewayReceiveServiceSoapBindingStub(new java.net.URL(Plugin_NSSGateway_NSSGatewayReceiveService_address), this);
                _stub.setPortName(getPlugin_NSSGateway_NSSGatewayReceiveServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Plugin_NSSGateway_NSSGatewayReceiveService".equals(inputPortName)) {
            return getPlugin_NSSGateway_NSSGatewayReceiveService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:http.service.gateway.nss.com", "NSSGatewayReceiveServiceSoapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:http.service.gateway.nss.com", "Plugin_NSSGateway_NSSGatewayReceiveService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Plugin_NSSGateway_NSSGatewayReceiveService".equals(portName)) {
            setPlugin_NSSGateway_NSSGatewayReceiveServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
