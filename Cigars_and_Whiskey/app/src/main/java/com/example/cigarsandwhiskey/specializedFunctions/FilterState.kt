package com.example.cigarsandwhiskey.specializedFunctions

enum class FilterType {
    ALL, BRAND, NAME, COUNTRY
}

data class FilterState(
    val type: FilterType = FilterType.ALL,
    val searchQuery: String = ""
)