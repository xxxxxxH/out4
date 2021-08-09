package net.basicmodel

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.layout_activity_translate.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import net.entity.DataEntity
import net.http.RequestService
import net.http.RetrofitUtils
import net.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class TranslateActivity : AppCompatActivity() {

    val retrofit = RetrofitUtils.get().retrofit()
    val service = retrofit.create(RequestService::class.java)
    var entity: DataEntity? = null
    var languageMap: HashMap<String, String>? = null
    var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_translate)
        languageMap = Language.getInstance().language
        initView()
    }


    private fun initView() {
        val p = input_language.layoutParams
        p.height = ScreenUtils.getScreenSize(this)[0] / 4
        input_language.layoutParams = p

        title_bar.text = "translate"

        //language_from
        language_from.setOnClickListener {
            selectLanguage("from")
        }

        //change_language
        change_language.setOnClickListener {
            val curFrom = language_from.text.toString()
            val curTo = language_to.text.toString()
            language_from.text = curTo
            language_to.text = curFrom
        }

        //language_to
        language_to.setOnClickListener {
            selectLanguage("to")
        }

        //btn_translate
        btn_translate.setOnClickListener {
            if (TextUtils.isEmpty(input_language.text.toString())) {
                Toast.makeText(this, "please input something", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (loadingDialog == null) {
                loadingDialog = LoadingDialog(this)
            }

            loadingDialog!!.show()

            val fromCode =
                Language.getInstance().getCode(languageMap, language_from.text.toString())
            val toCode = Language.getInstance().getCode(languageMap, language_to.text.toString())
            val content = input_language.text.toString()
            service.getTranslateResult(
                fromCode,
                toCode,
                content
            )
                .enqueue(object : Callback<DataEntity> {
                    override fun onResponse(
                        call: Call<DataEntity>,
                        response: Response<DataEntity>
                    ) {
                        Log.i("xxxxxxH", "response = $response")
                        entity = response.body() as DataEntity
                        val result = entity!!.trans_result[0].dst
                        translate_after.text = result
                        val entityKey = System.currentTimeMillis().toString()
                        MMKVUtils.saveKeys("history", entityKey)
                        MMKV.defaultMMKV()?.encode(entityKey, entity)
                        if (loadingDialog != null && loadingDialog!!.isShowing) {
                            loadingDialog!!.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<DataEntity>, t: Throwable) {
                        Toast.makeText(this@TranslateActivity, "no data", Toast.LENGTH_SHORT).show()
                        if (loadingDialog != null && loadingDialog!!.isShowing) {
                            loadingDialog!!.dismiss()
                        }
                    }

                })
        }


        //copy
        copy.setOnClickListener {
            if (TextUtils.isEmpty(translate_after.text.toString()))
                return@setOnClickListener
            CopyUtils.copy(this, translate_after.text.toString())
        }

        //collection
        collection.setOnClickListener {
            val entityKey = System.currentTimeMillis().toString()
            MMKVUtils.saveKeys("collection", entityKey)
            MMKV.defaultMMKV()?.encode(entityKey, entity)
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        }

        back.setOnClickListener {
            finish()
        }

    }

    private fun selectLanguage(type: String) {
        val intent = Intent(this, LanguageActivity::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        if (TextUtils.isEmpty(Constanst.FROM_LANGUAGE) && TextUtils.isEmpty(Constanst.TO_LANGUAGE)) {
            language_tv.text = "en - zh"
        } else {
            var fromCode = Language.getInstance().getCode(languageMap, Constanst.FROM_LANGUAGE)
            var toCode = Language.getInstance().getCode(languageMap, Constanst.TO_LANGUAGE)
            var content = ""
            if (!TextUtils.isEmpty(Constanst.FROM_LANGUAGE)) {
                language_from.text = Constanst.FROM_LANGUAGE
            }
            if (!TextUtils.isEmpty(Constanst.TO_LANGUAGE)) {
                language_to.text = Constanst.TO_LANGUAGE
            }
            if (TextUtils.isEmpty(fromCode)) {
                fromCode = "en"
            }
            if (TextUtils.isEmpty(toCode)) {
                toCode = "zh"
            }
            content = "$fromCode - $toCode"
            language_tv.text = content
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Constanst.FROM_LANGUAGE = ""
        Constanst.TO_LANGUAGE = ""
    }
}