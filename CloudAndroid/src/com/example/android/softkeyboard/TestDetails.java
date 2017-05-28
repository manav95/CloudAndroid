package com.example.android.softkeyboard;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * This activity displays all details of all tests that we have.
 * @author manavdutta1
 *
 */
public class TestDetails extends ListActivity{
       private ArrayList<String> testingList;
       private ArrayList<Test> testsList;
	   @Override
       protected void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	  		setContentView(R.layout.tests);
	  		Intent intent = getIntent();
		     Bundle extras = intent.getExtras();
		     ListView listview = getListView();
		     testingList = new ArrayList<String>();
		     testsList = (ArrayList<Test>) extras.get("Test value");
		     for (Test test : testsList) {
		    	 testingList.add(test.toString());
		     }
		     if(testingList.size() != 0) {
	  		   DetailsAdapter<String> adapter = new DetailsAdapter<String>(this, testingList.toArray(new String[1]));
	  		   listview.setAdapter(adapter);  	
	  		 }
	  }
       @Override
  	 public boolean onCreateOptionsMenu(Menu menu) {
  	        // Inflate the menu; this adds items to the action bar if it is present.
  	        getMenuInflater().inflate(R.menu.testing, menu);
  	        return true;
  	 }
       @Override
       public boolean onOptionsItemSelected(MenuItem item) {
           // Handle presses on the action bar items
           switch (item.getItemId()) {
               case R.id.test:
                   GoToTests();
                   return true;
               default:
                   return super.onOptionsItemSelected(item);
           }
       }
       /**
        * This navigates back to the test menu.
        */
	     public void GoToTests() {
	    	 Intent intent = new Intent(this, Tests.class);
	    	 intent.putExtra("Test value", testsList);
	    	 startActivity(intent);
	     }
       
}
