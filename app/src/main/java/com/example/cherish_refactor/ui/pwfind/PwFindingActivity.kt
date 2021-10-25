package com.example.cherish_refactor.ui.pwfind

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.cherish_refactor.R
import com.example.cherish_refactor.databinding.ActivityPwFindingBinding


class PwFindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPwFindingBinding
    lateinit var mBundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPwFindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeFragment()
    }

    private fun initializeFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_finding, PwFindingFirstFragment()).commit()
    }

    fun setActionBarTitlePwFinding(title: String?) {
        setSupportActionBar(binding.toolbarPwFinding)
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.icon_gnb_back)
            actionBar.setDisplayShowTitleEnabled(false)
            binding.toolbarTitleFinding.text = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(index: Int) {
        val transAction = supportFragmentManager.beginTransaction()

        when (index) {
            0 -> {
                transAction.replace(R.id.fragment_finding, PwFindingFirstFragment())
                    .addToBackStack(null)
                    .commit()
            }
            1 ->
                transAction.replace(R.id.fragment_finding, PwFindingSecondFragment())
                    .addToBackStack(null)
                    .commit()

            2 ->
                transAction.replace(R.id.fragment_finding, PwFindingThirdFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }


    fun postData(bundle: Bundle) {
        this.mBundle = bundle
    }
}