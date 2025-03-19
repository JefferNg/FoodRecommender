package com.zybooks.foodrecommender

import android.app.Application
import com.zybooks.foodrecommender.data.RecommenderRepository

class RecommenderApplication: Application() {
    lateinit var recommenderRepository: RecommenderRepository

    override fun onCreate() {
        super.onCreate()
        recommenderRepository = RecommenderRepository(this.applicationContext)
    }
}