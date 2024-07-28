package com.easyapps.jetutilslib.models

import android.os.*
import com.easyapps.jetutilslib.database.*
import kotlinx.parcelize.*

@Parcelize
data class Question(
    var number: Int,
    var question: String,
    var a: String,
    var b: String,
    var c: String,
    var d: String,
    var correct: String,
    var folder: Folder
) : Parcelable