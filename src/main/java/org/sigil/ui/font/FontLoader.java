package org.sigil.ui.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import java.util.*;

/**
 * Class to load and cache fonts
 */
public class FontLoader {
	
	private static final Map<String,Typeface> _fontMap = new HashMap<>(16);

	/**
	 * Loads the requested font if not already loaded
	 *
	 * @param context a Context to access assets
	 * @param path the path to the font
	 */
	public static final Typeface load( final Context context, String path ) {

		if ( null == path || 0 == path.length() ) {
			return null;
		}

		// normalize path
		if ( ! path.startsWith( "fonts/" ) ) {
			path = "fonts/"+path;
		}

		Typeface typeface = null;
		synchronized ( _fontMap ) {
			typeface = _fontMap.get( path );
			if ( null == typeface ) {
				typeface = Typeface.createFromAsset( context.getAssets(), path );
				if ( null != typeface ) {
					_fontMap.put( path, typeface );
				}
			}
		}
		return typeface;
	}
}
