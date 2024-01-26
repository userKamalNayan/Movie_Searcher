package com.kamalnayan.moviesearcher.ui.bts

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
     * invoked when value is changed of [selectedSortOption]
     * @see [selectedSortOption]
     */
    private var selectionChangedListener: SearchModifierSelectionCallback = null

    private val sortingOptions =
        SearchResultModifier::class.sealedSubclasses.map { (it.objectInstance as SearchResultModifier) }
    private var selectedSortOption: SearchResultModifier = SearchResultModifier.SortByDefault
        set(value) {
            field = value
            selectionChangedListener?.invoke(value)
        }

    fun setSelectedModifier(searchResultModifier: SearchResultModifier) {
        this.selectedSortOption = searchResultModifier
    }

    fun setSelectionChangedListener(selectionChangedListener: SearchModifierSelectionCallback) {
        this.selectionChangedListener = selectionChangedListener
    }


    override fun fetchData() {
        // do nothing
    }

    override fun setViewModelToBinding() {
        // do nothing
    }

    override fun initViews() {
        setupRadioButtons()
        setupSelectedOption()
    }

    override fun setData() {
        // do nothing
    }

    override fun setListeners() {
        with(binding) {
            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                handleSortRadioClick()
            }
        }
    }

    override fun setObservers() {

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
            setMargins(4.px, 12.px, 4.px, 0.px)
        }

        sortingOptions.forEach {
            val title = it.getTitleResourceId().toStringFromResourceId()
            val radioButton = RadioButton(requireContext())
            radioButton.apply {
                this.text = title
                tag = it.getTitleResourceId()
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
            binding.radioGroup.allViews.find { it.tag == selectedSortOption.getTitleResourceId() }
                ?: return
        binding.radioGroup.check(requiredRadioView.id)
    }


    /**
     * Gets the selected radio button and updates [selectedSortOption]
     */
    private fun handleSortRadioClick() {
        val selectedRadioId= binding.radioGroup.checkedRadioButtonId
        val selectedRadioTag =
            binding.radioGroup.findViewById<RadioButton>(selectedRadioId).tag
        val selectedOption = sortingOptions.find { it.getTitleResourceId() == selectedRadioTag } ?: return
        selectedSortOption = selectedOption
    }
}