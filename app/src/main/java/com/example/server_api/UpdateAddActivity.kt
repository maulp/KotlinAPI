package com.example.server_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_update_add.*

@Suppress ("SENSELESSES_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter2
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)

        presenter = Presenter2(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")

        if(itemDataItem == null){
            btnAction.text = "Tambah"
            btnAction.setOnClickListener(){
                presenter.addData(
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAddress.text.toString()
                )
            }
        } else if(itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.staffName.toString())
            etPhone.setText(item?.staffHp.toString())
            etAddress.setText(item?.staffAlamat.toString())

            btnAction.setOnClickListener(){
                presenter.updateData(
                    item?.staffId ?: "",
                    etName.text.toString(),
                    etPhone.text.toString(),
                    etAddress.text.toString()
                )
                finish()
            }
        }
    }

    override fun onSuccessAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorAdd(msg: String) {

    }

    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onErrorUpdate(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessGet(data: List<DataItem>?) {
        TODO("Not yet implemented")
    }

    override fun onFailedGet(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onSuccessDelete(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onErrorDelete(msg: String) {
        TODO("Not yet implemented")
    }

    fun onErrorupdate(localizedMessage: String?) {

    }
}