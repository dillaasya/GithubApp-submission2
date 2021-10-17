package com.adillasyafira.githubapp.model

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(

	@field:SerializedName("total_count")
	val totalCount: Int,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean,

	@field:SerializedName("items")
	val items: ArrayList<User>
)
