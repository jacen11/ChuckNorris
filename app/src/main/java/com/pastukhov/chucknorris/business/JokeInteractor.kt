package com.pastukhov.chucknorris.business

import com.pastukhov.chucknorris.business.model.JokeBusinessModel
import com.pastukhov.chucknorris.data.ChackNorrisService
import com.pastukhov.chucknorris.data.repository.map
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeInteractor @Inject constructor(val service: ChackNorrisService) : IJokeInteractor {

    override fun getJokeBusinessModel(): Observable<JokeBusinessModel> {
        return service.getRandomJoke().map {
            map(it)
        }
    }
}