package com.nayab.contactmnager

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var addButton: Button
    private lateinit var showButton: Button
    private lateinit var contactListView: RecyclerView
    private lateinit var favoriteToggle: ImageView

    private var contactList = ArrayList<BaseContact>()
    private lateinit var adapter: ContactAdapter
    private var isFavoriteSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkBackground)
        setContentView(R.layout.activity_main)

        // Bind views
        nameInput = findViewById(R.id.name_input)
        phoneInput = findViewById(R.id.phone_input)
        emailInput = findViewById(R.id.email_input)
        addButton = findViewById(R.id.add_button)
        showButton = findViewById(R.id.show_button)
        contactListView = findViewById(R.id.contact_list)
        favoriteToggle = findViewById(R.id.favorite_toggle)

        // Setup RecyclerView
        adapter = ContactAdapter(contactList)
        contactListView.layoutManager = LinearLayoutManager(this)
        contactListView.adapter = adapter

        // Toggle favorite heart icon
        favoriteToggle.setOnClickListener {
            isFavoriteSelected = !isFavoriteSelected
            if (isFavoriteSelected) {
                favoriteToggle.setImageResource(R.drawable.ic_heart_filled)
            } else {
                favoriteToggle.setImageResource(R.drawable.ic_outline_heart)
            }
        }

        // Add Contact button
        addButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val email = emailInput.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newContact: BaseContact = if (isFavoriteSelected) {
                FavoriteContact(name, phone, email)
            } else {
                Contact(name, phone, email)
            }

            contactList.add(newContact)
            adapter.notifyItemInserted(contactList.size - 1)

            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()

            // Clear inputs
            nameInput.text.clear()
            phoneInput.text.clear()
            emailInput.text.clear()
            isFavoriteSelected = false
            favoriteToggle.setImageResource(R.drawable.ic_outline_heart)
            contactListView.visibility = View.GONE
        }

        showButton.setOnClickListener {
            if (contactList.isEmpty()) {
                Toast.makeText(this, "No contacts to display", Toast.LENGTH_SHORT).show()
            } else {
                contactListView.visibility = View.VISIBLE  // 👈 Show the list now
                adapter.notifyDataSetChanged()
            }
        }

    }
}
