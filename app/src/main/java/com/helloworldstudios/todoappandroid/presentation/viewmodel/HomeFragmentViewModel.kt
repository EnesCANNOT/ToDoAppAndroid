package com.helloworldstudios.todoappandroid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloworldstudios.todoappandroid.data.remote.requests.taskRequests.CreateTaskRequest
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.CreatedTaskResponse
import com.helloworldstudios.todoappandroid.domain.repositories.TaskRepository
import com.helloworldstudios.todoappandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {
    private val _createdTaskResponse: MutableLiveData<Resource<CreatedTaskResponse>> = MutableLiveData<Resource<CreatedTaskResponse>>()
    val createdTaskResponse: LiveData<Resource<CreatedTaskResponse>> = _createdTaskResponse

    fun createTask(request: CreateTaskRequest){
        viewModelScope.launch {
            when(val result = taskRepository.createTask(request)){
                is Resource.Success -> {
                    _createdTaskResponse.value = result
                    println("Success")
                }

                is Resource.Error -> {
                    _createdTaskResponse.value = result
                    println("Error : " + result.message.toString())
                }

                is Resource.Loading -> {
                    _createdTaskResponse.value = result
                    println("Loading")
                }
            }
        }
    }
}