package com.nayab.contactmnager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var contactListView: RecyclerView
    private lateinit var showButton: Button
    private lateinit var addBtn: ImageView
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkBackground)
        setContentView(R.layout.activity_main)

        // Bind views
        showButton = findViewById(R.id.show_button)
        addBtn = findViewById(R.id.addBtn)
        contactListView = findViewById(R.id.contact_list)

        // Setup RecyclerView
        adapter = ContactAdapter(ContactData.contactList)
        contactListView.layoutManager = LinearLayoutManager(this)
        contactListView.adapter = adapter

        // Show contacts on button click
        showButton.setOnClickListener {
            if (ContactData.contactList.isEmpty()) {
                Toast.makeText(this, "No contacts to display", Toast.LENGTH_SHORT).show()
            } else {
                contactListView.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            }
        }

        // Add new contact from another activity
        addBtn.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down)
        }
    }

    // ✅ Prevent auto-showing contacts after return from AddContactActivity
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        contactListView.visibility = View.GONE  // 👈 Hides list until Show button is clicked
    }
}
