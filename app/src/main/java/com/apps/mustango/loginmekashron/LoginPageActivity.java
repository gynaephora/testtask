package com.apps.mustango.loginmekashron;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;

import retrofit2.Call;

public class LoginPageActivity extends AppCompatActivity {


    private static String SOAP_ACTION = "http://isapi.mekashron.com/StartAJob/General.dll/Login";

   // private static String SOAP_ACTION = "http://isapi.mekashron.com/soapclient/soapclient.php?URL=http://isapi.mekashron.com/StartAJob/General.dll%2Fwsdl%2FIGeneral";
    private static String NAMESPACE = "http://isapi.mekashron.com/StartAJob/General.dll/Login";
  // private static String NAMESPACE ="http://www.w3.org/2003/05/soap-encoding";
    private static String METHOD_NAME = "Login";

 private static String URL = "http://isapi.mekashron.com/StartAJob/General.dll/wsdl/IGeneral";

    EditText loginField;
    EditText passwordField;
  //  Post postRequest=new Post();
 //   RequestItemTask requestItemTask=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final  EditText loginField=(EditText) findViewById(R.id.editText);
        final EditText passwordField=(EditText) findViewById(R.id.editText2);
        Button loginButton=(Button) findViewById(R.id.button);



        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
/*
                String json = postRequest.loginJson(loginField.getText().toString(), passwordField.getText().toString());
                Log.i("json string",json);
                requestItemTask = (RequestItemTask) new RequestItemTask()
                        .execute(json);
*/

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try{
                         /*   LoginService loginService =
                                    ServiceGenerator.createService(LoginService.class, "Volodymyr", "qwerty123");
                            Call<User> call = loginService.basicLogin();
                            User user = call.execute().body();
                            Log.i("request1",user.mName);
                            Log.i("request2",user.mPassword);*/
//Initialize soap request + add parameters
                           // SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                            //Use this to add parameters
                           //  request.addProperty("params","UserName=Volodymyr&Password=qwerty123&IP=192.168.1.1");
                            request.addProperty("UserName","John")
                                    .addProperty("Password","Candy")
                                    .addProperty("IP","192.168.1.1");
                            //Declare the version of the SOAP request
                          //  SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.ENV2003);
                          //  String ENV2001="http://www.w3.org/2003/05/soap-envelope";
                            CustomSoapSerializationEnvelope envelope = new  CustomSoapSerializationEnvelope(SoapEnvelope.VER12);
                         // SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
                            envelope.setOutputSoapObject(request);

                            //Needed to make the internet call
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                            try {
                                //this is the actual part that will call the webservice
                                androidHttpTransport.call(SOAP_ACTION, envelope);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            // Get the SoapResult from the envelope body.
                            SoapObject result = (SoapObject)envelope.bodyIn;

                            if(result != null){
                               // TextView t = (TextView)this.findViewById(R.id.resultbox);
                                Log.i("response",result.getProperty(0).toString());
                                //Get the first property and change the label text
                            //    t.setText("SOAP response:\n\n" + result.getProperty(0).toString());
                            }
/*
                          Post rr= new Post();
                            rr.PostR("https://isapi.mekashron.com/StartAJob/General.dll");
*/
                        }catch(Exception e){
                            //do something
                        }
                        return null;
                    }
                }.execute();


            }
        });
    }







    /*
    private class RequestItemTask extends AsyncTask<String,Void, String> {


        protected String doInBackground(String... string) {
            String response = "";
            String json=string[0];
            //  postRequest.PostRequest("http://isapi.mekashron.com/StartAJob/General.dll",json);
         /*   try {
                response = postRequest.PostR("http://isapi.mekashron.com/StartAJob/General.dll/wsdl/IGeneral", json);
                Log.i("request",response);
                //  System.out.print(response);
            } catch (IOException e) {
                //обработка ошибки
            }
            return response;
        }

        @Override
        protected void onPostExecute(String request) {
            final TextView resultMessage = (TextView) findViewById(R.id.textView);
            resultMessage.setText(request);
            Log.i("request",request.toString());
        }

    }
*/

}
