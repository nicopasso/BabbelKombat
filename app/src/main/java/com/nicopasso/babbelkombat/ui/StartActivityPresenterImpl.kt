package com.nicopasso.babbelkombat.ui

import android.content.Context
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.nicopasso.babbelkombat.base.BasePresenter
import com.nicopasso.babbelkombat.model.Word
import com.nicopasso.babbelkombat.utils.BKUtils
import rx.Observable
import javax.inject.Inject

class StartActivityPresenterImpl @Inject constructor(): BasePresenter<StartView>() {

    fun loadWords(context: Context): Observable<Word> {
        return Observable.defer {
            Observable.just(Gson().fromJson<List<Word>>(BKUtils().loadJSONFromAsset(context)!!))
                    .flatMapIterable {
                        words -> words
                    }
        }
    }

}