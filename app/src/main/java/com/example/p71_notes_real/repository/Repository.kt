package com.example.p71_notes_real.repository

import com.example.p71_notes_real.db.FirebaseDatabase
import com.example.p71_notes_real.model.NoteModel

class Repository {
    private val db = FirebaseDatabase()

    suspend fun insertNote(noteModel: NoteModel) = db.insertNote(noteModel)

    suspend fun deleteNote(noteModel: NoteModel) = db.deleteNote(noteModel)

    suspend fun updateNote(noteModel: NoteModel) = db.updateNote(noteModel)

    suspend fun readNotes() = db.readNotes()

}