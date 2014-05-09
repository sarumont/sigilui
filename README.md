# SigilUI

Various custom Android widgets.

## Custom Font Faces

Android's `TextView` and derivitives do not allow specifying a custom typeface from the layout XML.
This project sub-classes the offending views to allow this.

### Usage

Drop your custom fonts into `assets/fonts`

Add the sigil namespace:

```
<LinearLayout 
	xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:sigil="http://schemas.android.com/apk/res-auto"
```

Use one of the non-broken views:

```
<org.sigil.ui.widget.TextView
	android:text="@string/app_name"
	font="SourceSansPro.ttf" />
```

The font loader will assume your fonts are in `assets/fonts`. You do **not** need to specify
`fonts/` in the name. You may use sub-directories, however:

```
<org.sigil.ui.widget.TextView
	android:text="@string/app_name"
	font="adobe/SourceSansPro.ttf" />
```

# License

SigilUI is licensed under the GPLv3. If you would like to license the library for commercial use,
please contact me.
