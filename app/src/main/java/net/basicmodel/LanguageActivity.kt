package net.basicmodel

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_activity_select_language.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import net.adapter.LanguageAdapter
import net.utils.Constanst
import net.utils.Language
import net.utils.OnclickListener
import java.util.*

class LanguageActivity : AppCompatActivity(), OnclickListener {

    var adapter: LanguageAdapter? = null
    var languageMap: HashMap<String, String>? = null
    var countrys: ArrayList<String>? = null
    var type:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_select_language)
        languageMap = Language.getInstance().language
        countrys = Language.getInstance().getAllCountry(languageMap)
        val intent = intent
        type = intent.getStringExtra("type") as String
        title_bar.text = "language"
        initView()
    }

    private fun initView() {
        editLan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length < 3)
                    return
                val index = findItem(p0.toString())
                if (index != -1) {
                    select_recycler.smoothScrollToPosition(index)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        back.setOnClickListener {
            finish()
        }

        adapter = LanguageAdapter(countrys)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        select_recycler.layoutManager = layoutManager
        select_recycler.adapter = adapter
        adapter!!.setListener(this)

        clear.setOnClickListener {
            if (!TextUtils.isEmpty(editLan.text.toString())) {
                editLan.text.clear()
            }
        }
    }

    private fun findItem(key: String): Int {
        var index = -1
        for ((i, item) in countrys!!.withIndex()) {
            if (item.toLowerCase().contains(key.toLowerCase())) {
                index = i
                break
            }
        }
        return index
    }

    override fun onItemClick(view: View?, position: Int, type: String) {
        val language = countrys?.get(position)
        if (TextUtils.equals(this.type, "from")) {
            Constanst.FROM_LANGUAGE = language
        }
        if (TextUtils.equals(this.type, "to")) {
            Constanst.TO_LANGUAGE = language
        }
        finish()
    }
}