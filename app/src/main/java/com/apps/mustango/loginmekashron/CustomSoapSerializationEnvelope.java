package com.apps.mustango.loginmekashron;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

/**
 * Created by mustango on 18.09.2016.
 */
public class CustomSoapSerializationEnvelope extends SoapSerializationEnvelope{

    public final String env="http://www.w3.org/2003/05/soap-envelope";
    public final String enc="http://www.w3.org/2003/05/soap-encoding";
    public final String ns1="urn:General.Intf-IGeneral";
    public final String encodingStyle="http://www.w3.org/2003/05/soap-encoding";
  //  public  boolean dotNet;
    public CustomSoapSerializationEnvelope(int version) {
        super(version);
    }

    // private void CustomSoapSerializationEnvelope(){}
    @Override
    public void write(XmlSerializer writer) throws IOException {
        super.addAdornments=false;
        super.dotNet=false;

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
/*
    public void writeBody(XmlSerializer writer) throws IOException {
        if (encodingStyle != null) {
            writer.attribute(env, "encodingStyle", encodingStyle);
        }
       // ((Node) bodyOut).write(writer);
    }
/*
    @Override
    public void writeBody(XmlSerializer writer) throws IOException{
      //  this.multiRef = new Vector();
      //  this.multiRef.addElement(this.bodyOut);
        Object[] qName = this.getInfo((Object)null, this.bodyOut);
     //   writer.startTag(this.dotNet?"":(String)qName[0], (String)qName[1]);
        writer.startTag(ns1,"Login");
        writer.attribute(this.env, "encodingStyle", "http://www.w3.org/2003/05/soap-encoding");
        //this.writeElement(writer, this.bodyOut, (PropertyInfo)null, qName[3]);
        //writer.endTag(this.dotNet?"":(String)qName[0], (String)qName[1]);
        writer.endTag(ns1,"Login");
    }*/
}
