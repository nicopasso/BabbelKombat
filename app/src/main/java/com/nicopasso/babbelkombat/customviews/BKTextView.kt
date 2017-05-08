package com.nicopasso.babbelkombat.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class BKTextView: TextView {

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr) {
        init()
    }

    fun init() {
        this.typeface = Typeface.createFromAsset(context.assets, "fonts/mk1.ttf")
    }

}