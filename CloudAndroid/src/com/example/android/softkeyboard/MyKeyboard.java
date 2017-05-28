package com.example.android.softkeyboard;
import android.annotation.TargetApi;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.InputType;
import android.text.method.MetaKeyKeyListener;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;

import java.util.ArrayList;
import java.util.List;
/**
 * This is a special keyboard that records all keys pressed using it.
 * @author manavdutta1
 *
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE) public class MyKeyboard extends SoftKeyboard {
    static final boolean DEBUG = false;
    static final boolean PROCESS_HARD_KEYS = true;
    private static ArrayList<Key> keysPressed = new ArrayList<Key>();
    private static ArrayList<Integer> primaryCodes = new ArrayList<Integer>();
    private InputMethodManager mInputMethodManager;
    private LatinKeyboardView mInputView;
    private CandidateView mCandidateView;
    private CompletionInfo[] mCompletions;
    private static ArrayList<String> keyEvents = new ArrayList<String>();
    private StringBuilder mComposing = new StringBuilder();
    private boolean mPredictionOn;
    private boolean mCompletionOn;
    private int mLastDisplayWidth;
    private boolean mCapsLock;
    private long mLastShiftTime;
    private long mMetaState;
    private List<Keyboard.Key> qwertyKeys;
    private List<Keyboard.Key> noKeys;
    private List<Keyboard.Key> someKeys;
	@TargetApi(Build.VERSION_CODES.CUPCAKE) @Override
	public void onCreate() {
        super.onCreate();
        mInputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        mWordSeparators = getResources().getString(R.string.word_separators);
    }
	  @Override public void onInitializeInterface() {
	        int displayWidth = getMaxWidth();
	        if (displayWidth == mLastDisplayWidth) return;
	        mLastDisplayWidth = displayWidth;
	        setmQwertyKeyboard(new LatinKeyboard(this, R.xml.qwerty));
	        qwertyKeys = mQwertyKeyboard.getKeys();
	        setmSymbolsKeyboard(new LatinKeyboard(this, R.xml.symbols));
	        setmSymbolsShiftedKeyboard(new LatinKeyboard(this, R.xml.symbols_shift));
	        noKeys = mSymbolsKeyboard.getKeys();
	        someKeys = mSymbolsShiftedKeyboard.getKeys();
	    }
	  @Override
	  public boolean onEvaluateInputViewShown() {
		     return true;
		}
	  private Keyboard.Key findKey(int primaryCode) {
		  for (Keyboard.Key key : qwertyKeys) {
			if (key != null) {
			   int[] codesList = key.codes;
			   for (int code: codesList) {
				   if ((primaryCode - code) == 0) {
					   return key;
				   }
				   
			   }
		     }
		  }
		  for (Keyboard.Key key : noKeys) {
			  if (key != null) {
				   int[] codesList = key.codes;
				   for (int code: codesList) {
					   if ((primaryCode - code) == 0) {
						   return key;
					   }
					   
				   }
			     }
		  }
		  for (Keyboard.Key key : someKeys) {
			  if (key != null) {
				   int[] codesList = key.codes;
				   for (int code: codesList) {
					   if ((primaryCode - code) == 0) {
						   return key;
					   }
					   
				   }
			     }
		  }
		  return null;
	  }
	  /**
	   * This button records the keys pressed.
	   */
	  public void onPress(int primaryCode) {
		  Key key = findKey(primaryCode);
		  if  (key != null) {
			keysPressed.add(key);
			if (key.label == null) {
				Log.i("Button pressed: ", key.toString());
				keyEvents.add(key.toString());
			}
			else{
	          Log.i("Button pressed: ", key.label.toString());
	          keyEvents.add("Button pressed: " + key.label.toString());
			}
		  }
		  primaryCodes.add(primaryCode);
	  }
	  public static void clearKeys() {
		  keysPressed.clear();
	  }
	  public static void clearKeyEvents() {
		     keyEvents.clear();
	  }
	  public static ArrayList<Key> getKeys() {
		     return keysPressed;
	  }
	  public static ArrayList<String> getKeyEvents() {
	         return keyEvents;
	  }
	  /**
	   * Logs the release of a key
	   */
	  public void onRelease(int primaryCode) {
		 if (findKey(primaryCode) != null) {
		   if (findKey(primaryCode).label != null) {
		    Log.i("Button released: ", (findKey(primaryCode).label).toString());  
	       }
		   else {
			   Log.i("Button released: ", (findKey(primaryCode).text.toString()));
		   }
	  }
}
}
	