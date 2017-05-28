package com.example.android.softkeyboard;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.inputmethodservice.Keyboard.Key;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
/**
 * This activity launches the main activity of the APK selected.
 * It then handles the keypresses recorded and saves them in the array.
 * @author manavdutta1
 *
 */
public class RecordingActivity extends Activity{
	  private int position;
	  private ArrayList<Test> testLists;
	  @SuppressLint("NewApi") protected void onCreate(Bundle savedInstanceState) {
	  		 super.onCreate(savedInstanceState);
	  		 Intent intent = new Intent(Intent.ACTION_MAIN);
	  		 intent.addCategory(Intent.CATEGORY_LAUNCHER);
	  		 String mainActivity = "";
	  		 MyKeyboard.clearKeyEvents();
		     MyKeyboard.clearKeys();
	  		 Intent newIntent = getIntent();
	  		 Bundle extras = newIntent.getExtras();
	  		 position = (Integer) extras.get("int value");
	  		 testLists = (ArrayList<Test>) extras.get("Test value");
	  		 Intent mIntent = getPackageManager().getLaunchIntentForPackage(APKActivity.currApkName); 
	  		 if (mIntent != null) {
	  		    if (mIntent.getComponent() != null) {
	  		        mainActivity = mIntent.getComponent().getClassName();
	  		    }
	  		 }
	  		 final int code = 8;
	  		 intent.setComponent(new ComponentName(APKActivity.currApkName, mainActivity));
	         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	         startActivityForResult(intent, 8);
	  }
	  /**
	   * Displays a layout after the activity is finished.
	   */
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		     setContentView(R.layout.record);
	  }
	  /**
	   * This method goes back to the Test menu and handles processing of key events recorded.
	   * @param v- the context of the button
	   */
	  public void GoBack(View v) {
		     ArrayList<Key> keys = MyKeyboard.getKeys();
		     ArrayList<String> events = MyKeyboard.getKeyEvents();
		     Intent intent = new Intent(this, Tests.class);
		     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		     Test test = testLists.get(position);
		     for (String event : events) {
		    	 test.addToEvents(event);
		     }
		     for (Key key: keys) {
		    	 test.addToWidgets(key.label.toString());
		     }
		     MyKeyboard.clearKeyEvents();
		     MyKeyboard.clearKeys();
		     intent.putExtra("Test value", testLists);
		     startActivity(intent);
	     }
		     
	  }
	  
