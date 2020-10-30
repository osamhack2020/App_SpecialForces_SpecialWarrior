package co.specialforce.data.response.getFriend

import com.google.gson.annotations.SerializedName

data class Friend (val user_id: String, val name: String,
                   @SerializedName(value="class") val level: Int)