package com.nayab.contactmnager

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var contactListView: RecyclerView
    private lateinit var addBtn: ImageView
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ContactData.load(this)

        addBtn = findViewById(R.id.addBtn)
        contactListView = findViewById(R.id.contact_list)

        // Sort *before* creating the adapter
        sortContacts()
        adapter = ContactAdapter(ContactData.contactList)
        contactListView.layoutManager = LinearLayoutManager(this)
        contactListView.adapter = adapter

        updateListVisibility()

        addBtn.setOnClickListener {
            val sheet = NewContactFragment {
                // after adding the new contact:
                sortContacts()
                adapter.notifyDataSetChanged()
                updateListVisibility()
                ContactData.save(this@MainActivity)
            }
            sheet.show(supportFragmentManager, "NewContact")
        }
    }

    override fun onResume() {
        super.onResume()
        // re‑sort in case something changed externally
        sortContacts()
        adapter.notifyDataSetChanged()
        updateListVisibility()
    }

    /** Sorts alphabetically by firstName then lastName (case‑insensitive) */
    private fun sortContacts() {
        ContactData.contactList.sortWith(
            compareBy(
                { it.firstName.lowercase(Locale.getDefault()) },
                { it.lastName.lowercase(Locale.getDefault()) }
            )
        )
    }

    private fun updateListVisibility() {
        if (ContactData.contactList.isEmpty()) {
            contactListView.visibility = View.GONE
            Toast.makeText(this, "No contacts to display", Toast.LENGTH_SHORT).show()
        } else {
            contactListView.visibility = View.VISIBLE
        }
    }
}