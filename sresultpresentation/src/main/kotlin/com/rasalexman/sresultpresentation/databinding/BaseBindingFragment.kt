package com.rasalexman.sresultpresentation.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.rasalexman.sresultpresentation.extensions.setupBinding
import com.rasalexman.sresultpresentation.fragments.BaseFragment
import com.rasalexman.sresultpresentation.viewModels.BaseContextViewModel

abstract class BaseBindingFragment<B : ViewDataBinding, VM : BaseContextViewModel> : BaseFragment<VM>(),
    IBaseBindingFragment<B, VM> {

    override var currentBinding: B? = null
    override val binding: B
            get() = currentBinding ?: throw NullPointerException("Binding is not initialized")

    /**
     *
     */
    override val needCallPending: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setupBindingView(inflater, container)
    }

    override fun setupBindingView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        return setupBinding(inflater, container, needCallPending)
    }

    /** starts in onCreateView */
    override fun initBinding(binding: B) = Unit

    override fun onDestroyView() {
        currentBinding?.unbind()
        currentBinding = null
        super.onDestroyView()
    }
}