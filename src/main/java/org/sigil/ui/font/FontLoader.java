package org.sigil.ui.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.Log;
import java.util.*;

/**
 * Class to load and cache fonts
 */
public class FontLoader {

	private static final String TAG = ""+FontLoader.class;
	
	private static final Map<String,Typeface> _fontMap = new HashMap<>(16);

	/**
	 * Loads the requested font if not already loaded
	 *
	 * @param context a Context to access assets
	 * @param path the path to the font
	 * @param textStyle the integer representation of the text style (bold, italic, bolditalic)
	 */
	public static final Typeface load( final Context context, String path, int textStyle ) {

		if ( null == path || 0 == path.length() ) {
			return null;
		}

		// build a postfix 
		String postfix = "";
		if ( ( Typeface.BOLD & textStyle ) == Typeface.BOLD ) {
			postfix = "bold";
		}
		if ( ( Typeface.ITALIC & textStyle ) == Typeface.ITALIC ) {
			postfix += "italic";
		}

		// normalize path
		if ( ! path.startsWith( "fonts/" ) ) {
			path = "fonts/"+path;
		}

		// create path for the styled version
		String styled = null;
		if ( ! "".equals( postfix ) ) {
			styled = "";
			String[] parts = path.split( "\\." );
			for ( int i=0; i<parts.length; i++ ) {
				styled += parts[i];
				if ( i == parts.length-2 ) {
					styled += "-";
					styled += postfix;
					styled += ".";
				}
			}
		}

		Log.d( TAG, "Searching for font: "+path+", styled="+styled+", textStyle="+textStyle );
		Typeface typeface = null;
		synchronized ( _fontMap ) {
			if ( null != styled ) {
				typeface = inflate( context, styled );
			}
			if ( null == typeface ) {
				if ( null != styled ) {
					Log.w( TAG, "Could not find styled variant "+styled+" - falling back to "+path );
				}
				typeface = inflate( context, path );
			}
		}
		return typeface;
	}

	/**
	 * Inflate a font. <b>Must be called within a synchronized block around _fontMap</b>. Caches any
	 * inflated fonts
	 *
	 * @param context a Context to access assets
	 * @param path the path to the font
	 */
	private static Typeface inflate( final Context context, final String path ) {
		Typeface typeface = _fontMap.get( path );
		if ( null == typeface ) {
			try {
				typeface = Typeface.createFromAsset( context.getAssets(), path );
			} catch ( Exception e ) {
				// don't really care
				Log.d( TAG, "Could not inflate "+path );
			}
			if ( null != typeface ) {
				_fontMap.put( path, typeface );
			}
		}
		return typeface;
	}
}
