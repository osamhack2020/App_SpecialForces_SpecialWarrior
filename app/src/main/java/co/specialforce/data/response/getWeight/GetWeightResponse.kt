package co.specialforce.data.response.getWeight

data class GetWeightResponse(val success: Boolean, val result: List<WeightArray>,
                             val min_max_avg: List<WeightMinMaxAvg>)