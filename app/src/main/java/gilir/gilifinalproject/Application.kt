package gilir.gilifinalproject

import android.app.Application
import gilir.gilifinalproject.data.AppDataBase

import gilir.gilifinalproject.repository.AppRepository


class Application : Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: Application

        private val db : AppDataBase by lazy {
            AppDataBase.create(instance)
        }

        val repository : AppRepository by lazy {
            AppRepository(db.songDao())
        }
    }

}