package com.example.realestatemanager.feature.edit

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    val repository: PropertyRepository,
    @ApplicationContext private val context: Context
): ViewModel() {

    var currentPhotoUri: List<Uri>? = null
        private set

    var currentTakePhotoUri: Uri? = null
        private set

    private val _data = MutableStateFlow(EditUiData())
    val data: StateFlow<EditUiData> = _data

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
        _data.value = data.value.copy(image = _data.value.image + value)
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

    fun onSaleChanged(value: Boolean) {
        _data.value = data.value.copy(sale = value)
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

    fun saveProperty(onSuccess:() -> Unit = {}){
        viewModelScope.launch {
            try {
                val propertyEntity = PropertyEntity(
                    id = 0,
                    type = _data.value.type.text.ifBlank { null },
                    price = _data.value.price.text.toLongOrNull(),
                    surface = _data.value.surface.text.toIntOrNull(),
                    room = _data.value.room.text.toIntOrNull(),
                    image = _data.value.image.toString(),
                    description = _data.value.description.text.ifBlank { null },
                    address = _data.value.address.text.ifBlank { null },
                    school = _data.value.school.or(false),
                    shops = _data.value.shops.or(false),
                    sale = _data.value.sale.or(false),
                    dateOfCreation = _data.value.dateOfCreation.text.toLongOrNull(),
                    dateOfSold = _data.value.dateOfSold.text.toLongOrNull(),
                    agent = _data.value.agent.text.ifBlank { null },
                    latitude = null,
                    longitude = null
                )
                repository.insert(propertyEntity = propertyEntity)
                Toast.makeText(context, "Property is saved !",Toast.LENGTH_LONG).show()
                onSuccess()
            } catch (e: Exception){
                Log.e("EditViewModel", "Error saving property: ${e.message}")
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
        _data.value = _data.value.copy(image = _data.value.image + imagePaths)
        uris.forEach { takePersistableUriPermission(it) }
    }

    private fun takePersistableUriPermission(uri: Uri) {
        try {
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, takeFlags)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}