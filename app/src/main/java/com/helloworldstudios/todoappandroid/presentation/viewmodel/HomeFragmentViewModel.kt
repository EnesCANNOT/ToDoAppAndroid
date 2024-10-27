package com.helloworldstudios.todoappandroid.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloworldstudios.todoappandroid.data.remote.responses.taskResponses.GetTaskResponse
import com.helloworldstudios.todoappandroid.domain.repositories.TaskRepository
import com.helloworldstudios.todoappandroid.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(val taskRepository: TaskRepository) : ViewModel() {
    private val _fetchedTaskList: MutableLiveData<Resource<List<GetTaskResponse>>> = MutableLiveData<Resource<List<GetTaskResponse>>>()
    val fetchedTaskList: LiveData<Resource<List<GetTaskResponse>>> = _fetchedTaskList


    fun getAll(){
        viewModelScope.launch {
            when(val result = taskRepository.getAllTasks()){
                is Resource.Success -> {
                    _fetchedTaskList.value = result
                    println("Success")
                }

                is Resource.Loading -> {
                    _fetchedTaskList.value = result
                    println("Loading")
                }

                is Resource.Error -> {
                    _fetchedTaskList.value = result
                    println("Error : " + result.message.toString())
                }
            }
        }
    }
}