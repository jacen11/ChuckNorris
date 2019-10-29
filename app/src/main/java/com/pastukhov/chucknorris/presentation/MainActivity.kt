package com.pastukhov.chucknorris.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pastukhov.chucknorris.App
import com.pastukhov.chucknorris.R
import com.pastukhov.chucknorris.data.ChackNorrisServiceForCoroutine
import com.pastukhov.chucknorris.data.model.RandomJokeModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainView {

    @Inject
    lateinit var presenter: IMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val randomJokeRxJava: RandomJokeModel = presenter.getRandomJokeRxJava()

//        interactor.getData()
//            .subscribeOn()
//            .observeOn()
//            .subscribe(
//                onNext = {
//                    view.showResul()
//                },
//                onComplete{ },
//                onErrro{}
//            )
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isFinishing) {

        }
    }

    fun getRandomJoke(view: View) {
        textView.text = presenter.getRandomJokeCoroutine().value
        textView2.text = presenter.getRandomJokeRxJava().value

    }
}
