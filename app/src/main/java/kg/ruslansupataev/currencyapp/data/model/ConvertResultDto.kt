package kg.ruslansupataev.currencyapp.data.model

import com.google.gson.annotations.SerializedName

data class ConvertResultDto(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("result")
    val result: Double
)
