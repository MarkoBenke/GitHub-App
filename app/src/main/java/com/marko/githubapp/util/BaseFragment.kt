package com.marko.githubapp.util

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.marko.githubapp.R

abstract class BaseFragment : Fragment() {

    fun showErrorDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.unknown_error_title))
            .setMessage(getString(R.string.unknown_error_message))
            .setPositiveButton(getString(R.string.okay)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }
}