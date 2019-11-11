package com.example.firstcodedemo.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

abstract class MyAbsFragment : Fragment(){
    protected abstract fun setContentLayout(): Int

    protected abstract fun initFragment()

    protected abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setContentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    /**
     * --------------------软键盘相关方法--------------------
     */
    //隐藏键盘
    protected fun hideKeyboard() {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(
                activity?.window?.peekDecorView()?.windowToken,
                0
            )
    }

    //弹出键盘
    protected fun showKeyboard() {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }

    /**
     * --------------------开启新界面相关方法--------------------
     */
    protected fun launchActivity(targetActivity: Class<*>) {
        startActivity(Intent(activity, targetActivity))
    }

    protected fun launchActivity(targetActivity: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, targetActivity)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    protected fun launchActivityForResult(targetActivity: Class<*>, requestCode: Int) {
        startActivityForResult(Intent(activity, targetActivity), requestCode)
    }

    protected fun launchActivityForResult(
        targetActivity: Class<*>,
        bundle: Bundle,
        requestCode: Int
    ) {
        val intent = Intent(activity, targetActivity)
        intent.putExtras(bundle)
        startActivityForResult(intent, requestCode)
    }

}