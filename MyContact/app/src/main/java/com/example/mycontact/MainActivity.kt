package com.example.mycontact

import android.content.Intent
import  android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontact.data.AppDatabase
import com.example.mycontact.data.Contact
import com.example.mycontact.databinding.ActivityMainBinding
import com.example.mycontact.databinding.FormContactBinding
//import com.example.mycontact.databinding.FormContactBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    // buat variable untuk binding
    private lateinit var binding: ActivityMainBinding

    private lateinit var contactViewAdapter: ContactViewAdapter

    // buat variable untuk DAO contact
    // digunakan untuk berinteraksi dengan tabel contact
    private val contatDao by lazy { AppDatabase.get(this).contactDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // assign binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        // set content view
        setContentView(binding.root)

        // assign contact view adapter
        contactViewAdapter = ContactViewAdapter(
            onEdit = { contact -> showEditDialog(contact) },
            onDelete = { contact -> showConfirmDelete(contact) }
        )

        with(binding) {
            btnProfile.setOnClickListener {
                startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
            }

            rvContact.apply {
                adapter = contactViewAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            btnAdd.setOnClickListener {
                showAddDialog()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // panggil refresh data
        refreshList()
    }

    // fungsi handle untuk update data ketika dibuka appnya
    private fun refreshList() {
        lifecycleScope.launch {
            // ambil data dari database
            var items = withContext(Dispatchers.IO) { contatDao.getAll() }

            // udpate data ke adapter agar tampilan terupdate
            contactViewAdapter.setItems(items)
        }
    }

    // fungsi untuk menampilkan form add contact
    private fun showAddDialog() {
        var binding = FormContactBinding.inflate(layoutInflater)

        var builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Add Contact")
        builder.setView(binding.root)

        builder.setPositiveButton("Save") { dialog, which ->
            // ambil name dan phone dari form
            var name = binding.edtName.text.toString().trim()
            var phone = binding.edtPhone.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    // insert ke dabase
                    contatDao.insert(Contact(name = name, phone = phone))

                    // minta UI untuk refresh data
                    withContext(Dispatchers.Main) { refreshList() }
                }

                dialog.dismiss()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Nama dan Phone harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        // dialog di show
        val dialog = builder.create()
        dialog.show()
    }


    private fun showEditDialog(contact: Contact) {
        var binding = FormContactBinding.inflate(layoutInflater)

        // isi form dengan data existing contact
        binding.edtName.setText(contact.name)
        binding.edtPhone.setText(contact.phone)

        var builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Edit Contact")
        builder.setView(binding.root)

        builder.setPositiveButton("Save") { dialog, which ->
            // ambil name dan phone dari form
            var name = binding.edtName.text.toString().trim()
            var phone = binding.edtPhone.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    // update ke dabase
                    contatDao.update(contact.copy(name = name, phone = phone))

                    // minta UI untuk refresh data
                    withContext(Dispatchers.Main) { refreshList() }
                }

                dialog.dismiss()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Nama dan Phone harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        // dialog di show
        val dialog = builder.create()
        dialog.show()
    }

    private fun showConfirmDelete(contact: Contact) {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Delete ${contact.name}?")

        builder.setPositiveButton("Delete") { dialog, which ->
            lifecycleScope.launch(Dispatchers.IO) {
                contatDao.delete(contact)
                withContext(Dispatchers.Main) { refreshList() }
            }
            dialog.dismiss()
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

}