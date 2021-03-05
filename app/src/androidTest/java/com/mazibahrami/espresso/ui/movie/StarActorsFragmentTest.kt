package com.mazibahrami.espresso.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mazibahrami.espresso.R
import com.mazibahrami.espresso.factory.MovieFragmentFactory
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest {

    @Test
    fun test_isDirectorsListVisible() {
        // GIVEN
        val directors = arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory(null, null)
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)
        val scenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // VERIFY
        Espresso.onView(ViewMatchers.withId(R.id.directors_text))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.withText(
                        DirectorsFragment.stringBuilderForDirectors(
                            directors
                        )
                    )
                )
            )
    }
}