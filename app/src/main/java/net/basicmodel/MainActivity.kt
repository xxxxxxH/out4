package net.basicmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*
import net.utils.ScreenUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MMKV.initialize(this)
        initView()
        click()
    }

    private fun initView() {
        val p = translateImg.layoutParams
        p.width = ScreenUtils.getScreenSize(this)[1] / 3
        p.height = ScreenUtils.getScreenSize(this)[1] / 3
        translateImg.layoutParams = p
    }

    private fun click() {
        translateArea.setOnClickListener {
            startActivity(Intent(this, TranslateActivity::class.java))
        }
        collectionArea.setOnClickListener {
            val intent = Intent(this, CollectionActivity::class.java)
            intent.putExtra("type", "collection")
            startActivity(intent)
        }
        historyArea.setOnClickListener {
            val intent = Intent(this, CollectionActivity::class.java)
            intent.putExtra("type", "history")
            startActivity(intent)
        }
    }
}