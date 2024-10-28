package com.example.p71_notes_real.db

import com.example.p71_notes_real.model.NoteModel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.tasks.await

class FirebaseDatabase {

    companion object {
        private const val DB_NAME = "notes"
        private const val TITLE = "title"
        private const val CONTENT = "content"
    }

    private val firebaseDatabase = Firebase.database
    private val databaseReference = firebaseDatabase.getReference(DB_NAME)

    suspend fun insertNote(noteModel: NoteModel) {
        val id = databaseReference.push().key
        val model = NoteModel(noteModel.title, noteModel.content, id)
        databaseReference.child(id!!).setValue(model).await()
    }

    suspend fun deleteNote(noteModel: NoteModel) {
        databaseReference.child(noteModel.id!!).removeValue().await()
    }

    suspend fun updateNote(noteModel: NoteModel) {
        val updateMap = mapOf(
            TITLE to noteModel.title,
            CONTENT to noteModel.content,
        )
        databaseReference.child(noteModel.id!!).updateChildren(updateMap).await()
    }

    suspend fun readNotes(): List<NoteModel> {
        val list = mutableListOf<NoteModel>()
        val snapshot = databaseReference.get().await()

        for (item in snapshot.children) {
            val model = item.getValue(NoteModel::class.java)

            if (model != null) {
                list += model
            }
        }

        return list
    }

}