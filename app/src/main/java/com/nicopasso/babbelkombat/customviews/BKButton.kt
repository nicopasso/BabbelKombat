package com.nicopasso.babbelkombat.customviews

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.Button
import com.nicopasso.babbelkombat.R

class BKButton: Button {

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
        this.background = ContextCompat.getDrawable(context, R.drawable.btn_background)
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
    }

}