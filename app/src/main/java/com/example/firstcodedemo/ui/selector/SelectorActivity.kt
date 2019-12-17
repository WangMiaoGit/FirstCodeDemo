package com.example.firstcodedemo.ui.selector

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast
import com.example.firstcodedemo.R
import com.example.firstcodedemo.activity.MyAbsActivity


class SelectorActivity : MyAbsActivity() {
    override fun setContentLayout()=R.layout.activity_selector

    private val tags = mutableListOf<String>()
    private val orders = mutableMapOf<String,String>()


    override fun initView() {
        //multiple-choice
        val multipleGroup = SelectorGroup()
        multipleGroup.setChoiceMode(SelectorGroup.MODE_MULTIPLE_CHOICE)
        multipleGroup.setStateListener(MultipleChoiceListener())
        (findViewById<View>(R.id.selector_10) as Selector).setGroup("multiple", multipleGroup)
        (findViewById<View>(R.id.selector_20) as Selector).setGroup("multiple", multipleGroup)
        (findViewById<View>(R.id.selector_30) as Selector).setGroup("multiple", multipleGroup)

        //single-choice
        val singleGroup = SelectorGroup()
        singleGroup.setChoiceMode(SelectorGroup.MODE_SINGLE_CHOICE)
        singleGroup.setStateListener(SingleChoiceListener())
        (findViewById<View>(R.id.single10) as Selector).setGroup("single", singleGroup)
        (findViewById<View>(R.id.single20) as Selector).setGroup("single", singleGroup)
        (findViewById<View>(R.id.single30) as Selector).setGroup("single", singleGroup)

        //order-choice
        val orderGroup = SelectorGroup()
        orderGroup.setStateListener(OrderChoiceListener())
        orderGroup.setChoiceMode(OderChoiceMode())
        (findViewById<View>(R.id.selector_starters_duck) as Selector).setGroup(
            "starters",
            orderGroup
        )
        (findViewById<View>(R.id.selector_starters_pork) as Selector).setGroup(
            "starters",
            orderGroup
        )
        (findViewById<View>(R.id.selector_starters_springRoll) as Selector).setGroup(
            "starters",
            orderGroup
        )
        (findViewById<View>(R.id.selector_main_pizza) as Selector).setGroup("main", orderGroup)
        (findViewById<View>(R.id.selector_main_pasta) as Selector).setGroup("main", orderGroup)
        (findViewById<View>(R.id.selector_soup_mushroom) as Selector).setGroup("soup", orderGroup)
        (findViewById<View>(R.id.selector_soup_scampi) as Selector).setGroup("soup", orderGroup)
        orderGroup.setSelected(true, findViewById<View>(R.id.selector_starters_duck) as Selector)
    }

    /**
     * business logic for single-choice is here
     */
    private inner class SingleChoiceListener : SelectorGroup.StateListener {

        override fun onStateChange(groupTag: String?, tag: String?, isSelected: Boolean) {
            Toast.makeText(this@SelectorActivity, tag!! + " is selected", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * business logic for multiple-choice is here
     */
    private inner class MultipleChoiceListener : SelectorGroup.StateListener {

        override fun onStateChange(groupTag: String?, tag: String?, isSelected: Boolean) {
            if (isSelected) {
                tags.add(tag!!)

            } else {
                tags.remove(tag)
            }
            Toast.makeText(this@SelectorActivity, tags.toString() + " is selected", Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * business logic for order-choice is here
     */
    private inner class OrderChoiceListener : SelectorGroup.StateListener {

        override fun onStateChange(groupTag: String?, tag: String?, isSelected: Boolean) {
            if (isSelected) {
                orders.put(groupTag!!, tag!!)
                Toast.makeText(
                    this@SelectorActivity,
                    orders.toString() + " is selected",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /**
     * extends the choice mode of SelectorGroup by implementing SelectorGroup.ChoiceAction
     * the new choice mode is like the behaviour when ordering by western food menu:one choice for one type
     */
    private inner class OderChoiceMode : SelectorGroup.ChoiceAction {

        override fun onChoose(
            selector: Selector,
            selectorGroup: SelectorGroup,
            stateListener: SelectorGroup.StateListener?
        ) {
            cancelPreSelector(selector, selectorGroup)
            selector.isSelected = true
            stateListener?.onStateChange(selector.getGroupTag(), selector.getSelectorTag(), true)
        }

        private fun cancelPreSelector(selector: Selector, selectorGroup: SelectorGroup) {
            val preSelector = selectorGroup.getPreSelector(selector.getGroupTag())
            if (preSelector != null) {
                preSelector.isSelected = false
            }
        }
    }
}
