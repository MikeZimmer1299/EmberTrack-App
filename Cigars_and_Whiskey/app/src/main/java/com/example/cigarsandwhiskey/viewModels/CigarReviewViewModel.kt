package com.example.cigarsandwhiskey.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cigarsandwhiskey.dataAccessObjects.CigarReviewDao
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.specializedFunctions.FilterState
import com.example.cigarsandwhiskey.specializedFunctions.FilterType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class CigarReviewViewModel(private val cigarReviewDao: CigarReviewDao) : ViewModel() {

    private val _currentFilter = MutableStateFlow(FilterState())

    @OptIn(ExperimentalCoroutinesApi::class)
    val reviewList: StateFlow<List<CigarReviews?>> = _currentFilter
        .flatMapLatest { filter ->
            when (filter.type) {
                FilterType.ALL -> cigarReviewDao.getAllCigarReviews()
                FilterType.BRAND -> cigarReviewDao.getAllBrandReviews(filter.searchQuery)
                FilterType.NAME -> cigarReviewDao.getAllCigarNameReviews(filter.searchQuery)
                FilterType.COUNTRY -> cigarReviewDao.getAllCountryReviews(filter.searchQuery)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000),
            initialValue = emptyList()
        )

    fun updateFilter(type: FilterType, query: String = ""){
        _currentFilter.value = FilterState(type, query)
    }

    companion object {
        fun provideFactory(dao: CigarReviewDao): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CigarReviewViewModel::class.java)) {
                    return CigarReviewViewModel(dao) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}