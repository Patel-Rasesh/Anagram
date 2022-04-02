package com.example.anagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.anagram.fragments.ComposeFragment
import com.example.anagram.fragments.FeedFragment
import com.example.anagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener{
            // When a user clicks on a specific item based on item_id
            item ->
            var fragmentToShow: Fragment? = null

            when (item.itemId){
                R.id.action_home  -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    fragmentToShow = FeedFragment()
                }
                R.id.action_compose -> {
                    Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show()
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    fragmentToShow = ProfileFragment()
                }
            }
            if (fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true        // This signifies that we have handled this particular interaction
        }
        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
  //      queryPosts()
    }

    companion object{
        const val TAG ="MainActivity"
    }
}