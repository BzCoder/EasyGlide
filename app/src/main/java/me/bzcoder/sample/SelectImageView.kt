package me.bzcoder.sample

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * 选择时会变成半透明的ImageView
 * @author : BaoZhou
 * @date : 2019/3/20 10:01
 */
class SelectImageView : AppCompatImageView {
    private var enableState = true
    private var pressedAlpha = 0.4f
    private var unableAlpha = 0.3f

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (enableState) {
            alpha = if (isPressed) {
                pressedAlpha
            } else if (!isEnabled) {
                unableAlpha
            } else {
                1.0f
            }
        }
    }

    fun enableState(enableState: Boolean): SelectImageView {
        this.enableState = enableState
        return this
    }

    fun pressedAlpha(pressedAlpha: Float): SelectImageView {
        this.pressedAlpha = pressedAlpha
        return this
    }

    fun unableAlpha(unableAlpha: Float): SelectImageView {
        this.unableAlpha = unableAlpha
        return this
    }
}