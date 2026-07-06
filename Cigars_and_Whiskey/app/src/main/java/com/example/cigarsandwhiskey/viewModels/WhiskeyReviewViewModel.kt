package com.example.cigarsandwhiskey.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.objects.WhiskeyReviews
import com.example.cigarsandwhiskey.specializedFunctions.FilterState
import com.example.cigarsandwhiskey.specializedFunctions.FilterType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class WhiskeyReviewViewModel(private val whiskeyReviewDao: WhiskeyReviewDao) : ViewModel() {

    private val _currentFilter = MutableStateFlow(FilterState())

    // TIPS: Implementing the Switchboard, which will listen to currentFilter and swaps
    //  DAO queries automatically
    @OptIn(ExperimentalCoroutinesApi::class)
    val reviewList: StateFlow<List<WhiskeyReviews?>> = _currentFilter
        .flatMapLatest { filter ->
            when (filter.type) {
                FilterType.ALL -> whiskeyReviewDao.getAllWhiskeyReviews()
                FilterType.BRAND -> whiskeyReviewDao.getAllWhiskeyBrandReviews(filter.searchQuery)
                FilterType.NAME -> whiskeyReviewDao.getAllWhiskeyReviews(filter.searchQuery)
                FilterType.COUNTRY -> whiskeyReviewDao.getAllWhiskeyOriginReviews(filter.searchQuery)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateFilter(type: FilterType, query: String = ""){
        _currentFilter.value = FilterState(type, query)
    }

    companion object {
        fun provideFactory(dao: WhiskeyReviewDao): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(WhiskeyReviewViewModel::class.java)) {
                    return WhiskeyReviewViewModel(dao) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}