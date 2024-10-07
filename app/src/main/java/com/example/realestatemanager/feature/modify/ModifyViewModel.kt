package com.example.realestatemanager.feature.modify

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.data.local.property.PropertyMapper
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.modify.model.ModifyUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val repository: PropertyRepository,
    private val propertyMapper: PropertyMapper
): ViewModel() {

    var currentPhotoUri: Uri? = null
        private set

    private val _data = MutableStateFlow(ModifyUiData())
    val data: StateFlow<ModifyUiData> = _data

    fun loadProperty(propertyId: Int) {
        viewModelScope.launch {
            // Collecte du Flow de PropertyEntity
            repository.getPropertyById(propertyId).collect { propertyEntity ->
                // Utilise le PropertyMapper pour mapper l'entité à l'UI
                _data.value = propertyMapper.toModifyUiData(propertyEntity)
            }
        }
    }

    fun onTypeChanged(value: TextFieldValue) {
        _data.value = data.value.copy(type = value)
    }

    fun onPriceChanged(value: TextFieldValue) {
        _data.value = _data.value.copy(price = value)
    }

    fun onSurfaceChanged(value: TextFieldValue) {
        _data.value = data.value.copy(surface = value)
    }

    fun onRoomChanged(value: TextFieldValue) {
        _data.value = data.value.copy(room = value)
    }

    fun onImageChanged(value: String) {
        _data.value = data.value.copy(image = value)
    }

    fun onDescritpionChanged(value: TextFieldValue) {
        _data.value = data.value.copy(description = value)
    }

    fun onAddressChanged(value: TextFieldValue) {
        _data.value = data.value.copy(address = value)
    }

    fun onInterestChanged(value: TextFieldValue) {
        _data.value = data.value.copy(interest = value)
    }

    fun onStatusChanged(value: TextFieldValue) {
        _data.value = data.value.copy(status = value)
    }

    fun onDateOfCreationChanged(value: TextFieldValue) {
        _data.value = data.value.copy(dateOfCreation = value)
    }

    fun onDateOfSoldChanged(value: TextFieldValue) {
        _data.value = data.value.copy(dateOfSold = value)
    }

    fun onAgentChanged(value: TextFieldValue) {
        _data.value = data.value.copy(agent = value)
    }

    fun modifyProperty(propertyId: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                // Utiliser le mapper pour convertir ModifyUiData en PropertyEntity
                val propertyEntity = propertyMapper.toPropertyEntity(_data.value, propertyId)
                repository.update(propertyEntity)
                onSuccess()
            } catch (e: Exception) {
                Log.e("ModifyViewModel", "Error updating property: ${e.message}")
            }
        }
    }

    fun takePhoto(context: Context, onPhotoUriReady: (Uri?) -> Unit) {
        // Create Uri for keep image
        currentPhotoUri = createImageUri(context)
        onPhotoUriReady(currentPhotoUri)
    }

    private fun createImageUri(context: Context): Uri? {
        return try {
            val storageDir = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "my_images"
            )

            // Create repository if doesn't exist
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            // Create unique field for new image
            val imageFile = File.createTempFile(
                "IMG_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
            )

            // Use FileProvider for get a secure Uri
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                imageFile
            )
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}