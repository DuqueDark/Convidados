package com.example.convidados.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.Service.Constants.GuestConstants
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.View.Adapter.GuestAdapter
import com.example.convidados.View.Listener.GuestListener
import com.example.convidados.ViewModel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private val mAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        val root: View = inflater.inflate(R.layout.fragment_all, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guest)
        recycler.layoutManager = LinearLayoutManager(root.context)
        recycler.adapter = mAdapter

        observer()
        allGuestsViewModel.load()

        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context,GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID,id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(guest:GuestModel) {
                allGuestsViewModel.delete(guest)
                allGuestsViewModel.load()
            }

        }
        mAdapter.attachListener(mListener)

        return root
    }

    override fun onResume() {
        allGuestsViewModel.load()
        super.onResume()
    }

    private fun observer() {
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, {
            mAdapter.updateGuest(it)
        })
    }

}