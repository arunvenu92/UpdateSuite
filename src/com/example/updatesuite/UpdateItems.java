package com.example.updatesuite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONArray;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.updatesuite.Start;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class UpdateItems extends Activity{
	Button submit,edit;
	EditText it1,it2,it3,it4,it5,it6;
	TextView t,t2;
	String item1,item2,item3,item4,item5,item6;
	String fr;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_items);
      //  BufferedReader bw=new BufferedReader(new FileReader(""));
        
        submit=(Button)findViewById(R.id.b1);
        edit=(Button)findViewById(R.id.b2);
       
        it1=(EditText)findViewById(R.id.edit_t);
        it2=(EditText)findViewById(R.id.edit_t1);
        it3=(EditText)findViewById(R.id.edit_t2);
        it4=(EditText)findViewById(R.id.edit_t3);
        it5=(EditText)findViewById(R.id.editText5);
        it6=(EditText)findViewById(R.id.editText6);
        
      
        t2=(TextView)findViewById(R.id.textView9);
        
        t2.setText("Welcome to Update Suite" +" " +Start.name);
        //t.setText(mydate);
    	final AlertDialog alertDialog = new AlertDialog.Builder(UpdateItems.this).create();
        alertDialog.setTitle("Update Suite");

       
         submit.setOnClickListener(new View.OnClickListener() {
			InputStream is;
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {
				String result="";
				
				item1=it1.getText().toString();
				item2=it2.getText().toString();
				item3=it3.getText().toString();
				item4=it4.getText().toString();
				item5=it5.getText().toString();
				item6=it6.getText().toString();
				fr=Start.fid;
				 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			     StrictMode.setThreadPolicy(policy);
			     final ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

				
				if(it1.getText().toString().trim().length() > 0 && it2.getText().toString().trim().length() > 0 && it3.getText().toString().trim().length() > 0 && it4.getText().toString().trim().length() > 0 && it5.getText().toString().trim().length() > 0 && it6.getText().toString().trim().length() > 0)
				{
				nameValuePairs.add(new BasicNameValuePair("item1",item1));
		        nameValuePairs.add(new BasicNameValuePair("item2",item2));
		        nameValuePairs.add(new BasicNameValuePair("item3",item3));
		        nameValuePairs.add(new BasicNameValuePair("item4",item4));
		        nameValuePairs.add(new BasicNameValuePair("item5",item5));
		        nameValuePairs.add(new BasicNameValuePair("item6",item6));
		        nameValuePairs.add(new BasicNameValuePair("fr", fr));
		      
		       
		        try{
			        HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://applicationdownloader.net46.net/walcliff/newitems.php");
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
					       //t.setText(result);
					        
	                 if(result.contains(result))
					        {
				        alertDialog.setMessage("Database is updated");
				        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				                public void onClick(final DialogInterface dialog, final int which) {
				                // Write your code here to execute after dialog closed
				                Toast.makeText(getApplicationContext(), "Thanks for Entering", Toast.LENGTH_SHORT).show();
				                }
				        });
				 
				        // Showing Alert Message
				        alertDialog.show();					        	
					        }
					        else
					        {
					        	AlertDialog alertDialog = new AlertDialog.Builder(UpdateItems.this).create();
				                alertDialog.setTitle("Update Suite");
				                alertDialog.setMessage("Database is not updated check fid");
				                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				                public void onClick(final DialogInterface dialog, final int which) {
								Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
				                }
				                });
				 
				        alertDialog.show();					        	
				
					        }
					}catch(Exception e){
					        Log.e("log_tag", "Error converting result "+e.toString());
					}
			}catch(Exception e){
			        Log.e("log_tag", "Error in http connection "+e.toString());
			}
		     
			
			}
				
				else
				{
					alertDialog.setTitle("Update Suite");
					alertDialog.setMessage("Enter all the field");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							arg0.cancel();
							
						}
					});
					alertDialog.show();
				}
		
				
		}});
         
         
        edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent in1=new Intent("com.example.updatesuite.UpdateItems2");
        		startActivity(in1);
			    	
			}
		}); 
    
    }
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	AlertDialog.Builder al=new AlertDialog.Builder(UpdateItems.this);
        	al.setTitle("Update Suite");
        	al.setMessage("Do u wanna exit");
        	al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Start.name="";
					Start.fid="";
					finish();
					System.exit(0);
					
				}
			});
        	 al.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
    				@Override
    				public void onClick(DialogInterface dialog, int which) {
    					dialog.cancel();
    					
    				}
    			});
       
           
            al.show();
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.update_items, menu);
        return true;
    }
    
}
