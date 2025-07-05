package com.nayab.contactmnager

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

        showButton = findViewById(R.id.show_button)
        addBtn = findViewById(R.id.addBtn)
        contactListView = findViewById(R.id.contact_list)

        adapter = ContactAdapter(ContactData.contactList)
        contactListView.layoutManager = LinearLayoutManager(this)
        contactListView.adapter = adapter

        showButton.setOnClickListener {
            if (ContactData.contactList.isEmpty()) {
                Toast.makeText(this, "No contacts to display", Toast.LENGTH_SHORT).show()
            } else {
                contactListView.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            }
        }

        addBtn.setOnClickListener {
            val sheet = NewContactFragment {
                // No blur anymore
            }
            sheet.show(supportFragmentManager, "NewContact")
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        contactListView.visibility = View.GONE
    }
}
