package util;


import firo.utils.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AnBQ
 */
public class SMS {
	public static void main(String[] args) throws Exception {
            SOAPMessage soapRequest = createSOAPRequest("soxaydung_api", "M@tKh@u793!@#", "SXD-TPHCM", "1", "84967635937", "anq");
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            System.setProperty("java.net.useSystemProxies", "true");
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            SOAPMessage soapResponse = soapConnection.call(soapRequest, "http://221.132.39.104:8083/bsmsws.asmx");
             File fXmlFile = File.createTempFile("out", ".xml");
            FileOutputStream fos = new FileOutputStream(fXmlFile);
            soapResponse.writeTo(fos);
            FileInputStream fisTargetFile = new FileInputStream(fXmlFile);
            String targetFileStr = IOUtils.toString(fisTargetFile);
            String result = "";
            if(targetFileStr.contains("<SendBrandSmsResult>") && targetFileStr.contains("</SendBrandSmsResult>")){
                    result = targetFileStr.substring(targetFileStr.indexOf("<SendBrandSmsResult>")+"<SendBrandSmsResult>".length(), targetFileStr.indexOf("</SendBrandSmsResult>"));
            } else {
                    System.out.println("GuiSMS: Cau hinh service khong dung");
            }

            // print SOAP Response
            System.out.println("Response SOAP Message:" + result);
    //        soapResponse.writeTo(System.out);

            soapConnection.close();
	}
        private static SOAPMessage createSOAPRequest(String username, String password, String brandName, String type, String numberPhone, String message) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", "http://tempuri.org/");

        // nhung cai khac giua nguyen
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("SendBrandSms", "example");

        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("username", "example");
        soapBodyElem1.addTextNode(username);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "example");
        soapBodyElem2.addTextNode(password);
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("phonenumber", "example");
        soapBodyElem3.addTextNode(numberPhone);
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("message", "example");
        soapBodyElem4.addTextNode(message);
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("brandname", "example");
        soapBodyElem5.addTextNode(brandName);
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("loaitin", "example");
        soapBodyElem6.addTextNode(type);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "http://tempuri.org/" + "SendBrandSms");

        soapMessage.saveChanges();

        return soapMessage;
    }
}
