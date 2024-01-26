package com.kamalnayan.commons.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kamalnayan.commons.extensions.Empty
import com.kamalnayan.commons.utils.autoCleared


abstract class BaseFragment<B : ViewDataBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B
) : Fragment() {

    protected var binding by autoCleared<B>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = bindingInflater.invoke(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()
        initViews()
        setViewModelToBinding()
        setData()
        setListeners()
        setObservers()
    }


    abstract fun fetchData()
    /**
     * Used to set view model to view binding
     */
    abstract fun setViewModelToBinding()

    abstract fun initViews()

    abstract fun setData()

    abstract fun setListeners()

    abstract fun setObservers()


    protected fun Int.toStringFromResourceId(): String =
        context?.resources?.getString(this) ?: String.Empty

    protected fun Int.toColor() = ContextCompat.getColor(requireContext(), this)

}