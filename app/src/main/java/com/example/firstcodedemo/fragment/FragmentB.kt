package com.example.firstcodedemo.fragment


import android.os.Bundle
import com.example.firstcodedemo.R

class FragmentB : MyAbsFragment() {

    companion object {
        fun newInstance(): FragmentB {
            return FragmentB().apply {
                arguments = Bundle()
            }
        }

        fun newInstance(args: Bundle): FragmentB {
            return FragmentB().apply {
                arguments = Bundle(args)
            }
        }
    }

    override fun setContentLayout(): Int = R.layout.fragment_b

    override fun initFragment() {

    }

    override fun initView() {

    }

}