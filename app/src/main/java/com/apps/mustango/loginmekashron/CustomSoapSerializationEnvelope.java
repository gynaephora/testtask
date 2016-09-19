package com.apps.mustango.loginmekashron;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

/**
 * Created by mustango on 18.09.2016.
 */
public class CustomSoapSerializationEnvelope extends SoapSerializationEnvelope{

    public CustomSoapSerializationEnvelope(int version) {
        super(version);
    }

    // private void CustomSoapSerializationEnvelope(){}
    @Override
    public void write(XmlSerializer writer) throws IOException {
        writer.setPrefix("env", env); // <-- changed line

       // writer.setPrefix("env","http://www.w3.org/2003/05/soap-envelope");
        writer.setPrefix("ns1", "urn:General.Intf-IGeneral" );
        // writer.setPrefix("enc", enc);
        writer.setPrefix("xsd", xsd);
        writer.setPrefix("xsi", xsi);
      //  writer.setPrefix("env","http://www.w3.org/2003/05/soap-envelope");
        writer.setPrefix("enc","http://www.w3.org/2003/05/soap-encoding");
        writer.startTag(env, "Envelope");
       // writer.startTag(env, "Header");
       // writeHeader(writer);
      //  writer.endTag(env, "Header");
        writer.startTag(env, "Body");
        //writer.setPrefix();
       // writer.startTag("ns1","Login");
        writeBody(writer);
       // writer.endTag("ns1","Login");
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }

}
