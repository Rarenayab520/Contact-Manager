package com.nayab.contactmnager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contactList: List<BaseContact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactInfo: TextView = itemView.findViewById(R.id.contact_info)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favorite_icon)
        val contactType: TextView = itemView.findViewById(R.id.contact_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        // Display contact details
        holder.contactInfo.text = contact.displayInfo()
        holder.contactType.text = "Type: ${contact.getContactType()}"

        // Show or hide star icon based on type
        if (contact is FavoriteContact) {
            holder.favoriteIcon.visibility = View.VISIBLE
        } else {
            holder.favoriteIcon.visibility = View.GONE
        }

        // Click: show Toast with contact name
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Clicked: ${contact.getName()}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = contactList.size
}
