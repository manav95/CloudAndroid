package com.example.android.softkeyboard;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import android.app.UiAutomation;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
/**
 * This is our Main Activity in the application.
 * @author manavdutta1
 *
 */
public class MainActivity extends Activity{
	 private ArrayList<Test> tests;
	 public static Activity act = null;
     @Override
     public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.main);
	     tests = new ArrayList<Test>();
	     Intent intent = getIntent();
	     Bundle extras = intent.getExtras();
	     if (extras != null) {
	        tests = (ArrayList<Test>) extras.get("Test value");
	     } 
	 }
     @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	 }
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle presses on the action bar items
         switch (item.getItemId()) {
             case R.id.button2:
                 GoToTests();
                 return true;
             case R.id.button3:
            	 GoToApks();
            	 return true;
             default:
                 return super.onOptionsItemSelected(item);
         }
     }
     /**
      * This method navigates to the APK menu.
      */
     public void GoToApks() {
    	 Intent intent = new Intent(this, APKActivity.class);
    	 intent.putExtra("Test value", tests);
    	 startActivity(intent);
     }
     /**
      * This method navigates to the test menu.
      */
     public void GoToTests() {
    	 Intent intent = new Intent(this, Tests.class);
    	 intent.putExtra("Test value", tests);
    	 startActivity(intent);
     }
}
