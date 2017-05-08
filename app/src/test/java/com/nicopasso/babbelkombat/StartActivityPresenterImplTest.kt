package com.nicopasso.babbelkombat

import android.content.Context
import android.content.res.AssetManager
import com.nicopasso.babbelkombat.model.Word
import com.nicopasso.babbelkombat.ui.StartActivityPresenterImpl
import com.nicopasso.babbelkombat.ui.StartView
import com.nicopasso.babbelkombat.utils.BKUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import rx.observers.TestSubscriber

@RunWith(MockitoJUnitRunner::class)
class StartActivityPresenterImplTest {

    var presenter: StartActivityPresenterImpl? = null

    @Mock var context: Context? = null
    @Mock var assetManager: AssetManager? = null
    @Mock var utils: BKUtils? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        doReturn(assetManager).`when`(context)?.assets

        presenter = StartActivityPresenterImpl()
        presenter!!.attachView(Mockito.mock(StartView::class.java))
    }

    @Test
    fun loadWords_test() {

        val wordsString = "[{\"text_eng\": \"hello\",\"text_spa\": \"hola\"}, " +
                "{\"text_eng\": \"game\",\"text_spa\": \"juego\"}, " +
                "{\"text_eng\": \"rule\",\"text_spa\": \"regla\"}]"

        val words = mutableListOf<Word>()
        words.add(Word("hello", "hola"))
        words.add(Word("game", "juego"))
        words.add(Word("rule", "regla"))

        val testSubscriber = TestSubscriber<Word>()

        presenter?.loadWords(context!!)?.subscribe(testSubscriber)

        testSubscriber.assertReceivedOnNext(words)

    }

}