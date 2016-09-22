package com.apps.mustango.loginmekashron;

import org.ksoap2.SoapEnvelope;

import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by mustango on 22.09.2016.
 */
public class CustomSoapEnvelop extends SoapEnvelope {
    public final String env="http://www.w3.org/2003/05/soap-envelope";
    public final String enc="http://www.w3.org/2003/05/soap-encoding";
    public final String ns1="urn:General.Intf-IGeneral";
    public final String encodingStyle="http://www.w3.org/2003/05/soap-encoding";
    //  public  boolean dotNet;
    public CustomSoapEnvelop(int version) {
        super(version);
    }

    // private void CustomSoapSerializationEnvelope(){}
    public void write(XmlSerializer writer) throws IOException {


        writer.setPrefix("env", env); // <-- changed line
        writer.setPrefix("ns1", ns1 );
        writer.setPrefix("xsd", xsd);
        writer.setPrefix("xsi", xsi);
        writer.setPrefix("enc",enc);
        writer.startTag(env, "Envelope");
        writer.startTag(env, "Body");
        // writer.startTag(ns1,"Login");
        // writer.attribute(this.env, "encodingStyle", "http://www.w3.org/2003/05/soap-encoding");
        //  writeBody(writer);
        writeBody(writer);
        //  writer.endTag(ns1,"Login");
        // this.writeElement(writer, this.bodyOut, (PropertyInfo)null, qName[3]);
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }

    public void writeBody(XmlSerializer writer) throws IOException {
        if (encodingStyle != null) {
            writer.attribute(env, "encodingStyle", encodingStyle);
        }
       ((Node) bodyOut).write(writer);
    }

}
