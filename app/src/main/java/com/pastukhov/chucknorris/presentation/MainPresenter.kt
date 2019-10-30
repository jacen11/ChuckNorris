package com.pastukhov.chucknorris.presentation

import android.util.Log
import com.pastukhov.chucknorris.data.ChackNorrisServiceForCoroutine
import com.pastukhov.chucknorris.data.ChackNorrisService
import com.pastukhov.chucknorris.data.model.RandomJokeModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor(
    var chackNorrisServiceForCoroutine: ChackNorrisServiceForCoroutine,
    var chackNorrisService: ChackNorrisService
) : IMainPresenter {

    var jokeModel: RandomJokeModel = RandomJokeModel("0", "0")

    private var view: IMainView? = null

    override fun attachView(view: IMainView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getRandomJokeCoroutine(): RandomJokeModel {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            try {
                val response = chackNorrisServiceForCoroutine.getRandomJoke()
                jokeModel = response.await()

            } catch (e: RuntimeException) {
                //Toast.makeText(this, "OOops!", Toast.LENGTH_SHORT).show()
            }
        }
        return jokeModel
    }

    override fun getRandomJokeRxJava() {
        compositeDisposable.add(
            chackNorrisService.getRandomJoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view?.setText2(it.value)
                        Log.d("zavanton", "zavanton - joke: $it")
                    },
                    {
                        Log.e("zavanton", "zavanton - error: $it")
                    }
                )
        )
    }
}