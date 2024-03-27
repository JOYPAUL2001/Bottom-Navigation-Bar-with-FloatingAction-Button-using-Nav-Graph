package com.example.buttomnavigation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.buttomnavigation.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private val rotateopen: Animation by lazy { AnimationUtils. loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateclose: Animation by lazy { AnimationUtils. loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils. loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils. loadAnimation(this, R.anim.to_bottom_anim) }

//    private lateinit var fragmentManager: FragmentManager

    private var clicked = false
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding.navView.background=null
        binding.navView.menu.getItem(1).isEnabled = false

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)


        binding.fab.setOnClickListener {
            onAddButtonClicked()
        }
        binding.fabCam.setOnClickListener {
            Toast.makeText(this,"Camera Section",Toast.LENGTH_SHORT).show()
        }

        binding.fabUpload.setOnClickListener {
            Toast.makeText(this,"Gallery Section",Toast.LENGTH_SHORT).show()
        }

//        binding.navView.setOnItemReselectedListener {item ->
//            when(item.itemId){
//                R.id.FilesFragment ->{
//                    openFragment(FilesFragment())
//                    true
//                }
//                R.id.AccountFragment-> {
//                    openFragment(AccountFragment())
//                    true
//                }
//                else -> false
//            }
//        }
//
//        //fragmentManager = supportFragmentManager
//        openFragment(FilesFragment())





    }

    private fun onAddButtonClicked() {
        setVisibilty(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked=!clicked
    }

    private fun setAnimation(clicked: Boolean) {
      if(!clicked){
          binding.fabCam.visibility = View.VISIBLE
          binding.fabUpload.visibility = View.VISIBLE
      }else{
          binding.fabCam.visibility = View.INVISIBLE
          binding.fabUpload.visibility = View.INVISIBLE
      }
    }

    private fun setVisibilty(clicked: Boolean) {
       if(!clicked){
           binding.fabCam.startAnimation(fromBottom)
           binding.fabUpload.startAnimation(fromBottom)
           binding.fab.startAnimation(rotateopen)
       }
        else{
           binding.fabCam.startAnimation(toBottom)
           binding.fabUpload.startAnimation(toBottom)
           binding.fab.startAnimation(rotateclose)
       }
    }

    private fun setClickable(clicked: Boolean){
        if(!clicked){
            binding.fabCam.isClickable=true
            binding.fabUpload.isClickable=true
        }
        else{
            binding.fabCam.isClickable=false
            binding.fabUpload.isClickable=false
        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//      when(item.itemId){
//          R.id.FilesFragment -> openFragment(FilesFragment())
//          R.id.AccountFragment -> openFragment(AccountFragment())
//      }
//        return true
//    }

//    private fun openFragment(fragment: Fragment){
//       supportFragmentManager.beginTransaction().replace(R.id.NavHostFragment, fragment).commit()
//    }
}