/*
 * This file is part of sigilui
 *
 * Sigilui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Sigilui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
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
