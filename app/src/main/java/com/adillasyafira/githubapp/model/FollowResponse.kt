package com.adillasyafira.githubapp.model

import com.google.gson.annotations.SerializedName

data class FollowResponseItem(

	@field:SerializedName("gists_url")
	val gistsUrl: String,

	@field:SerializedName("repos_url")
	val reposUrl: String,

	@field:SerializedName("following_url")
	val followingUrl: String,

	@field:SerializedName("starred_url")
	val starredUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("followers_url")
	val followersUrl: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String
)
