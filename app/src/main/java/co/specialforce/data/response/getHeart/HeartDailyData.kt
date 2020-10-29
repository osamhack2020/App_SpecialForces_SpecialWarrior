package co.specialforce.data.response.getHeart

data class HeartDailyData (val date: String, val result: List<HeartArray>,
                           val min_max_avg: HeartMinMaxAvg)