package net.basicmodel

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_activity_collect.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import net.adapter.CollectionAdapter
import net.entity.DataEntity
import net.utils.CopyUtils
import net.utils.MMKVUtils
import net.utils.OnclickListener

class CollectionActivity : AppCompatActivity(), OnclickListener {

    var adapter: CollectionAdapter? = null
    var type: String = ""
    var data: ArrayList<DataEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_collect)
        val intent = intent
        type = intent.getStringExtra("type") as String
        if (TextUtils.equals(type, "collection")) {
            data = MMKVUtils.getAllDatas("collection")
        } else if (TextUtils.equals(type, "history")) {
            data = MMKVUtils.getAllDatas("history")
        }
        adapter = CollectionAdapter(data, type)
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
        adapter!!.setListener(this)

        back.setOnClickListener {
            finish()
        }
    }

    override fun onItemClick(view: View?, position: Int, type: String?) {
        val entity = data!![position] as DataEntity
        val text = entity.trans_result[0].dst
        CopyUtils.copy(this, text)
    }
}