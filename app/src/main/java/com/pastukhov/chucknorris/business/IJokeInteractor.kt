package com.pastukhov.chucknorris.business

import com.pastukhov.chucknorris.business.model.JokeBusinessModel
import io.reactivex.Observable

interface IJokeInteractor {

    fun getJokeBusinessModel(): Observable<JokeBusinessModel>
}