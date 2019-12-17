package com.example.firstcodedemo.ui.selector

import android.text.method.TextKeyListener.clear




class SelectorGroup {

    companion object{
        val MODE_SINGLE_CHOICE = 1
        val MODE_MULTIPLE_CHOICE = 2
    }


    private var choiceMode: ChoiceAction? = null
    private var onStateChangeListener: StateListener? = null
    /**
     * a map to keep previous selected selector
     */
    private val selectorMap = mutableMapOf<String,Selector>()

    /**
     * customized an choice mode by yourself
     *
     * @param choiceMode
     */
    fun setChoiceMode(choiceMode: ChoiceAction) {
        this.choiceMode = choiceMode
    }

    /**
     * set a default choice mode
     *
     * @param mode
     */
    fun setChoiceMode(mode: Int) {
        when (mode) {
            MODE_MULTIPLE_CHOICE -> choiceMode = MultipleAction()
            MODE_SINGLE_CHOICE -> choiceMode = SingleAction()
        }
    }

    fun setStateListener(onStateChangeListener: StateListener) {
        this.onStateChangeListener = onStateChangeListener
    }

    /**
     * get the selector which clicked last time by the specific group tag
     *
     * @param groupTag a tag which the previous selector belongs to
     * @return
     */
    fun getPreSelector(groupTag: String?): Selector? {
        return selectorMap.get(groupTag)
    }

    /**
     * toggle or cancel one choice
     *
     * @param selected
     * @param selector
     */
    fun setSelected(selected: Boolean, selector: Selector?) {
        if (selector == null) {
            return
        }
        if (selected) {
            //keep click selector in map
            selectorMap[selector.getGroupTag()!!] = selector
        }
        selector.isSelected = selected
        if (onStateChangeListener != null) {
            onStateChangeListener!!.onStateChange(
                selector.getGroupTag(),
                selector.getSelectorTag(),
                selected
            )
        }
    }

    /**
     * cancel selected state of one Selector when another is selected
     *
     * @param selector the Selector which is selected right now
     */
    private fun cancelPreSelector(selector: Selector) {
        val groupTag = selector.getGroupTag()
        val preSelector = getPreSelector(groupTag)
        if (preSelector != null) {
            preSelector.isSelected = false
        }
    }

    /**
     * add extra layer which means more complex
     *
     * @param selector
     */
    fun onSelectorClick(selector: Selector) {
        if (choiceMode != null) {
            choiceMode!!.onChoose(selector, this, onStateChangeListener)
        }
        //keep click selector in map
        selectorMap[selector.getGroupTag()!!] = selector
    }

    fun clear() {
        if (selectorMap != null) {
            selectorMap.clear()
        }
    }

    interface ChoiceAction {
        /**
         * invoked when one selector is clicked
         *
         * @param selector      the clicked selector
         * @param selectorGroup
         * @param stateListener
         */
        fun onChoose(
            selector: Selector,
            selectorGroup: SelectorGroup,
            stateListener: StateListener?
        )
    }

    /**
     * pre-defined choice mode: previous choice will be canceled if there is a new choice
     */
    private inner class SingleAction : ChoiceAction {

        override fun onChoose(
            selector: Selector,
            selectorGroup: SelectorGroup,
            stateListener: StateListener?
        ) {
            cancelPreSelector(selector)
            setSelected(true, selector)
        }
    }

    /**
     * pre-defined choice mode: all choices will be preserved
     */
    private inner class MultipleAction : ChoiceAction {

        override fun onChoose(
            selector: Selector,
            selectorGroup: SelectorGroup,
            stateListener: StateListener?
        ) {
            val isSelected = selector.isSelected
            setSelected(!isSelected, selector)
        }
    }

    interface StateListener {
        fun onStateChange(groupTag: String?, tag: String?, isSelected: Boolean)
    }
}