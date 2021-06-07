package com.marko.githubapp.util

import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView

/**
 * Extension function for MaterialTextView that handles it's visibility by input value.
 * If input value is null -> hide the view
 * If input value is not null -> set input value as text and show the view
 */
fun MaterialTextView.handleVisibilityByInput(value: String?) {
    if (value == null) {
        this.isVisible = false
    } else {
        this.text = value
        this.isVisible = true
    }
}