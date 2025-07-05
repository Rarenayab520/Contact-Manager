package com.nayab.contactmnager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactAdapter(private val contactList: List<BaseContact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val initialCircle: TextView = itemView.findViewById(R.id.initial_circle)
        val contactName: TextView = itemView.findViewById(R.id.contact_name)
        val contactType: TextView = itemView.findViewById(R.id.contact_type)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favorite_icon)
        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]
        val fullName = contact.displayInfo()

        holder.contactName.text = fullName
        holder.contactType.text = contact.getContactType()

        if (contact.imageUri != null) {
            // Show profile image using Glide
            holder.profileImage.visibility = View.VISIBLE
            holder.initialCircle.visibility = View.GONE
            Glide.with(holder.itemView.context)
                .load(contact.imageUri)
                .centerCrop()
                .into(holder.profileImage)
        } else {
            // Show initial circle
            holder.profileImage.visibility = View.GONE
            holder.initialCircle.visibility = View.VISIBLE
            holder.initialCircle.text = contact.getFirstInitial()
        }

        holder.favoriteIcon.visibility = if (contact is FavoriteContact) View.VISIBLE else View.GONE

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
