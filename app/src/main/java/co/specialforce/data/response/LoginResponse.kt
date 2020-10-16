package co.specialforce.data.response

data class LoginResponse(val access_token: String, val expires_in: Int, val token_type: String,
                         val scope: Any, val refresh_token: String)