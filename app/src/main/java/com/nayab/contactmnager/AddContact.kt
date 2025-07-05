package com.nayab.contactmnager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class AddContactActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etPhone: EditText
    private lateinit var imgProfile: ImageView
    private lateinit var btnAddPhoto: Button
    private lateinit var btnCancel: ImageView
    private lateinit var btnSave: ImageView
    private lateinit var favoriteToggle: ImageView

    private var isFavorite = false
    private var selectedImageUri: Uri? = null

    companion object {
        private const val IMAGE_PICK_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkBackground)

        // Bind views
        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etPhone = findViewById(R.id.etPhone)
        imgProfile = findViewById(R.id.imgProfile)
        btnAddPhoto = findViewById(R.id.btnAddPhoto)
        btnCancel = findViewById(R.id.btnCancel)
        btnSave = findViewById(R.id.btnSave)
        favoriteToggle = findViewById(R.id.favoriteToggle)

        // Select image from gallery
        btnAddPhoto.setOnClickListener {
            pickImageFromGallery()
        }

        // Cancel and go back
        btnCancel.setOnClickListener {
            finish()
        }

        // Toggle favorite
        favoriteToggle.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                favoriteToggle.setImageResource(R.drawable.ic_heart_filled)
            } else {
                favoriteToggle.setImageResource(R.drawable.ic_outline_heart)
            }
        }

        // Save contact
        btnSave.setOnClickListener {
            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (firstName.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "First name and phone are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newContact: BaseContact = if (isFavorite) {
                FavoriteContact(firstName, lastName, phone)
            } else {
                Contact(firstName, lastName, phone)
            }

            // ✅ Add contact to shared list
            ContactData.contactList.add(newContact)

            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            imgProfile.setImageURI(selectedImageUri)
        }
    }
}
