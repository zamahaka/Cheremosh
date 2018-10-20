package org.zamahaka.cheremosh.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.View

abstract class ViewLifecycleFragment : Fragment() {
    var viewLifecycleOwner: ViewLifecycleOwner? = null

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner = ViewLifecycleOwner().apply {
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        }
    }

    override fun onStart() {
        super.onStart()
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onStop() {
        super.onStop()
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner?.lifecycle?.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        viewLifecycleOwner = null
    }


    class ViewLifecycleOwner : LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)

        override fun getLifecycle() = lifecycleRegistry
    }

}