package com.example.android.softkeyboard;

import android.graphics.drawable.Drawable;
/**
 * This holds a list of drawables for the APK activity to use.
 * @author manavdutta1
 *
 */
public class ImageHolder {
    private static Drawable[] resids;
    /**
     * This sets the drawable array used.
     * @param array- the array containing the drawables we have.
     */
    public static void setResids(Drawable [] array) {
    	resids = array;
    }
    /**
     * This returns the current drawable array.
     * @return the current drawable array
     */
    public static Drawable[] getResids() {
    	return resids;
    }
}
