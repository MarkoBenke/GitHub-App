package com.marko.githubapp.util

import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.handleVisibilityByInput(value: String?) {
    if (value == null) {
        this.isVisible = false
    } else {
        this.text = value
        this.isVisible = true
    }
}