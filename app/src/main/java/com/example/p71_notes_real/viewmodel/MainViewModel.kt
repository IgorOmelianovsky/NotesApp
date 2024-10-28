package com.example.p71_notes_real.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p71_notes_real.model.NoteModel
import com.example.p71_notes_real.repository.Repository
import com.example.p71_notes_real.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _currentList = MutableLiveData<Resource>()
    val currentList: LiveData<Resource> get() = _currentList

    fun insertNote(noteModel: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(noteModel)
    }

    fun deleteNote(noteModel: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(noteModel)
    }

    fun updateNote(noteModel: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(noteModel)
    }

    fun readNotes() = viewModelScope.launch(Dispatchers.IO) {
        _currentList.postValue(Resource.Loading)
        try {
            val list = repository.readNotes()
            _currentList.postValue(Resource.Success(list))
        } catch (exception: Exception) {
            exception.message?.let {
                _currentList.postValue(Resource.Error(it))
            }
        }
    }

}