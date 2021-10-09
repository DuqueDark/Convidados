package com.example.convidados.View

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.Service.Constants.GuestConstants
import com.example.convidados.ViewModel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private var mId = 0
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setlisteners()
        observe()
        loadData()

    }

    private fun loadData() {
        val bundle = intent.extras
        if(bundle != null){
            mId = bundle.getInt(GuestConstants.GUEST_ID)
            mViewModel.load(mId)
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_save ->{

                val name = edit_name.text.toString()
                val presence = radio_presents.isChecked

                mViewModel.save(mId,name,presence)
            }
        }

    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, {
            if(it){
                Toast.makeText(applicationContext,getString(R.string.success),Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext,getString(R.string.fail),Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.loadGuest.observe(this, {
            edit_name.setText(it.name)
            if (it.presence){
                radio_presents.isChecked = true
            }else{
                radio_absents.isChecked = true
            }
        })
    }

    private fun setlisteners() {
        btn_save.setOnClickListener(this)
    }


}