package com.example.convidados.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.ViewModel.GuestFormViewModel
import com.example.convidados.R
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setlisteners()
        observe()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_save ->{

                val name = edit_name.text.toString()
                val presence = radio_presents.isChecked

                mViewModel.save(name,presence)
            }
        }

    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext,getString(R.string.success),Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(applicationContext,getString(R.string.fail),Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setlisteners() {
        btn_save.setOnClickListener(this)
    }


}