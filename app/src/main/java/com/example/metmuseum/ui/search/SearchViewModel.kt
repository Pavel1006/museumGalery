package com.example.metmuseum.ui.search
package com.example.metmuseum.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metmuseum.network.MuseumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val museumRepository: MuseumRepository
) : ViewModel() {

    private val _artObjects = MutableStateFlow<List<ArtObjectApi>>(emptyList())
    val artObjects: StateFlow<List<ArtObjectApi>> = _artObjects

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun searchArtObjects(query: String) {
        if (query.isBlank()) return

        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val results = museumRepository.searchArtObjects(query)
                if (results.isNotEmpty()) {
                    _artObjects.value = results
                } else {
                    _error.value = "Nu au fost găsite obiecte de artă pentru acest termen."
                }
            } catch (e: Exception) {
                _error.value = "A apărut o eroare la căutare: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
