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
	font="sourcesanspro.ttf" />
```

The font loader will assume your fonts are in `assets/fonts`. You do **not** need to specify
`fonts/` in the name. You may use sub-directories, however:

```
<org.sigil.ui.widget.TextView
	android:text="@string/app_name"
	font="adobe/sourcesanspro.ttf" />
```

### Styles

You may use custom fonts in a custom style, too:

```
<style name="Button">
	<item name="font">sourcesanspro.ttf</item>
</style>
```

Notice that you do not need the XML namespace here.

### Text Styling

You can also use the `android:textStyle` attributes, but you must provide fonts as Android cannot
fake bold and italic for custom fonts. Using SigilUI, you can still use `android:textStyle` and the
same `sigil:font` definition, so long as you provide buold, italic, and bolditalic versions of the
fonts.

The font loader will automatically look for `fontname-bold`, `fontname-italic`, and
`fontname-bolditalic` in `assets/fonts`: 

```
assets/
  fonts/
    sourcesanspro.ttf
	sourcesanspro-bold.ttf
	sourcesanspro-italic.ttf
	sourcesanspro-bolditalic.ttf
```

# License

SigilUI is licensed under the GPLv3. If you would like to license the library for commercial use,
please contact me.
