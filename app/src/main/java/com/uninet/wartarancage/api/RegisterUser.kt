package com.uninet.wartarancage.api

data class RegisterUser(
	val response: Response?,
	val messages: String?,
	val status: Int?
)

data class Response(
	val uid: String? = null,
	val password: String? = null,
	val role: Int? = null,
	val updatedAt: String? = null,
	val apiToken: String? = null,
	val fgActive: String? = null,
	val createdAt: String? = null,
	val email: String? = null,
	val username: String? = null
)

