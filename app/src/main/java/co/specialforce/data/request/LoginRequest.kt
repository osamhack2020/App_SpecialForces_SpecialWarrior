package co.specialforce.data.request

data class LoginRequest (val grant_type: String, val client_id: String,
                         val user_id: String, val password: String)