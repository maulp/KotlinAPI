package com.example.server_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = Presenter(this)
        presenter.getData()

        btnTambah.setOnClickListener{
            startActivity(Intent(applicationContext, UpdateAddActivity::class.java))
            finish()
        }
    }

    override fun onSuccessGet(data: List<DataItem>?){
        rvCategory.adapter = DataAdapter(data, object : DataAdapter.onClickItem{
            override fun clicked(item: DataItem?){
                val bundle = Bundle()
                bundle.putSerializable("dataItem", item)
                val intent = Intent(applicationContext, UpdateAddActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            override fun delete(item: DataItem?){
                presenter.hapusData(item?.staffId)
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        })
    }

    override fun onFailedGet(msg: String) {

    }

    override fun onSuccessDelete(msg: String){
        presenter.getData()
    }

    override fun onErrorDelete(msg: String){
        Toast.makeText(this, "Delete tidak berhasil", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessAdd(msg: String) {}

    override fun onErrorAdd(msg: String) {}

    override fun onSuccessUpdate(msg: String) {}

    override fun onErrorUpdate(msg: String) {}
}