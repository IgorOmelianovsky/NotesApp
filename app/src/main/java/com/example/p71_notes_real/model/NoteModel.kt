package com.example.p71_notes_real.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NoteModel(
    val title: String? = null,
    val content: String? = null,
    val id: String? = null,
) : Parcelable
