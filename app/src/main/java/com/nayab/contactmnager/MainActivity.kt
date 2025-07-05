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
import com.eightbitlab.blurview.BlurView
import com.eightbitlab.blurview.RenderScriptBlur

class MainActivity : AppCompatActivity() {

    private lateinit var contactListView: RecyclerView
    private lateinit var showButton: Button
    private lateinit var addBtn: ImageView
    private lateinit var adapter: ContactAdapter
    private lateinit var blurView: BlurView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkBackground)
        setContentView(R.layout.activity_main)

        // Bind views
        showButton = findViewById(R.id.show_button)
        addBtn = findViewById(R.id.addBtn)
        contactListView = findViewById(R.id.contact_list)
        blurView = findViewById(R.id.blurView)

        // Setup RecyclerView
        adapter = ContactAdapter(ContactData.contactList)
        contactListView.layoutManager = LinearLayoutManager(this)
        contactListView.adapter = adapter

        // BlurView Setup
        val radius = 18f
        val decorView = window.decorView.rootView as View
        val windowBackground = window.decorView.background

        blurView.setupWith(decorView.findViewById(android.R.id.content))
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setHasFixedTransformationMatrix(true)

        blurView.visibility = View.GONE // Hidden by default

        // Show contacts on button click
        showButton.setOnClickListener {
            if (ContactData.contactList.isEmpty()) {
                Toast.makeText(this, "No contacts to display", Toast.LENGTH_SHORT).show()
            } else {
                contactListView.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            }
        }

        // Add new contact (show bottom sheet + blur)
        addBtn.setOnClickListener {
            blurView.visibility = View.VISIBLE
            val sheet = NewContactFragment {
                blurView.visibility = View.GONE
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
