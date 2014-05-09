package org.sigil.ui.widget;

import org.sigil.ui.*;
import org.sigil.ui.font.*;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Custom Button implementation
 */
public class Button extends android.widget.Button {

	/**
	 * Constructor 
	 */
	public Button( final Context context ) {
		this( context, null );
	}

	/**
	 * Constructor 
	 */
	public Button( final Context context, final AttributeSet attrs ) {
		this( context, attrs, 0 );
	}

	/**
	 * Constructor 
	 */
	public Button( final Context context, final AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );
		final TypedArray style = context.obtainStyledAttributes( attrs, R.styleable.TextView );
		if ( null != style ) {
			setTypeface( 
					FontLoader.load(
						context, 
						style.getString( R.styleable.TextView_font ),
						style.getInt( R.styleable.TextView_android_textStyle, 0 )));
            style.recycle();
		}
	}
}