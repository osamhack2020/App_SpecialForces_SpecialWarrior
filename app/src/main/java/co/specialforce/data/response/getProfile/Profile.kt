package co.specialforce.data.response.getProfile

import com.google.gson.annotations.SerializedName

data class Profile(val user_id: String, val cadre_flag: String, val admin_flag: String,
                   @SerializedName(value="class") val level: String, val army_num: String,
                   val unit_id: String, val name: String, val email: String, val phone: String,
                   val created_at: String, val unit_full_name: String)