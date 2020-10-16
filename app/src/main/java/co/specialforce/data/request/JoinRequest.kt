package co.specialforce.data.request

import com.google.gson.annotations.SerializedName

data class JoinRequest (val user_id: String, val password: String,
                        @SerializedName(value="class") val level: Int, val cadre_flag: Boolean,
                        val army_num: String, val unit_id: Int, val name: String,
                        val email: String, val phone: String)