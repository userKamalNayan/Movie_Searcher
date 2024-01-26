package com.kamalnayan.moviesearcher.ui.bts

import android.view.ViewGroup
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.allViews
import com.kamalnayan.commons.alias.SearchModifierSelectionCallback
import com.kamalnayan.commons.base.BaseBottomSheetFragment
import com.kamalnayan.commons.extensions.px
import com.kamalnayan.commons.modifier.SearchResultModifier
import com.kamalnayan.moviesearcher.databinding.BtsSearchResultModifierBinding

class BtsSearchResultModifier :
    BaseBottomSheetFragment<BtsSearchResultModifierBinding>(BtsSearchResultModifierBinding::inflate) {


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BtsCharacterDataModifier.
         */
        @JvmStatic
        fun newInstance() = BtsSearchResultModifier()
    }

    /**
     * Callback for selection change.
     * invoked when value is changed of [sortAndFilterSelection]
     * @see [sortAndFilterSelection]
     */
    private var selectionChangedListener: SearchModifierSelectionCallback = null

    private val sortingOptions =
        SearchResultModifier::class.sealedSubclasses.map { (it.objectInstance as SearchResultModifier) }
    private var sortAndFilterSelection: SearchResultModifier = SearchResultModifier.SortByDefault
        set(value) {
            field = value
            selectionChangedListener?.invoke(value)
        }

    fun setSelectedModifier(sortAndFilterSelection: SearchResultModifier) {
        this.sortAndFilterSelection = sortAndFilterSelection
    }

    fun setSelectionChangedListener(selectionChangedListener: SearchModifierSelectionCallback) {
        this.selectionChangedListener = selectionChangedListener
    }


    override fun fetchData() {

    }

    override fun setViewModelToBinding() {

    }

    override fun initViews() {
        setupRadioButtons()
        setupSelectedOption()
    }

    /**
     * Creates radio buttons and adds to `radioGroup`
     * Radio buttons are created as many as sorting options are available
     */
    private fun setupRadioButtons() {
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0,12.px,0,0.px)
        }
        sortingOptions.forEach {
            val text = it.getNameResourceId().toStringFromResourceId()
            val radioButton = RadioButton(requireContext())
            radioButton.apply {
                this.text = text
                tag = it.getNameResourceId()
                this.layoutParams = layoutParams
            }
            binding.radioGroup.addView(radioButton)
        }
    }

    /**
     * Marks the radio button as checked for the option
     * that is selected
     */
    private fun setupSelectedOption() {
        val requiredRadioView =
            binding.radioGroup.allViews.find { it.tag == sortAndFilterSelection.getNameResourceId() }
                ?: return
        binding.radioGroup.check(requiredRadioView.id)
    }


    override fun setData() {

    }

    override fun setListeners() {
        with(binding) {
            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                handleSortRadioClick(i)
            }
        }
    }

    private fun handleSortRadioClick(i: Int) {
        val tag =
            binding.radioGroup.findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId).tag
        val selectedOption = sortingOptions.find { it.getNameResourceId() == tag } ?: return
        selectionChangedListener?.invoke(selectedOption)
    }


    override fun setObservers() {

    }
}