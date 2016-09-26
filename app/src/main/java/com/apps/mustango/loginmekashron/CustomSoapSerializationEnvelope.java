package com.apps.mustango.loginmekashron;

import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;



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
     // super.dotNet=false;
        writer.setPrefix("env",env); // <-- changed line
        writer.setPrefix("ns1", ns1 );
        writer.setPrefix("xsd",xsd);
        writer.setPrefix("xsi",xsi);
        writer.setPrefix("enc",enc);

        writer.startTag(env, "Envelope");
        writer.startTag(env, "Body");
        writeBody(writer);
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }
}
