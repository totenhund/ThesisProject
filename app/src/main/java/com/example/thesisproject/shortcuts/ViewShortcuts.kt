package com.example.thesisproject.shortcuts

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar


fun View.showLongSnackbar(@StringRes text: Int) =
    Snackbar.make(this, text, Snackbar.LENGTH_LONG)
        .show()


fun View.showShortSnackbar(@StringRes text: Int) =
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT)
        .show()