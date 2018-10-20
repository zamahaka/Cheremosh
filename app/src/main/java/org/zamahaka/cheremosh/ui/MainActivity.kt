package org.zamahaka.cheremosh.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.zamahaka.cheremosh.R
import org.zamahaka.cheremosh.ui.concert.ConcertListFragment
import org.zamahaka.cheremosh.ui.notes.NotesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_news -> showConcertListFragment()

                R.id.navigation_concerts -> showConcertListFragment()

                R.id.navigation_notes -> showNotesListFragment()

                R.id.navigation_profile -> showConcertListFragment()
            }

            true
        }

        bottomNavigation.setOnNavigationItemReselectedListener {
            (supportFragmentManager.findFragmentById(R.id.fragmentContainer_main) as? ReselectListener)?.onReselected()
        }

        bottomNavigation.selectedItemId = R.id.navigation_concerts

    }


    private fun showConcertListFragment() {
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainer_main) is ConcertListFragment) return

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer_main, ConcertListFragment())
                .commit()
    }

    private fun showNotesListFragment() {
        if (supportFragmentManager.findFragmentById(R.id.fragmentContainer_main) is NotesListFragment) return

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer_main, NotesListFragment())
                .commit()
    }

}
