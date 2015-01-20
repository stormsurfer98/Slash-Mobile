package me.shreygupta.slash;

import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.content.Intent;

//Author: Andrei Gheorghe (http://github.com/idevelop)
//Editor: Shrey Gupta

public class ASCImage {
    private static String asciiFromCanvas(Drawable drawable) {
        //Characters
        String[] characters = new String[15];
        characters = (" .,:;i1tfLCG08@").split("");

        //Width, height, and ASCII Characters
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        int canvasWidth = ((BitmapDrawable)drawable).getBitmap().getHeight();
        int canvasHeight = ((BitmapDrawable)drawable).getBitmap().getHeight();
        String asciiCharacters = "";

        //Calculate contrast factor
        //int contrastFactor = (259 * (options.contrast + 255)) / (255 * (259 - options.contrast));
        for (int y = 0; y < canvasHeight; y += 2) {
            for (int x = 0; x < canvasWidth; x++) {
                //Get each pixel's brightness and output corresponding character
                HashMap<String, Integer> color = new HashMap<String, Integer>();
                int pixel = bitmap.getPixel(x,y);
                color = getColorAtOffset(pixel);

                //Increase the contrast of the image so that the ASCII representation looks better
                HashMap<String, Double> contrastedColor = new HashMap<String, Double>();
                contrastedColor.put("red", Math.max(Math.min(Math.floor((color.get("red") - 128)) + 128, 255), 0));
                contrastedColor.put("green", Math.max(Math.min(Math.floor((color.get("green") - 128)) + 128, 255), 0));
                contrastedColor.put("blue", Math.max(Math.min(Math.floor((color.get("blue") - 128)) + 128, 255), 0));

                //Calculate pixel brightness
                double brightness = (0.299 * contrastedColor.get("red") + 0.587 * contrastedColor.get("green") + 0.114 * contrastedColor.get("blue")) / 255;
                String character = characters[(characters.length - 1) - Math.round((float)brightness * (characters.length - 1))];
                asciiCharacters += character;
            }
            asciiCharacters += "\n";
        }
        return asciiCharacters;
    }

    private static HashMap<String, Integer> getColorAtOffset(int pixel) {
        HashMap<String, Integer> color = new HashMap<String, Integer>();
        color.put("red", Color.red(pixel));
        color.put("green", Color.blue(pixel));
        color.put("blue", Color.green(pixel));
        return color;
    }
}