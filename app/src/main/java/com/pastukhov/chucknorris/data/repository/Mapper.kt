package com.pastukhov.chucknorris.data.repository

import com.pastukhov.chucknorris.business.model.JokeBusinessModel
import com.pastukhov.chucknorris.data.model.RandomJokeModel

fun map(model: RandomJokeModel) = JokeBusinessModel(model.id, model.value)
