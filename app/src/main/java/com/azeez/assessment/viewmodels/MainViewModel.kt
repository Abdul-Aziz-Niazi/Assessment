package com.azeez.assessment.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azeez.assessment.daos.requests.LoginRequest
import com.azeez.assessment.daos.responses.APIResponse
import com.azeez.assessment.daos.responses.AuthResponse
import com.azeez.assessment.daos.responses.ItemResponse
import com.azeez.assessment.repository.BaseRepository
import com.azeez.assessment.ui.fragments.ListItemFragment
import com.azeez.assessment.ui.fragments.ViewDetailsFragment

class MainViewModel(private val repository: BaseRepository) : ViewModel() {

    private lateinit var token: String
    lateinit var username: String

    val navigator = MutableLiveData<String>()
    val updateToolbar = MutableLiveData<String>()


    fun moveToViewDetails() {
        navigator.postValue(ViewDetailsFragment::class.java.simpleName)
    }

    fun moveToListScreen() {
        navigator.postValue(ListItemFragment::class.java.simpleName)
    }

    fun login(username: String, password: String): MutableLiveData<APIResponse<AuthResponse>> {
        val mediatorLiveData = MediatorLiveData<APIResponse<AuthResponse>>()
        mediatorLiveData.addSource(repository.login(LoginRequest(username, password))) {
            if (it.isSuccessful) {
                this.username = username
                token = it.data?.token ?: ""
            }
            mediatorLiveData.postValue(it)
        }
        return mediatorLiveData
    }

    fun getItems(): MutableLiveData<APIResponse<ItemResponse>> {
        return repository.getItems(token)
    }

    class MainViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(BaseRepository::class.java)
                .newInstance(repository)
        }
    }
}