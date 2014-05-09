package org.sigil.ui.widget;

import org.sigil.ui.*;
import org.sigil.ui.font.*;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;


/**
 * Custom TextView implementation
 */
public class TextView extends android.widget.TextView {

	/**
	 * Constructor 
	 */
	public TextView( final Context context ) {
		this( context, null );
	}

	/**
	 * Constructor 
	 */
	public TextView( final Context context, final AttributeSet attrs ) {
		this( context, attrs, 0 );
	}

	/**
	 * Constructor 
	 */
	public TextView( final Context context, final AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );

		final TypedArray style = context.obtainStyledAttributes( attrs, R.styleable.TextView );
		if ( null != style ) {

			final String path = style.getString( R.styleable.TextView_font );
			final Typeface typeface = FontLoader.load( context, path );
			if ( null != typeface ) {
                setTypeface(typeface);
			}
            style.recycle();
		}
	}
}
