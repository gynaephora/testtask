package com.apps.mustango.loginmekashron;

import android.graphics.Color;
import android.os.AsyncTask;
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


public class LoginPageActivity extends AppCompatActivity {

    private static String SOAP_ACTION = "http://isapi.mekashron.com/soapclient/soapclient.php?URL=http://isapi.mekashron.com/StartAJob/General.dll%2Fwsdl%2FIGeneral";
    private static String NAMESPACE = "http://isapi.mekashron.com/StartAJob/General.dll/";
    private static String METHOD_NAME = "Login";
    private static String URL = "http://isapi.mekashron.com/StartAJob/General.dll/wsdl/IGeneral";

    RequestItemTask requestItemTask=null;

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

                    CustomSoapSerializationEnvelope envelope = new  CustomSoapSerializationEnvelope(SoapEnvelope.VER12);
                    envelope.setOutputSoapObject(request);
                    Log.i("request",envelope.toString());
                    //Needed to make the internet call
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                    try {
                        //this is the actual part that will call the webservice
                          androidHttpTransport.call(SOAP_ACTION, envelope);
                          SoapObject result = (SoapObject)envelope.getResponse();

                          if(result != null){
                            Log.i("response",result.getProperty(0).toString());
                            response=result.toString();
                        }
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
    }













