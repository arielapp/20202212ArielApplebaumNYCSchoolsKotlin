package com.example.a20202212arielapplebaumnycschoolskotlin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.a20202212arielapplebaumnycschoolskotlin.R
import com.example.a20202212arielapplebaumnycschoolskotlin.ViewModels.SchoolDetailViewModel
import com.example.a20202212arielapplebaumnycschoolskotlin.databinding.FragmentSchoolDetailBinding

class SchoolDetailFragment : Fragment(R.layout.fragment_school_detail) {

    private val viewModel: SchoolDetailViewModel by viewModels()

    private val args : SchoolDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSchoolDetailBinding.bind(view)



        val school = viewModel.getDetails(args.dbn)

        binding.apply {
            detailSchoolName.text = school?.name
            detailSchoolEmail.text = school?.email
            detailSchoolPhone.text = school?.phone
        }
    }
}