package com.example.realestatemanager.feature.modify

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.repository.property.mapper.PropertyMapper
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.modify.model.ModifyUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val repository: PropertyRepository,
    private val propertyMapper: PropertyMapper,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var currentPhotoUri: List<Uri>? = null
        private set

    var currentTakePhotoUri: Uri? = null
        private set

    private val dateRegex = Regex("^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[0-2])(\\d{4})$")

    private val _data = MutableStateFlow(ModifyUiData())
    val data: StateFlow<ModifyUiData> = _data

    private val _isDateValid = MutableStateFlow(true)
    val isDateValid: StateFlow<Boolean> = _isDateValid

    private val _isSale = MutableStateFlow(data.value.sale)
    val isSale: StateFlow<Boolean> get() = _isSale

    fun loadProperty(propertyId: Int) {
        viewModelScope.launch {
            repository.getPropertyById(propertyId).collect { property ->
                val modifiedData = propertyMapper.toModifyUiData(property)
                _data.value = propertyMapper.toModifyUiData(property)
                _isSale.value = modifiedData.sale
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

    fun onImageChanged(value: List<String>) {
        val currentImages = _data.value.image
        val updatedImages = (currentImages + value).distinct()
        _data.value = _data.value.copy(image = updatedImages)
    }

    fun onDescriptionChanged(value: TextFieldValue) {
        _data.value = data.value.copy(description = value)
    }

    fun onAddressChanged(value: TextFieldValue) {
        _data.value = data.value.copy(address = value)
    }

    fun onSchoolChanged(value: Boolean) {
        _data.value = data.value.copy(school = value)
    }

    fun onShopsChanged(value: Boolean) {
        _data.value = data.value.copy(shops = value)
    }

    /*fun onSaleChanged(value: Boolean) {
        Log.d("VIEWMODEL_CHANGE", "onSaleChanged called with value: $value")
        _data.value = data.value.copy(sale = value)
    }*/
    fun onSaleChanged(isChecked: Boolean) {
        _isSale.value = isChecked
        _data.value = _data.value.copy(sale = isChecked)
    }

    fun onDateOfCreationChanged(value: TextFieldValue) {
        val isValid = value.text.matches(dateRegex)
        _isDateValid.value = isValid
        if (isValid) {
            _data.value = data.value.copy(dateOfCreation = value)
        }
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
                val propertyEntity = propertyMapper.toPropertyEntity(_data.value, propertyId)
                Log.d("MODIFYPROPERTY", "$propertyEntity")
                repository.update(propertyEntity)
                onSuccess()
            } catch (e: Exception) {
                Log.e("ModifyViewModel", "Error updating property: ${e.message}")
            }
        }
    }

    fun takePhoto(context: Context, onPhotoUriReady: (Uri?) -> Unit) {
        currentTakePhotoUri = createImageUri(context)
        onPhotoUriReady(currentTakePhotoUri)
    }

    private fun createImageUri(context: Context): Uri? {
        return try {
            val storageDir = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "my_images"
            )

            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }

            val imageFile = File.createTempFile(
                "IMG_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
            )

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

    fun chooseImageFromGallery(uris: List<Uri>) {
        currentPhotoUri = uris
        val imagePaths = uris.map { it.toString() }
        onImageChanged(imagePaths)
        uris.forEach { takePersistableUriPermission(it) }
    }

    private fun takePersistableUriPermission(uri: Uri) {
        try {
            val takeFlags: Int =
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, takeFlags)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}