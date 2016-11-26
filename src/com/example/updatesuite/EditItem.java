package com.example.updatesuite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.ParseException;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import com.example.updatesuite.UpdateItems;
@SuppressLint("NewApi")

public class EditItem extends Activity{
	EditText e1,e2,e3,e4,e5,e6,e7;
	TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    final String fr=Start.fid;
    
	
	Button save,ok;
	
	static String f1,i1,i2,i3,i4,i5,i6,i7,i8; 
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_again);
        e1=(EditText)findViewById(R.id.editText1);
        t9=(TextView)findViewById(R.id.textView9);
        t9.setText(fr);
        
        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        t3.setVisibility(View.INVISIBLE);
        e2=(EditText)findViewById(R.id.editText2);
        e2.setVisibility(View.INVISIBLE);
        
        t4=(TextView)findViewById(R.id.textView4);
        t4.setVisibility(View.INVISIBLE);
        e3=(EditText)findViewById(R.id.editText3);
        e3.setVisibility(View.INVISIBLE);
        
	
        t5=(TextView)findViewById(R.id.textView5);
        t5.setVisibility(View.INVISIBLE);
        e4=(EditText)findViewById(R.id.editText4);
        e4.setVisibility(View.INVISIBLE);
        
	
        t6=(TextView)findViewById(R.id.textView6);
        t6.setVisibility(View.INVISIBLE);
        e5=(EditText)findViewById(R.id.editText5);
        e5.setVisibility(View.INVISIBLE);
        
        t7=(TextView)findViewById(R.id.textView7);
        t7.setVisibility(View.INVISIBLE);
        e6=(EditText)findViewById(R.id.editText6); 
        e6.setVisibility(View.INVISIBLE);
        
        t8=(TextView)findViewById(R.id.textView8);
        t8.setVisibility(View.INVISIBLE);
        e7=(EditText)findViewById(R.id.editText7);
        e7.setVisibility(View.INVISIBLE);
        
        ok=(Button)findViewById(R.id.button1);
        save=(Button)findViewById(R.id.button2);
        save.setVisibility(View.INVISIBLE);
        
        ok.setOnClickListener(new View.OnClickListener() {
		   InputStream is;	
		   String result="";
		   @TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@SuppressLint("NewApi")
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				if(e1.getText().toString().trim().length()==10)
		        {
		        	 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				     StrictMode.setThreadPolicy(policy);
				     final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				     final String mydate=e1.getText().toString();
				     final String fr=Start.fid;
		        	 nameValuePairs.add(new BasicNameValuePair("mydate",mydate));
				     nameValuePairs.add(new BasicNameValuePair("fr",fr));
				        
				     try{
					        HttpClient httpclient = new DefaultHttpClient();
					        HttpPost httppost = new HttpPost("http://applicationdownloader.net46.net/walcliff/edititems.php");
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
					
							}
							finally{}
						} 
				     catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalStateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							finally{}
				      
				     try{
					        JSONArray jArray = new JSONArray(result);
					        JSONObject json_data=null;
					        for(int i=0;i<jArray.length();i++){
				                json_data = jArray.getJSONObject(i);
				                f1=json_data.getString("fid");
				                i1=json_data.getString("item1");
				                i2=json_data.getString("item2");
				                i3=json_data.getString("item3");
				                i4=json_data.getString("item4");
				                i5=json_data.getString("item5");
				                i6=json_data.getString("item6");
				                i7=json_data.getString("input");
				                i8=json_data.getString("reentered");
				             } 
					         t3.setVisibility(View.VISIBLE);
						     e2.setVisibility(View.VISIBLE);
						     e2.setText(i1);
						     
						     t4.setVisibility(View.VISIBLE);
						     e3.setVisibility(View.VISIBLE);
						     e3.setText(i2);
						     
						     t5.setVisibility(View.VISIBLE);
						     e4.setVisibility(View.VISIBLE);
						     e4.setText(i3);
						     
						     t6.setVisibility(View.VISIBLE);
						     e5.setVisibility(View.VISIBLE);
						     e5.setText(i4);
						     
						     t7.setVisibility(View.VISIBLE);
						     e6.setVisibility(View.VISIBLE);
						     e6.setText(i5);
						     
						     t8.setVisibility(View.VISIBLE);
						     e7.setVisibility(View.VISIBLE);
						     e7.setText(i6);
						     
						     save.setVisibility(View.VISIBLE);
						         
					        }catch(JSONException e1){
					            e1.printStackTrace();
					        }catch (ParseException e1){
					            e1.printStackTrace();
					        }
				     
				      
				         
				     }
				else
				{
					AlertDialog al=new AlertDialog.Builder(EditItem.this).create();
					al.setTitle("Update Suite");
		        	al.setMessage("Please Enter the date properly");
		        	al.setButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
                  al.show();
				}
				
			}
		});
        
	
 save.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			String w1 ="",w2,w3,w4,w5,w6;
			InputStream is1;
			String result1="";
			final String mydate=e1.getText().toString();
			if((e2.getText().toString().trim().length() > 0) && (e3.getText().toString().trim().length() > 0) && (e4.getText().toString().trim().length() > 0) && (e5.getText().toString().trim().length() > 0) && (e6.getText().toString().trim().length() > 0))
			{
			w1=e2.getText().toString();
			w2=e3.getText().toString();
			w3=e4.getText().toString();
			w4=e5.getText().toString();
			w5=e6.getText().toString();
			w6=e7.getText().toString();
			
			
	    	 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		     StrictMode.setThreadPolicy(policy);
		     final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        	 nameValuePairs.add(new BasicNameValuePair("w1",w1));
        	 nameValuePairs.add(new BasicNameValuePair("w2",w2));
        	 nameValuePairs.add(new BasicNameValuePair("w3",w3));
        	 nameValuePairs.add(new BasicNameValuePair("w4",w4));
        	 nameValuePairs.add(new BasicNameValuePair("w5",w5));
        	 nameValuePairs.add(new BasicNameValuePair("w6",w6));
		     nameValuePairs.add(new BasicNameValuePair("fr",fr));
		     nameValuePairs.add(new BasicNameValuePair("mydate", mydate));  
		     try{
			        HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://applicationdownloader.net46.net/walcliff/updatetheedit.php");
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			        HttpResponse response = httpclient.execute(httppost);
			        HttpEntity entity = response.getEntity();
			        is1 = entity.getContent();
			      //convert response to string
					try{
					        BufferedReader reader = new BufferedReader(new InputStreamReader(is1,"iso-8859-1"),8);
					        StringBuilder sb = new StringBuilder();
					        String line = null;
					        while ((line = reader.readLine()) != null) {
					                sb.append(line + "\n");
					        }
					      is1.close();
		                    result1=sb.toString();
			
					}
					finally{}
				} 
		     catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					finally{}

			
		}
			}
	});
	
	
	
	
	
	}
 
}
