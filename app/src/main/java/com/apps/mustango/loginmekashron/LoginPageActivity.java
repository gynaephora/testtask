package com.apps.mustango.loginmekashron;

import android.graphics.Color;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import okhttp3.Call;
import okhttp3.Response;
import retrofit2.http.HTTP;


public class LoginPageActivity extends AppCompatActivity {

   // private static String SOAP_ACTION = "http://isapi.mekashron.com/soapclient/soapclient.php?URL=http://isapi.mekashron.com/StartAJob/General.dll%2Fwsdl%2FIGeneral";
   private static String SOAP_ACTION ="urn:General.Intf-IGeneral#Login";
   private static String NAMESPACE = "urn:General.Intf-IGeneral";
   private static String METHOD_NAME = "Login";
   private static String URL ="http://isapi.mekashron.com/StartAJob/General.dll/soap/IGeneral";
    RequestItemTask requestItemTask=null;
    HttpTransportSE androidHttpTransport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final  EditText loginField=(EditText) findViewById(R.id.editText);
        final EditText passwordField=(EditText) findViewById(R.id.editText2);
        Button loginButton=(Button) findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String[] par={loginField.getText().toString(),passwordField.getText().toString()};
                requestItemTask = (RequestItemTask) new RequestItemTask().execute(par);

            }
        });
    }
        private class RequestItemTask extends AsyncTask<String,Void, String> {


            protected String doInBackground(String... string) {
                String response = "";
                String username=string[0];
                String password=string[1];
                //  postRequest.PostRequest("http://isapi.mekashron.com/StartAJob/General.dll",json);
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    request.addProperty("UserName",username)
                            .addProperty("Password",password)
                            .addProperty("IP","192.168.1.1");


                    CustomSoapSerializationEnvelope envelope = new  CustomSoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    Log.i("request",envelope.toString());
                    //Needed to make the internet call
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                    androidHttpTransport.debug = true;
                    //HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    //"http://isapi.mekashron.com/StartAJob/General.dll");
                    try {
                        //this is the actual part that will call the webservice
                        // androidHttpTransport.call("http://isapi.mekashron.com/StartAJob/General.dll", envelope);

                       androidHttpTransport.call(SOAP_ACTION, envelope);

                        if(androidHttpTransport!=null) {
                            Log.i("HTTP request", androidHttpTransport.requestDump);
                            Log.i("HTTP response", androidHttpTransport.responseDump);
                        }
                    envelope.enc="http://schemas.xmlsoap.org/soap/encoding/";
                      envelope.env="http://schemas.xmlsoap.org/soap/envelope/";
                      SoapObject result=(SoapObject)envelope.bodyIn;
                      SoapPrimitive rr=(SoapPrimitive)result.getPrimitiveProperty("return");
                     //result.getProperty("LoginResponse");
                      Log.i("response",rr.toString());
                       // Log.i("response",t.getProperty());
                     //  SoapObject result = (SoapObject)envelope.bodyIn;
                     //  Log.i("response",result.getProperty("result").toString());
///*
                         /* if(result != null){
                            Log.i("response",result.toString());
                            response=result.toString();
                        }*/
                        }catch (Exception e) {
                                 e.printStackTrace();
                        response="";
                    }
                    // Get the SoapResult from the envelope body.
                  //  SoapObject result = (SoapObject)envelope.bodyIn;
                }catch(Exception e){
                    //do something
                }
                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                final TextView resultMessage = (TextView) findViewById(R.id.textView);
                if(response !="") {
                    resultMessage.setText(response);
                }
                else {
                    resultMessage.setText("bad response");
                    resultMessage.setTextColor(Color.MAGENTA);
                }
                Log.i("response",response.toString());
            }

        }
/*
       String request(String[] args){
        String encodingStyleURI = "http://www.w3.org/2003/05/soap-encoding";
        String message;
        if ( args.length != 0 )
            message = args[0];
        else
            message = "Thanks!";
        // попытка удаленного вызова процедуры SOAP
        try {
            URL url  =new URL("http://isapi.mekashron.com/StartAJob/General.dll" );
            // формирование вызова
            Call remoteMethod = new Call();
            remoteMethod.setTargetObjectURI("urn: jcml-simple-message" ) ;
            // задание имени вызываемого удаленного метода
            remoteMethod.setMethodName("Login");
            remoteMethod.setEncodingStyleORI(encodingStyleURI);
            // задание параметров для удаленного метода
            Vector parameters = new Vector();
            parameters.addElement( new Parameter( "message",
                    String.class, message, null ) );
            remoteMethod.setParams( parameters );
            Response response;
            // вызов удаленного метода
            response = remoteMethod.invoke( url, "" );
            // получение ответа
            if ( response.generatedFault() ) {
                Fault fault = responee.getFault();
                System.err.println( "CALL FAILED:\nFault Code = "
                        + fault.getFaultCode()+ "\nFault String = "
                        + fault.getFaultString() );
            }
            else {
                Parameter result = response.getReturnValue();
                // отображение результатов вызова
                System.out.println( result.getValue() );
            }
        }
        // перехват исключения при указании неверного URL
        catch ( MalformedURLException malformedURLException ) {
            malformedURLException.printStackTrace();
            System.exit( 1 );
        }
        // перехват исключения SOAPException
        catch ( SOAPException soapException ) {
            System.err.printin( "Error message: " +	soapException.getMessage() );
            System.exit( 1 );
        }


return args;
}*/
    }













