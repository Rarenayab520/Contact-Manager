package com.nayab.contactmnager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var addButton: Button
    private lateinit var showButton: Button
    private lateinit var recyclerView: RecyclerView

    private val contactList = mutableListOf<BaseContact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name_input)
        phoneEditText = findViewById(R.id.phone_input)
        emailEditText = findViewById(R.id.email_input)
        addButton = findViewById(R.id.add_button)
        showButton = findViewById(R.id.show_button)
        recyclerView = findViewById(R.id.contact_list)

        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val email = emailEditText.text.toString()

            val contact = if (name.contains("*")) {
                FavoriteContact(name.replace("*", ""), phone, email)
            } else {
                Contact(name, phone, email)
            }

            contactList.add(contact)
            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show()

            nameEditText.text.clear()
            phoneEditText.text.clear()
            emailEditText.text.clear()
        }

        showButton.setOnClickListener {
            recyclerView.adapter = ContactAdapter(contactList)
        }
    }
}
