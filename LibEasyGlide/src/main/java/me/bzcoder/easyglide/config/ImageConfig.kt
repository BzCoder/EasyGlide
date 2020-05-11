
package me.bzcoder.easyglide.config

import android.widget.ImageView

open class ImageConfig {
    var url: String? = null
        protected set
    var drawableId = 0
        protected set
    var imageView: ImageView? = null
        protected set
    var placeholder = 0
        protected set
    var errorPic = 0
        protected set
}