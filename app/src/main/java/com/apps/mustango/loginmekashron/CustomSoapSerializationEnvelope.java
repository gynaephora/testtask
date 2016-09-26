package com.apps.mustango.loginmekashron;

import org.ksoap2.SoapFault;
import org.ksoap2.SoapFault12;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.util.Vector;


/**
 * Created by mustango on 18.09.2016.
 */
public class CustomSoapSerializationEnvelope extends SoapSerializationEnvelope{

    public final String ns1="urn:General.Intf-IGeneral";
    public String encodingStyle="http://www.w3.org/2003/05/soap-encoding";
    public CustomSoapSerializationEnvelope(int version) {
        super(version);
    }


    @Override
    public void write(XmlSerializer writer) throws IOException {
        super.addAdornments=false;
    //   super.dotNet=false;
        writer.setPrefix("env",env); // <-- changed line
        writer.setPrefix("ns1", ns1 );
        writer.setPrefix("xsd",xsd);
        writer.setPrefix("xsi",xsi);
        writer.setPrefix("enc",enc);

        writer.startTag(env, "Envelope");
        writer.startTag(env, "Body");
      //  writer.startTag(ns1, "Login");
       // writer.attribute(this.env, "encodingStyle", this.encodingStyle);
         writeBody(writer);
       // writer.attribute(this.env, "encodingStyle", this.encodingStyle);
       /* writer.startTag("","UserName");
        writer.attribute(xsi, "type", "xsd:string");
        writer.text("");
        writer.endTag("","UserName");
      /*  writer.attribute(xsi, "encodingStyle", "xsd:string");
        writer.attribute(xsi, "encodingStyle", "xsd:string");

        writer.startTag("","password");
        writer.attribute(xsi, "type", "xsd:string");
        writer.text("");
        writer.endTag("","password");
        writer.startTag("","IP");
        writer.attribute(xsi, "type", "xsd:string");
        writer.text("");
        writer.endTag("","IP");*/
      //  writer.endTag(ns1, "Login");
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }




}
