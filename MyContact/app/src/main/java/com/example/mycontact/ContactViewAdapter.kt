package com.example.mycontact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontact.data.Contact
import com.example.mycontact.databinding.ItemContactBinding

class ContactViewAdapter(
    private val onEdit: (Contact) -> Unit,
    private val onDelete: (Contact) -> Unit
): RecyclerView.Adapter<ContactViewAdapter.ItemContactViewHolder>() {

    // list data yang mau ditampilkan
    private val contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemContactViewHolder {
        // assign binding
        var binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // setup view holder
        return ItemContactViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemContactViewHolder,
        position: Int
    ) {
        // ketika sudah di bind
        // panggil fungsi untuk menampilkan item
        holder.binding(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.count()
    }

    // set content view di level per item
    inner class ItemContactViewHolder(private val binding: ItemContactBinding)
        : RecyclerView.ViewHolder(binding.root) {

        // buat fungsi untuk mengatur ui tiap item
        fun binding(contact: Contact) {
            with(binding) {
                txtName.setText(contact.name)
                txtPhone.setText(contact.phone)

                // panggil edit
                btnEdit.setOnClickListener { onEdit(contact) }
                btnDelete.setOnClickListener { onDelete(contact) }
            }
        }
    }

    // fungsi untuk update isi dari data kontak
    fun setItems(newData: List<Contact>) {
        contacts.clear()
        contacts.addAll(newData)
        notifyDataSetChanged()
    }

}