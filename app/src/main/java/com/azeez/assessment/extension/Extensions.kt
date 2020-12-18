package com.azeez.assessment.extension

import android.text.Editable


fun Editable?.isValid(): Boolean {
    return !this.isNullOrEmpty()
}
