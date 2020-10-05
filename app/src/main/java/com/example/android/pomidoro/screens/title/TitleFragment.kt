package com.example.android.pomidoro.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.pomidoro.R
import com.example.android.pomidoro.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    private lateinit var viewModel: TitleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: TitleFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false)

        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)

        binding.titleViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.taskState.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                binding.actionButton.text = getString(R.string.startButtonText)
            } else {
                binding.actionButton.text = getString(R.string.loseButtonText)
            }
        })

        return binding.root
    }
}