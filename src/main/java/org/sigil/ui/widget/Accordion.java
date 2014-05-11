package org.sigil.ui.widget;

import android.app.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.util.*;

/**
 * Class to manage an accordion
 */
public class Accordion implements View.OnClickListener {

	private static final String TAG = ""+Accordion.class;

	protected final Activity _activity;
	protected final int _scrollViewId;
	protected final int _expandId;
	protected final int _contractId;
	protected final Map<Button,Integer> _buttons;

	/**
	 * Constructor 
	 *
	 * @param activity the parent activity holding the accordion
	 */
	public Accordion( Activity activity ) {
		this( activity, -1 );
	}

	/**
	 * Constructor 
	 *
	 * @param activity the parent activity holding the accordion
	 * @param scrollViewId the optional ID of a parent ScrollView. If provided, expanding an element
	 * will scroll to the top of the button activating the element
	 */
	public Accordion( Activity activity, int scrollViewId ) {
		this( activity, scrollViewId, -1, -1 );
	}

	/**
	 * Constructor 
	 *
	 * @param activity the parent activity holding the accordion
	 * @param scrollViewId the optional ID of a parent ScrollView. If provided, expanding an element
	 * will scroll to the top of the button activating the element
	 * @param expandId resource ID for an icon to display on the left of a button which can be
	 * expanded
	 * @param contractId resource ID for an icon to display on the left of a button which can be
	 * contracted
	 */
	public Accordion( Activity activity, int scrollViewId, int expandId, int contractId ) {
		_activity = activity;
		_buttons = new HashMap<>(16);
		_scrollViewId = scrollViewId;
		_expandId = expandId;
		_contractId = contractId;
	}

	/**
	 * Adds a button to the accordion
	 *
	 * @param buttonViewId the ID of the button view
	 * @param viewId the ID of the associated view
	 */
	public void add( int buttonViewId, int viewId ) {
		Button b = (Button)_activity.findViewById( buttonViewId );
		if ( null == b ) {
			throw new IllegalStateException( "Cannot find Button with ID "+buttonViewId );
		}
		_buttons.put( b, viewId );
		b.setOnClickListener( this );
	}

	/**
	 * Called when a button is clicked
	 */
	@Override
	public void onClick( View view ) {
		Integer id = _buttons.get( view );
		if ( null == id ) {
			Log.w( TAG, "Could not find view matching "+view );
			return;
		}
		View v = _activity.findViewById( id );
		if ( null == v ) {
			Log.w( TAG, "Could not find view with ID "+id );
			return;
		}
		ScrollView scrollView = null;
		if ( -1 != _scrollViewId ) {
			scrollView = (ScrollView)_activity.findViewById( _scrollViewId );
			if ( null == scrollView ) {
				Log.w( TAG, "Could not find ScrollView for ID "+_scrollViewId );
			}
		}

		Button button = (Button)view;
		int[] loc = new int[2];
		view.getLocationOnScreen( loc );
		if ( v.getVisibility() == View.VISIBLE ) {
			// hide it
			v.setVisibility( View.GONE );
			if ( -1 != _expandId ) {
				button.setCompoundDrawablesWithIntrinsicBounds( _expandId, 0, 0, 0 );
			}

			// don't scroll if hiding all items
			loc[0] = -1;
		} else {

			// show it
			v.setVisibility( View.VISIBLE );
			if ( -1 != _contractId ) {
				button.setCompoundDrawablesWithIntrinsicBounds( _contractId, 0, 0, 0 );
			}

			// hide the rest
			for ( Map.Entry<Button,Integer> entry : _buttons.entrySet()) {

				Button b = entry.getKey();
				if ( b == button ) {
					continue;
				}

				View v2 = _activity.findViewById( entry.getValue() );
				if ( null == v2 ) {
					Log.w( TAG, "Could not find view with ID "+entry.getValue() );
					continue;
				}
				v2.setVisibility( View.GONE );
				if ( -1 != _expandId ) {
					b.setCompoundDrawablesWithIntrinsicBounds( _expandId, 0, 0, 0 );
				}
			}
		}

		Log.i( TAG, "onClick(): scrollview="+scrollView+", x="+loc[0]+", y="+loc[1] );
		if ( null != scrollView && loc[0] > -1 ) {
			final ScrollView sv = scrollView;
			final int y = loc[1];
			scrollView.post( new Runnable() { public void run() { sv.smoothScrollTo( 0, y ); } });
		}
	}

}
