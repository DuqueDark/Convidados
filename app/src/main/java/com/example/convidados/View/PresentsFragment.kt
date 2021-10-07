package com.example.convidados.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.ViewModel.PresentsViewModel

class PresentsFragment : Fragment() {

    private lateinit var presentsViewModel: PresentsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presentsViewModel =
            ViewModelProvider(this).get(PresentsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_presents, container, false)

        return root
    }
}
