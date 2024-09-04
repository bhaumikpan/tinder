package com.example.feature_ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.feature_domain.model.ChildrenResponseModel
import com.example.feature_domain.repository.FeatureRepo
import com.example.network_data.extensions.CoreResult

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
     private val repo: FeatureRepo,
// private val analyticsRepo: AnalyticsRepoImpl
) : ViewModel() {

    private val _items = MutableLiveData<List<ChildrenResponseModel>>()
    val items: LiveData<List<ChildrenResponseModel>> get() = _items

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = repo.getChildrenPostList()) {
                    is CoreResult.OnSuccess -> {
                        Log.d("BMK", "X+SX: ${response.data.size}")
                        _items.postValue(response.data)
                    }

                    is CoreResult.OnError -> {
                        Log.d("BMK", "error: ${response.error?.message}")
                        val error =
                            response.error ?: Throwable("EMPTY ERROR IN RESPONSE: UNKNOWN ERROR")
                        _error.postValue(error.message)
                    }

                    else -> {
                        _error.postValue("UNKNOWN ERROR")
                    }
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }



  /* private fun getTimeAgoString(duration: String): String {
        val minutes = duration.toMinutes()
        val hours = duration.toHours()
        val days = duration.toDays()

        return when {
            minutes < 2 -> "Just now"
            minutes in 2 until 15 -> "a few minutes ago"
            minutes in 15 until 60 -> "$minutes minutes ago"
            hours == 1L -> "1 hour ago"
            hours in 2 until 24 -> "$hours hours ago"
            days == 1L -> "1 day ago"
            else -> "$days days ago"
        }
    }*/
}