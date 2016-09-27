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
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;




public class LoginPageActivity extends AppCompatActivity {

  // private static String SOAP_ACTION = "http://isapi.mekashron.com/soapclient/soapclient.php?URL=http://isapi.mekashron.com/StartAJob/General.dll%2Fwsdl%2FIGeneral";
    private static String SOAP_ACTION ="urn:General.Intf-IGeneral#Login";
   private static String NAMESPACE = "urn:General.Intf-IGeneral";
   private static String METHOD_NAME = "Login";
   private static String URL ="http://isapi.mekashron.com/StartAJob/General.dll/soap/IGeneral";
    RequestItemTask requestItemTask=null;
    HttpTransportSE androidHttpTransport;
    private boolean httpStat=false;
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
                String login=loginField.getText().toString();
                String pass=passwordField.getText().toString();
                if(login.equals("") | pass.equals("")){
                    Toast t=Toast.makeText(getApplicationContext(),"The field Login and Password cannot be empty. Please fill it",Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    String[] par = {login, pass};
                    requestItemTask = (RequestItemTask) new RequestItemTask().execute(par);
            }
            }
        });
    }
        private class RequestItemTask extends AsyncTask<String,Void, String> {


            protected String doInBackground(String... string) {
                String response = "";
                String username=string[0];
                String password=string[1];

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

                    try {
                        //call the webservice
                       androidHttpTransport.call(SOAP_ACTION, envelope);

                       if(androidHttpTransport!=null) {
                            Log.i("HTTP request", androidHttpTransport.requestDump);
                            Log.i("HTTP response", androidHttpTransport.responseDump);
                        }
                      envelope.enc="http://schemas.xmlsoap.org/soap/encoding/";
                      envelope.env="http://schemas.xmlsoap.org/soap/envelope/";
                   // SoapObject result=(SoapObject)envelope.bodyIn;
                        if (envelope.bodyIn instanceof SoapFault) {
                            String strFault = ((SoapFault) envelope.bodyIn).faultstring;
                            Log.i("SOAP Request : ",androidHttpTransport.requestDump);
                            Log.i("Fault string : ",strFault);
                        } else {
                            Object object = envelope.getResponse();
                            JSONObject jObject = new JSONObject(object.toString());
                            String aJsonString;
                            aJsonString=jObject.toString();
                            httpStat=true;
                            response=aJsonString;
                            }

                        }catch (Exception e) {
                                 e.printStackTrace();
                        httpStat=false;
                        response="HTTP request failed";
                    }
                    // Get the SoapResult from the envelope body.

                }catch(Exception e){
                    httpStat=false;
                    response="HTTP request failed";
                }
                return response;
            }

            @Override
            protected void onPostExecute(String response) {
                final TextView resultMessage = (TextView) findViewById(R.id.textView);
                final TextView resultResponse = (TextView) findViewById(R.id.textView4);
                if(httpStat==true) {
                    resultMessage.setTextColor(Color.BLACK);
                    resultMessage.setText("HTTP request success");
                    resultResponse.setText(response);
                }
                else {
                    resultMessage.setTextColor(Color.RED);
                    resultMessage.setText(response);
                    resultResponse.setText("");
                }
                Log.i("response",response.toString());
            }
        }

}













