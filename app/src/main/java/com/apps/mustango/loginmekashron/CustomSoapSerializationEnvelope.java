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
        writer.setPrefix("i", xsi);
        writer.setPrefix("d", xsd);
        writer.setPrefix("c", enc);
        writer.setPrefix("soap", env); // <-- changed line
        writer.startTag(env, "Envelope");
        writer.startTag(env, "Header");
        writeHeader(writer);
        writer.endTag(env, "Header");
        writer.startTag(env, "Body");
        writeBody(writer);
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }

}
