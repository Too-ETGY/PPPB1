package com.example.d4_myshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.d4_myshop.databinding.FragmentHomeBinding
import com.example.d4_myshop.databinding.FragmentProfileBinding
import kotlin.getValue

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null;
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEdit.setOnClickListener {
                val action = ProfileFragmentDirections
                    .actionProfileFragmentToEditProfileFragment(
                        nameTxt.text.toString(),
                        phoneTxt.text.toString(),
                        alamatTxt.text.toString()
                    )
                findNavController().navigate(action)
            }

            // 🔹 Listen for updated profile data
            findNavController().currentBackStackEntry
                ?.savedStateHandle
                ?.getLiveData<Triple<String, String, String>>("profile_data")
                ?.observe(viewLifecycleOwner) { (name, phone, alamat) ->
                    nameTxt.text = name
                    phoneTxt.text = phone
                    alamatTxt.text = alamat
                }
        }
    }
}