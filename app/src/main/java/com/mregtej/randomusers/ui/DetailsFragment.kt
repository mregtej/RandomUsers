package com.mregtej.randomusers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mregtej.randomusers.database.model.RandomUserDetailsData
import com.mregtej.randomusers.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillUI(args.userDetails)
    }

    private fun fillUI(data: RandomUserDetailsData) = binding.apply {
        loadProfilePhoto(data.photoUrl)
        profileName.text = data.name
        profileGender.text = data.gender
        profileLocation.text = data.location
        profileEmail.text = data.email
        binding.profileRegisteredDate.text = data.registeredDate
    }

    private fun loadProfilePhoto(url: String) =
        Glide.with(requireContext()).load(url).into(binding.profilePhoto)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}