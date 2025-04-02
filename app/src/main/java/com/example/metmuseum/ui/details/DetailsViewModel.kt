package com.example.metmuseum.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.metmuseum.network.ArtDetails
import com.example.metmuseum.network.MuseumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class DetailsViewModel(private val elementsRepository: MuseumRepository, savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val artObjectId: Int = savedStateHandle.toRoute<DetailsRoute>().artObjectId

    private val _details: MutableStateFlow<ArtDetails> = MutableStateFlow(ArtDetails())
    val details: StateFlow<ArtDetails> = _details.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _details.update {
                try {
                    elementsRepository.getArtDetails(objectId = artObjectId) ?: ArtDetails()
                } catch (e: Exception) {
                    ArtDetails()
                }
            }
        }
    }
}