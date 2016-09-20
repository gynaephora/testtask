package com.apps.mustango.loginmekashron;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by mustango on 18.09.2016.
 */
public class CustomSoapSerializationEnvelope extends SoapSerializationEnvelope{

    public final String env="http://www.w3.org/2003/05/soap-envelope";
    public final String enc="http://www.w3.org/2003/05/soap-encoding";
    public final String ns1="urn:General.Intf-IGeneral";

    public final boolean dotNet=false;
    public CustomSoapSerializationEnvelope(int version) {
        super(version);
    }

    // private void CustomSoapSerializationEnvelope(){}
    @Override
    public void write(XmlSerializer writer) throws IOException {
        writer.setPrefix("env", env); // <-- changed line

       // writer.setPrefix("env","http://www.w3.org/2003/05/soap-envelope");
        writer.setPrefix("ns1", ns1 );
        // writer.setPrefix("enc", enc);
        writer.setPrefix("xsd", xsd);
        writer.setPrefix("xsi", xsi);

      //  writer.setPrefix("env","http://www.w3.org/2003/05/soap-envelope");
        writer.setPrefix("enc",enc);
        writer.startTag(env, "Envelope");
       // writer.startTag(env, "Header");
       // writeHeader(writer);
      //  writer.endTag(env, "Header");
        writer.startTag(env, "Body");
        //writer.setPrefix();
       // writer.startTag("ns1","Login");
       // writer.attribute((String)null, "id", qName[2] == null?"o0":(String)qName[2]);
        writer.startTag(ns1,"Login");
        writer.attribute(this.env, "encodingStyle", "http://www.w3.org/2003/05/soap-encoding");
       // writer.
        writeBody(writer);
        writer.endTag(ns1,"Login");
        //writer.attribute("ns1", env,enc);
       // writer.endTag("ns1","Login");
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }

}
