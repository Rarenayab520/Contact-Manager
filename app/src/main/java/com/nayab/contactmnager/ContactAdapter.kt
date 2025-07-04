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

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val initialCircle: TextView = itemView.findViewById(R.id.initial_circle)
        val contactName: TextView = itemView.findViewById(R.id.contact_name)
        val contactType: TextView = itemView.findViewById(R.id.contact_type)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favorite_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        // Set full name
        holder.contactName.text = contact.displayInfo()

        // Set initial in the circle
        holder.initialCircle.text = contact.getFirstInitial()

        // Show contact type
        holder.contactType.text = contact.getContactType()

        // Show star for favorite
        if (contact is FavoriteContact) {
            holder.favoriteIcon.visibility = View.VISIBLE
        } else {
            holder.favoriteIcon.visibility = View.GONE
        }

        // On click: show more details
        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Name: ${contact.getName()}\nPhone: ${contact.getPhoneNumber()}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = contactList.size
}
