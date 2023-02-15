package com.example.myapplication_0125.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application)!!.userDao()
        repository = UserRepository(userDao)
        readAllData = repository.getUserAll
    }

    fun getIdUser(email: String): LiveData<User>? {
        var data: LiveData<User>? = null
        viewModelScope.launch(Dispatchers.IO) {
            data = repository.getIdUser(email)
        }
        return data
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

    class Factory(val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(application) as T
        }
    }

}