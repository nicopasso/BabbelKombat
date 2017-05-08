package com.nicopasso.babbelkombat

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nicopasso.babbelkombat.ui.GameActivity
import com.nicopasso.babbelkombat.ui.StartActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class StartActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(StartActivity::class.java)

    @Test
    fun testTextViews() {

        onView(withId(R.id.title_textview)).check(matches(isDisplayed()))
        onView(withId(R.id.title_textview)).check(matches(withText(R.string.title)))

        onView(withId(R.id.number_tetxview)).check(matches(isDisplayed()))
        onView(withId(R.id.number_tetxview)).check(matches(withText(R.string.number_of_players)))
    }

    @Test
    fun testButtonsUI() {
        onView(withId(R.id.one_player_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.one_player_btn)).check(matches(withText("1")))
        onView(withId(R.id.two_players_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.two_players_btn)).check(matches(withText("2")))
        onView(withId(R.id.three_players_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.three_players_btn)).check(matches(withText("3")))
        onView(withId(R.id.four_players_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.four_players_btn)).check(matches(withText("4")))
    }

    @Test
    fun testButtonsClick() {
        Intents.init()
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.one_player_btn)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(GameActivity::class.java.name))
        Intents.release()

        Intents.init()
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.two_players_btn)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(GameActivity::class.java.name))
        Intents.release()

        Intents.init()
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.three_players_btn)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(GameActivity::class.java.name))
        Intents.release()

        Intents.init()
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.four_players_btn)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(GameActivity::class.java.name))
        Intents.release()
    }
}

