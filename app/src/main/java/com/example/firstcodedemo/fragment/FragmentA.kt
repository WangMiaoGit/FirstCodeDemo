package com.example.firstcodedemo.fragment


import android.os.Bundle
import com.example.firstcodedemo.R

class FragmentA : MyAbsFragment(){


    companion object {
        fun newInstance(): FragmentA {
            return FragmentA().apply {
                arguments = Bundle()
            }
        }

        fun newInstance(args: Bundle): FragmentA {
            return FragmentA().apply {
                arguments = Bundle(args)
            }
        }
    }


    override fun setContentLayout(): Int= R.layout.fragment_a

    override fun initFragment() {

    }

    override fun initView() {
    }

}