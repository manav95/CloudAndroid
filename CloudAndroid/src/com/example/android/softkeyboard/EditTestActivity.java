package com.example.android.softkeyboard;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
/**
 * This is the Activity for editing a particular test
 * @author manavdutta1
 *
 */
public class EditTestActivity extends Activity{
	private String value;
	private ArrayList<Test> testLists;
	private ArrayList<String> testEvents;
	private int testPosition;
	/**
	 * This creates the activity.
	 */
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.clito);
	        ListView val = (ListView) findViewById(R.id.clitoView);
	        testEvents = new ArrayList<String>();
	        Context context = getApplicationContext();
	        Intent intent = getIntent();
	        Bundle extras = intent.getExtras();
	        testLists = (ArrayList<Test>) extras.get("Test value");
	        int position = (Integer) extras.get("int value");
	        testEvents.addAll(testLists.get(position).getEvents());  
	        testPosition = position;
	        EditTextAdapter adapter = new EditTextAdapter(this, (String[]) testEvents.toArray(new String[1]));
	        val.setAdapter(adapter);
	 }
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.bob, menu);
	        return true;
	 }
	 @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle presses on the action bar items
         switch (item.getItemId()) {
             case R.id.gotest:
                 GoToTests();
                 return true;
             default:
                 return super.onOptionsItemSelected(item);
         }
     }
	 /**
	  * This deletes the particular key from the sequence of keys in Test
	  * @param v- view corresponding to the particular key in sequence
	  */
	 public void deleteKey(View v) {
		 int value = (Integer) v.getTag();
         testLists.get(testPosition).deleteString(value);
         Intent intent = new Intent(this, EditTestActivity.class);
         intent.putExtra("Test value", testLists);
	      intent.putExtra("int value", testPosition);
         startActivity(intent);              
    }
	 /**
	  * sends the key chosen to the first row
	  * @param v- the view corresponding to chosen key
	  */
	 public void startSwap(View v) {
		 int value = (Integer) v.getTag();
		 String event = testEvents.get(value);
		 int position = 0;
		 String eventTwo = testEvents.get(position);
		 testEvents.set(value, eventTwo);
		 testEvents.set(position, event);
		 testLists.get(testPosition).setEvents(testEvents);
		 Intent intent = new Intent(this, EditTestActivity.class);
         intent.putExtra("Test value", testLists);
	      intent.putExtra("int value", testPosition);
		 startActivity(intent);
	 }
	 /**
	  * Goes to the tests
	  */
	 public void GoToTests() {
    	 Intent intent = new Intent(this, Tests.class);
    	 intent.putExtra("Test value", testLists);
    	 startActivity(intent);
	 }
	 
}
