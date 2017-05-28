package com.example.android.softkeyboard;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
/**
 * This activity displays the list of APKs we can choose from and handles APK selection
 * @author manavdutta1
 *
 */
public class APKActivity extends ListActivity{
	       public static ArrayList<Test> testList;
	       public static ArrayList<String> testNameList;
	       public static String currApkName;
	       private List pkgAppsList;
	       @Override
	       protected void onCreate(Bundle savedInstanceState) {
	  		 super.onCreate(savedInstanceState);
	  		setContentView(R.layout.apks);
	  		final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
	  		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	  		pkgAppsList = getBaseContext().getPackageManager().queryIntentActivities( mainIntent, 0);
	  		String [] values = new String[pkgAppsList.size()];
	  		Drawable [] ids = new Drawable[pkgAppsList.size()];
	  		int i = 0;
	  		for (Object resolve : pkgAppsList) {
	  			 String object = ((ResolveInfo)resolve).activityInfo.name;
	    		 values[i] = object;
	    		 ids[i] = ((ResolveInfo)resolve).loadIcon(getPackageManager());
	    		 i = i + 1;
	    	 }
	  		ImageHolder.setResids(ids);
	  		Intent intent = getIntent();
		     Bundle extras = intent.getExtras();
		     ListView listview = getListView();
		     testList = (ArrayList<Test>) extras.get("Test value");
		     if(pkgAppsList.size() != 0) {
	  		   MotherAdapter<String> adapter = new MotherAdapter<String>(this, values);
	  		   listview.setAdapter(adapter);  	
	  		 }
	       }
	       /**
	        * This method handles the action bar for this activity
	        */
	       @Override
	  	 public boolean onCreateOptionsMenu(Menu menu) {
	  	        // Inflate the menu; this adds items to the action bar if it is present.
	  	        getMenuInflater().inflate(R.menu.apkfile, menu);
	  	        return true;
	  	 }
	     /**
	     * This records the name of the chosen apk
	     * @param v- the item in listview corresponding to chosen apk
	     */
	     public void recordChoose(View v) {
	    	 int position =  (Integer) v.getTag();
	    	 ResolveInfo currResolveInfo = ((ResolveInfo) pkgAppsList.get(position));
	    	 currApkName = currResolveInfo.activityInfo.packageName;
	     }
	     @Override
	     public boolean onOptionsItemSelected(MenuItem item) {
	         // Handle presses on the action bar items
	         switch (item.getItemId()) {
	             case R.id.mainTravel:
	                 GoToMain();
	                 return true;            
	             default:
	                 return super.onOptionsItemSelected(item);
	         }
	     }
	     /**
	      * This takes us back to the main screen.
	      */
	     public void GoToMain() {
	    	 Intent intent = new Intent(this, MainActivity.class);
	    	 intent.putExtra("Test value", testList);
	    	 startActivity(intent);
	     } 
}
