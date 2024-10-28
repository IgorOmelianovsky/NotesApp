package com.example.p71_notes_real.util

import com.example.p71_notes_real.model.NoteModel

sealed class Resource {
    data object Loading : Resource()
    data class Success(val noteList: List<NoteModel>) : Resource()
    data class Error(val message: String) : Resource()
}