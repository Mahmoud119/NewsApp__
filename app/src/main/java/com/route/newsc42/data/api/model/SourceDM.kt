package com.route.newsc42.data.api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SourceDM(
	@ColumnInfo
	@field:SerializedName("country")
	val country: String? = null,

	@ColumnInfo
	@field:SerializedName("name")
	val name: String? = null,

	@ColumnInfo
	@field:SerializedName("description")
	val description: String? = null,

	@ColumnInfo
	@field:SerializedName("language")
	val language: String? = null,

	@ColumnInfo
	@PrimaryKey
	@field:SerializedName("id")
	val id: String ,

	@ColumnInfo
	@field:SerializedName("category")
	val category: String? = null,

	@ColumnInfo
	@field:SerializedName("url")
	val url: String? = null
)