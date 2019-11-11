package com.example.firstcodedemo.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import com.example.firstcodedemo.R
import com.example.firstcodedemo.fragment.FragmentA
import com.example.firstcodedemo.fragment.FragmentB
import kotlinx.android.synthetic.main.activity_fragmentshow.*

class ShowFragmentActivity : MyAbsActivity() {

    override fun setContentLayout(): Int = R.layout.activity_fragmentshow

    @SuppressLint("ResourceAsColor")
    override fun initView() {


//        replaceFragment(FragmentA.newInstance())

        switchFragment(FragmentA.newInstance())
        button_openA.setOnClickListener {
//            replaceFragment(FragmentA.newInstance())
            switchFragment(FragmentA.newInstance())
        }

        button_openB.setOnClickListener {
//            replaceFragment(FragmentB.newInstance())
            switchFragment(FragmentB.newInstance())
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        //拿到supportFragmentManager  beginTransaction  传入  要替换加载的fragment
        //点击  调用commit才能  提交  fragment的更改
        //addToBackStack(null)  将加入的Fragment  加入  回退栈中
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.show_fragment, fragment,fragment.javaClass.name)
//        beginTransaction.replace(R.id.show_fragment, fragment)
//        beginTransaction.addToBackStack(null)
        beginTransaction.commit()
    }


    private var currentFragment: Fragment? = null

    private val fragmentList = ArrayList<Fragment>()
    /**
     * 切换碎片（内部调用）
     */
    private fun switchFragment(targetFragment: Fragment) {
        immersiveStatus()
        val transaction = supportFragmentManager.beginTransaction()

        if (!targetFragment.isAdded) {
            if (currentFragment != null) {
                transaction.hide(currentFragment!!)
            }
            transaction.add(R.id.show_fragment, targetFragment, targetFragment.javaClass.name)
            transaction.commit()
        } else {
            transaction.hide(currentFragment!!).show(targetFragment).commit()
        }
        currentFragment = targetFragment
    }

}