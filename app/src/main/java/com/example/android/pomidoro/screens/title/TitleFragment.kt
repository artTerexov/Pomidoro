package com.example.android.pomidoro.screens.title

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.pomidoro.R
import com.example.android.pomidoro.databinding.BottomSheetFragmentBinding
import com.example.android.pomidoro.databinding.TitleFragmentBinding
import com.example.android.pomidoro.screens.bottomSheet.BottomSheetFragment

class TitleFragment : Fragment() {

    private lateinit var viewModel: TitleViewModel
    private lateinit var binding: TitleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.i("TitleFragment", "is Create!")
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false)


        //inflate bottomSheetFragment
        val bottomBinding: BottomSheetFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.bottom_sheet_fragment, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(TitleViewModel::class.java)

        binding.titleViewModel = viewModel
        binding.lifecycleOwner = this

        binding.goalTitle.setOnClickListener {
            BottomSheetFragment().show(requireActivity().supportFragmentManager, BottomSheetFragment.TAG)
        }

        return binding.root
    }
}