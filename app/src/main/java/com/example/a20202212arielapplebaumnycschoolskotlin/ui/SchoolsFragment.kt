package com.example.a20202212arielapplebaumnycschoolskotlin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20202212arielapplebaumnycschoolskotlin.R
import com.example.a20202212arielapplebaumnycschoolskotlin.ViewModels.SchoolsViewModel
import com.example.a20202212arielapplebaumnycschoolskotlin.data.School
import com.example.a20202212arielapplebaumnycschoolskotlin.databinding.FragmentSchoolsBinding
import com.example.a20202212arielapplebaumnycschoolskotlin.di.exhaustive
import kotlinx.coroutines.flow.collect

class SchoolsFragment : Fragment(R.layout.fragment_schools), SchoolsAdapter.OnItemClickListener {

    private val schoolsViewModel: SchoolsViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSchoolsBinding.bind(view)

        val schoolsAdapter = SchoolsAdapter(this)

        binding.apply {
            schoolsListRecyclerView.apply {
                adapter = schoolsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }


        val schools = schoolsViewModel.getData()
        schoolsAdapter.submitList(schools)


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            schoolsViewModel.schoolsEvents.collect { event ->

                when (event) {
                    is SchoolsViewModel.SchoolsEvents.NavigateToDetails -> {
                        val action =
                            SchoolsFragmentDirections.actionSchoolsFragmentToSchoolDetailFragment(
                                event.school.dbn
                            )
                        findNavController().navigate(action)
                    }
                }.exhaustive

            }
        }


    }


    override fun onItemClicked(school: School) {
        schoolsViewModel.onItemClicked(school)
    }
}