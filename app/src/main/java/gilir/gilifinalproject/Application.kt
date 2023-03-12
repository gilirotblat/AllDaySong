package gilir.gilifinalproject

import android.app.Application
import gilir.gilifinalproject.data.AppDataBase

import gilir.gilifinalproject.repository.SongRepository


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

        val repository : SongRepository by lazy {
            SongRepository(db.songDao())
        }
    }

}