package com.example.feature_ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_ui.adapter.ListAdapter
import com.example.feature_ui.databinding.ListFragmentBinding
import com.example.feature_ui.viewmodel.FeatureViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment: Fragment(), ListAdapter.OnItemClickListener {

    private val viewModel by viewModels<FeatureViewModel>()

    private var _binding: ListFragmentBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access views using the binding property

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the items LiveData
        viewModel.items.observe(viewLifecycleOwner) { items ->
            // Update RecyclerView with the new list
            binding.recyclerView.adapter = ListAdapter(items, this)
        }

        // Observe the error LiveData
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Log.d("BMK", "error: $it")
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        // Navigate to another fragment


        // Use FragmentManager to begin a transaction and replace the current fragment
        val anotherFragment = PostDetailsFragment(viewModel.items.value?.get(position)!!)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(com.example.feature_ui.R.id.fragment_container, anotherFragment, "findThisFragment")
            .addToBackStack(null)
            .commit()

        Log.d("BMK", "pos: "+viewModel.items.value?.get(position))
    }
}