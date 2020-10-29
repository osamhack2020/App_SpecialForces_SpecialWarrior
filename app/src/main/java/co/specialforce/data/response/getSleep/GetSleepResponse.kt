package co.specialforce.data.response.getSleep

data class GetSleepResponse(val success: Boolean, val result : List<SleepArray>,
                            val min_max_avg : SleepMinMaxAvg)