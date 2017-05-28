package com.example.android.softkeyboard;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * This activity displays our test menu and options that the user can do.
 * @author manavdutta1
 *
 */
public class Tests extends ListActivity{
	       public static ArrayList<Test> testList;
	       public static ArrayList<String> testNameList;
	       @Override
	       protected void onCreate(Bundle savedInstanceState) {
	  		 super.onCreate(savedInstanceState);
	  		setContentView(R.layout.tests);
	  		Intent intent = getIntent();
		     Bundle extras = intent.getExtras();
		     ListView listview = getListView();
		     testList = (ArrayList<Test>) extras.get("Test value");
		     if(testList.size() != 0) {
	  		   CustomAdapter<Test> adapter = new CustomAdapter<Test>(this, testList.toArray(new Test[1]));
	  		   listview.setAdapter(adapter);  	
	  		 }
	       }
	       @Override
	  	 public boolean onCreateOptionsMenu(Menu menu) {
	  	        // Inflate the menu; this adds items to the action bar if it is present.
	  	        getMenuInflater().inflate(R.menu.valits, menu);
	  	        return true;
	  	 }
	     /**
	      * This launches the activity to record keypresses for a new test.  
	      * @param v- the context of the test selected
	      */
	     public void recordSteps(View v) {
	    	 Intent intent = new Intent(this, RecordingActivity.class);
	    	 int position =  (Integer) v.getTag();
		     intent.putExtra("Test value", testList);
		     intent.putExtra("int value", position);
		     startActivity(intent);
	     }
	     /**
	     *This deletes the test we have selected.
	     */
	     public void deleteTest(View v) {
              int value = (Integer) v.getTag();
              testList.remove(value);
              Intent intent = new Intent(this, Tests.class);
              intent.putExtra("Test value", testList);
 		      intent.putExtra("int value", 0);
              startActivity(intent);              
	     }
         /**
          * This launches the editing mechanism for the test selected.
          * @param v- the test item selected.
          */
	     public void editTest(View v) {
	    	 int value = (Integer) v.getTag();
	    	 Intent intent = new Intent(this, EditTestActivity.class);
	    	 intent.putExtra("Test value", testList);
	    	 intent.putExtra("int value", value);
	    	 startActivity(intent);
	     }
	     @Override
	     public boolean onOptionsItemSelected(MenuItem item) {
	         // Handle presses on the action bar items
	         switch (item.getItemId()) {
	             case R.id.createtest:
	                 createTest();
	                 return true;
	             case R.id.details:
	            	 GoToTestDetails();
	                 return true;
	             case R.id.gomain:
	                 GoToMain();
	                 return true;
	             default:
	                 return super.onOptionsItemSelected(item);
	         }
	     }
	     /**
	      * This goes to the TestDetails activity.
	      */
	     public void GoToTestDetails() {
	    	 Intent intent = new Intent(this, TestDetails.class);
	    	 intent.putExtra("Test value", testList);
	    	 startActivity(intent);
	     }
	     /**
	      * This navigates back to the Main menu.
	      */
	     public void GoToMain() {
	    	 Intent intent = new Intent(this, MainActivity.class);
	    	 intent.putExtra("Test value", testList);
	    	 startActivity(intent);
	     }
	     /**
	      * This goes to the Create Test Activity to create a new test.
	      */
	     public void createTest() {
	    	 Intent intent = new Intent(this, Settings.class);
	    	 intent.putExtra("Test value", testList);
	    	 startActivity(intent);
	     }
}

