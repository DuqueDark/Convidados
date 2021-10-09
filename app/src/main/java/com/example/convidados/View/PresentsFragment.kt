package com.example.convidados.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.Service.Constants.GuestConstants
import com.example.convidados.View.Adapter.GuestAdapter
import com.example.convidados.View.Listener.GuestListener
import com.example.convidados.ViewModel.PresentsViewModel

class PresentsFragment : Fragment() {

    private val mAdapter = GuestAdapter()
    private lateinit var presentsViewModel: PresentsViewModel
    private lateinit var mListener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presentsViewModel =
            ViewModelProvider(this).get(PresentsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_presents, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_presents)
        recycler.layoutManager = LinearLayoutManager(root.context)
        recycler.adapter = mAdapter

        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context,GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID,id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                presentsViewModel.delete(id)
                presentsViewModel.load()
            }

        }
        mAdapter.attachListener(mListener)

        observer()
        presentsViewModel.load()

        return root
    }

    override fun onResume() {
        presentsViewModel.load()
        super.onResume()
    }

    private fun observer() {
        presentsViewModel.guestList.observe(viewLifecycleOwner, {
            mAdapter.updateGuest(it)
        })
    }
}
