package com.example.firstcodedemo.widget.dialog

import android.content.Context
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.PartShadowPopupView

abstract class AbstractSipnnerAttachDialog(context: Context) : PartShadowPopupView(context) {
    /**
     * 设置布局ID
     */

    abstract fun setContentLayout(): Int

    override fun getImplLayoutId(): Int {
        return setContentLayout()
    }


    private val dialog = XPopup.Builder(context).asCustom(this)

    /**
     * 直接调用show()会空指针
     */
    fun showDialog() {

//        dialog.popupInfo.enableDrag = false
        if (!dialog.isShow) {
            println("++++++++++++++++++++")
            dialog.show()
        }
    }

    fun closeDialog() {

//        dialog.popupInfo.enableDrag = true
        if (dialog.isShow) {

            println("====================")
            dialog.dismiss()
        }

    }

    @Deprecated(
        "This method will throws NullPointException,call showDialog() instead",
        ReplaceWith("super.show()", "com.lxj.xpopup.core.BottomPopupView")
    )
    override fun show(): BasePopupView {
        return super.show()
    }

    /**
     * Dismiss监听
     */
    private var onDismissListener: (() -> Unit)? = null

    //    弹框消失
    fun setOnDismissListener(listener: (() -> Unit)) {
        this.onDismissListener = listener
    }

    override fun onDismiss() {
        super.onDismiss()
        onDismissListener?.invoke()
    }
}