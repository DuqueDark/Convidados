package com.example.convidados.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.Service.Constants.GuestConstants
import com.example.convidados.View.Adapter.GuestAdapter
import com.example.convidados.View.Listener.GuestListener
import com.example.convidados.ViewModel.AbsentsViewModel
import com.example.convidados.databinding.FragmentAbsentsBinding
import kotlin.math.abs

class AbsentsFragment : Fragment() {

    private val mAdapter = GuestAdapter()
    private lateinit var absentsViewModel: AbsentsViewModel
    private lateinit var mListener:GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        absentsViewModel =
            ViewModelProvider(this).get(AbsentsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_absents, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_absents)
        recycler.layoutManager = LinearLayoutManager(root.context)
        recycler.adapter = mAdapter

        observer()
        absentsViewModel.load()

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context,GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID,id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                absentsViewModel.delete(id)
                absentsViewModel.load()
            }

        }
        mAdapter.attachListener(mListener)

        return root
    }

    private fun observer() {
        absentsViewModel.guestList.observe(viewLifecycleOwner,  {
            mAdapter.updateGuest(it)
        })
    }

    override fun onResume() {
        absentsViewModel.load()
        super.onResume()
    }
}