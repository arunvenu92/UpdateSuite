package com.example.updatesuite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class Start extends Activity {
	
	Button cont,register;
	TextView j,j1,j2;
	EditText e,e1;
	String result="";
	static String name;
	String mail,secnum,imei,age,address;
	static String fid;
	String number;
	int i1;
	int registering=0;
	String checkcon;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_first);
	        j1=(TextView)findViewById(R.id.textView3);
	        j2=(TextView)findViewById(R.id.textView4);
	        e1=(EditText)findViewById(R.id.edit_t1);
	        register=(Button)findViewById(R.id.button1);
	        cont=(Button)findViewById(R.id.b1);
	        
	       // boolean checkStatus=checkRegistration();
	        if(registering==0)
	        {
	        	AlertDialog al=new AlertDialog.Builder(Start.this).create();
	        	al.setTitle("Register the Application");
	        	al.setMessage("Welcome to Update Suite,a centralized client updation application \n Click to register and Accept the license of the application copyright @ creativekeysrokes 2013 \n Enter the secret Number");
	        	e1=new EditText(this);
	        	al.setView(e1);
	        	al.setButton("Register",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						 String sec=e1.getText().toString();
					     InputStream is;	
						 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					     StrictMode.setThreadPolicy(policy);
					     final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			        	 nameValuePairs.add(new BasicNameValuePair("sec",sec));
					     try{
						        HttpClient httpclient = new DefaultHttpClient();
						        HttpPost httppost = new HttpPost("http://applicationdownloader.net46.net/walcliff/register.php");
						        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						        HttpResponse response = httpclient.execute(httppost);
						        HttpEntity entity = response.getEntity();
						        is = entity.getContent();
						      //convert response to string
								try{
								        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
								        StringBuilder sb = new StringBuilder();
								        String line = null;
								        while ((line = reader.readLine()) != null) {
								                sb.append(line + "\n");
								        }
								        is.close();
							        result=sb.toString();
							       // j1.setText(result);
							        }
								catch(Exception e){
							        Log.e("log_tag", "Error converting result "+e.toString());
						        	}
				            	}
					     		catch(Exception e){
					     		Log.e("log_tag", "Error in http connection "+e.toString());
					     	}
		        			   	
					}
				});
	        	registering=1;
	        	al.show();
	        }
	        
	        
	        
	        
	        
	        
	        
	        cont.setOnClickListener(new View.OnClickListener() {	
				@SuppressLint("NewApi")
				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View v) {
					 InputStream is;	
					 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				     StrictMode.setThreadPolicy(policy);
				     final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				     final String p1=e.getText().toString();
				     checkcon="ENTER";		     
				     if(e.getText().toString().trim().length() > 0 && e.getText().toString().trim().length() <= 10 && !(e.getText().toString().contains("0000000000")))
				     {
				     nameValuePairs.add(new BasicNameValuePair("p1",p1));
				     nameValuePairs.add(new BasicNameValuePair("checkcon",checkcon));
				     try{
					        HttpClient httpclient = new DefaultHttpClient();
					        HttpPost httppost = new HttpPost("http://applicationdownloader.net46.net/walcliff/check.php");
					        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					        HttpResponse response = httpclient.execute(httppost);
					        HttpEntity entity = response.getEntity();
					        is = entity.getContent();
					      //convert response to string
							try{
							        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
							        StringBuilder sb = new StringBuilder();
							        String line = null;
							        while ((line = reader.readLine()) != null) {
							                sb.append(line + "\n");
							        }
							        is.close();
						        result=sb.toString();
						       // j1.setText(result);
						        }
							catch(Exception e){
						        Log.e("log_tag", "Error converting result "+e.toString());
					        	}
			            	}
				     		catch(Exception e){
				     		Log.e("log_tag", "Error in http connection "+e.toString());
				     	}
				     
				       try{
				        JSONArray jArray = new JSONArray(result);
				        JSONObject json_data=null;
				        for(int i=0;i<jArray.length();i++){
			                json_data = jArray.getJSONObject(i);
			                fid=json_data.getString("fid");
			                name=json_data.getString("name");
			                mail=json_data.getString("email");
			                number=json_data.getString("phone");
			                age=json_data.getString("age");
			                address=json_data.getString("address");
			                secnum=json_data.getString("secretnum");
			                }
			               
			             } 
				         
				        catch(JSONException e1){
				        	i1=1;
				        	AlertDialog alt=new AlertDialog.Builder(Start.this).create();
				        	alt.setTitle("Update Suite");
				        	alt.setMessage("Your credentials are not Authenticated.Please Register,Or check Internet connecction");
				        	alt.setButton("OK",new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
									System.exit(-1);
									
								}
							});
				        	alt.show();
				        }catch (ParseException e1){
				            e1.printStackTrace();
				        }
				           if(i1!=1){
				         	Intent in=new Intent("com.example.updatesuite.UpdateItems");
				            startActivity(in);
				           }
		                }
				       	       
				
				     else
				     {
				    	 AlertDialog alertDialog = new AlertDialog.Builder(Start.this).create();
			             alertDialog.setTitle("Update Suite");
			             alertDialog.setMessage("Enter the valid phone number"); 
			             alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface arg0, int arg1) {
					     arg0.cancel();						
						}
					});
			        alertDialog.show();
				     
				     }
				     
				     
				}
				    
				    
			});	        
	      
	   
}
  private boolean checkRegistration() {
		 TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
     	 String deviceid = tm.getDeviceId();
         InputStream is;	
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	     StrictMode.setThreadPolicy(policy);
	     final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	     checkcon="CHECK";
 	     nameValuePairs.add(new BasicNameValuePair("deviceid",deviceid));
 	     nameValuePairs.add(new BasicNameValuePair("checkcon",checkcon));
	     try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://applicationdownloader.net46.net/walcliff/check.php");
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		      //convert response to string
				try{
				        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				        StringBuilder sb = new StringBuilder();
				        String line = null;
				        while ((line = reader.readLine()) != null) {
				                sb.append(line + "\n");
				        }
				        is.close();
			        result=sb.toString();
			       // j1.setText(result);
			        }
				catch(Exception e){
			        Log.e("log_tag", "Error converting result "+e.toString());
		        	}
         	}
	     		catch(Exception e){
	     		Log.e("log_tag", "Error in http connection "+e.toString());
	     	}
	
	    try{
	        JSONArray jArray = new JSONArray(result);
	        JSONObject json_data=null;
	        for(int j=0;j<jArray.length();j++){
              json_data = jArray.getJSONObject(j);
              secnum=json_data.getString("secretnum");
              imei=json_data.getString("imei");
              }
             
           } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  if(imei.isEmpty() || imei=="null")
	    return false;
	  else
		return true;
	}
@SuppressWarnings({ "deprecation", "unused" })
public void onStart()
  {
	  int versionnumber=Integer.valueOf(android.os.Build.VERSION.SDK);
	  if (versionnumber >= 8) {
          super.onStart();
          ActionBar actionBar = this.getActionBar();
          actionBar.setDisplayHomeAsUpEnabled(true);
  }
  else {
          super.onStart();
  }

  }
}
