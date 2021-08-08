package net.http

import net.entity.DataEntity
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RequestService {

    @FormUrlEncoded
    @POST("testfanyi.php")
    fun getTranslateResult(
        @Field("fromT") fromT: String,
        @Field("toT") toT: String,
        @Field("queryT") queryT: String
    ): Call<DataEntity>

}