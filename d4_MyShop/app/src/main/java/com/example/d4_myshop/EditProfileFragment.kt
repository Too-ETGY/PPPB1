package com.example.d4_myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.d4_myshop.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val args: EditProfileFragmentArgs by navArgs() // <-- Safe Args for initial values

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            // 🔹 Pre-fill EditTexts with data passed from ProfileFragment
            nameEdt.setText(args.profileName)
            phoneEdt.setText(args.profilePhone)
            edtAddress.setText(args.profileAlamat)

            // 🔹 If user clicks on address field, navigate to AddressFragment
            edtAddress.setOnClickListener {
                val action = EditProfileFragmentDirections
                    .actionEditProfileFragmentToAddressFragment()
                findNavController().navigate(action)
            }

            // 🔹 Listen for address result coming back from AddressFragment
            findNavController().currentBackStackEntry?.savedStateHandle
                ?.getLiveData<String>("address")
                ?.observe(viewLifecycleOwner) { res ->
                    edtAddress.setText(res)
                }

            // 🔹 Save button returns updated profile data back to ProfileFragment
            btnSave.setOnClickListener {
                val updatedName = nameEdt.text.toString()
                val updatedPhone = phoneEdt.text.toString()
                val updatedAlamat = edtAddress.text.toString()

                findNavController().previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        "profile_data",
                        Triple(updatedName, updatedPhone, updatedAlamat)
                    )

                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
