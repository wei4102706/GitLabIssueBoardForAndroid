package com.example.gitlabissueboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.example.gitlabissueboard.adapter.ViewPagerAdapter
import com.example.gitlabissueboard.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    val sharedViewModel by viewModel<IssueViewModel>()

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        // For SearchBar
        binding.etMainSearchBar.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s:CharSequence,start:Int,count:Int,after:Int){}
            override fun onTextChanged(s:CharSequence,start:Int,before:Int,count:Int){}
            override fun afterTextChanged(s:Editable){
                sharedViewModel.filterIssueByTitle(s.toString())
            }
        })

        // For ViewPager
        val viewPager=binding.vpMainSwitchPages
        val tabLayout=binding.tlMainMenu
        val adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter=adapter
        TabLayoutMediator(tabLayout,viewPager){tab,position->
            tab.text=resources.getStringArray(R.array.tab_name)[position]
        }.attach()

        // For RefreshButton
        binding.btnMainRefresh.setOnClickListener{
            Log.v("refresh button pressed","Issue Board Refreshed")
            sharedViewModel.refresh()
        }
    }
}