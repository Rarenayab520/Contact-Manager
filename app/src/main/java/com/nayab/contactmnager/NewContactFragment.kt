package com.nayab.contactmnager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewContactFragment(
    private val onDismissCallback: () -> Unit // 👈 used to hide blurView when modal closes
) : BottomSheetDialogFragment() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etPhone: EditText
    private lateinit var imgProfile: ImageView
    private lateinit var btnAddPhoto: Button
    private lateinit var btnSave: ImageView
    private lateinit var btnCancel: ImageView
    private lateinit var favoriteToggle: ImageView
    private var selectedImageUri: Uri? = null
    private var isFavorite = false

    companion object {
        private const val IMAGE_PICK_CODE = 1001
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etFirstName = view.findViewById(R.id.etFirstName)
        etLastName = view.findViewById(R.id.etLastName)
        etPhone = view.findViewById(R.id.etPhone)
        imgProfile = view.findViewById(R.id.imgProfile)
        btnAddPhoto = view.findViewById(R.id.btnAddPhoto)
        btnSave = view.findViewById(R.id.btnSave)
        btnCancel = view.findViewById(R.id.btnCancel)
        favoriteToggle = view.findViewById(R.id.favoriteToggle)

        btnAddPhoto.setOnClickListener {
            pickImageFromGallery()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        favoriteToggle.setOnClickListener {
            isFavorite = !isFavorite
            favoriteToggle.setImageResource(
                if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_outline_heart
            )
        }

        btnSave.setOnClickListener {
            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (firstName.isEmpty() || phone.isEmpty()) {
                Toast.makeText(requireContext(), "First name and phone required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newContact: BaseContact = if (isFavorite) {
                FavoriteContact(firstName, lastName, phone, selectedImageUri)
            } else {
                Contact(firstName, lastName, phone, selectedImageUri)
            }

            ContactData.contactList.add(newContact)
            Toast.makeText(requireContext(), "Contact added", Toast.LENGTH_SHORT).show()
            dismiss()
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

    override fun onDismiss(dialog: android.content.DialogInterface) {
        super.onDismiss(dialog)
        onDismissCallback() // 👈 Hide blurView when bottom sheet closes
    }
}
