package com.example.android.softkeyboard;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
/**
 * This is where we create new tests.
 * @author manavdutta1
 *
 */
public class Settings extends Activity{
	private String value;
	private ArrayList<Test> testLists;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.settingss);
	        Context context = getApplicationContext();
	        Intent intent = getIntent();
	        Bundle extras = intent.getExtras();
	        testLists = (ArrayList<Test>) extras.get("Test value");
	 }
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        return true;
	 }
	    public void setValue(String key) {
	    	value = key;
	    }
	    /**
	     * This method goes back to test menu.
	     * @param v- the context of the button pressed to get here
	     */
	    public void goToTest(View v) {
	    	Intent intent = new Intent(this, Tests.class);
	    	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
	    	intent.putExtra("Test value", testLists);
	    	startActivity(intent);
	     }
	    /**
	     * This method creates the new Test object.
	     * @param v- the context of button pressed to get here.
	     */
	    public void createNewTest(View v) {
	    	EditText mEdit = (EditText)findViewById(R.id.editText1);
	    	CheckBox check = (CheckBox) findViewById(R.id.capture_ui);
	    	CheckBox checkTwo = (CheckBox) findViewById(R.id.capture_keys);
	    	Test test;
	    	if (check.isChecked()) {
	    	    test = new Test(mEdit.getText().toString(), "ui");
	    	}
	    	else {
	    	     test = new Test(mEdit.getText().toString(), "keys");
	    	}
	    	testLists.add(test);
	    	}
	    }
