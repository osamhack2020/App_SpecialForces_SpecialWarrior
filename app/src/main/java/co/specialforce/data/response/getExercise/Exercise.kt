package co.specialforce.data.response.getExercise

data class Exercise (val exercise_profile_id: Int, val exercise_id: Int, val exercise_weight: Int,
                     val exercise_count: Int, val exercise_time: Int, val profile_id: String,
                     val user_id: String, val created_at: String)