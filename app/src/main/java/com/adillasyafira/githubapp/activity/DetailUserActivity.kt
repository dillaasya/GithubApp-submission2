package com.adillasyafira.githubapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.adillasyafira.githubapp.R
import com.adillasyafira.githubapp.adapter.DetailSectionAdapter
import com.adillasyafira.githubapp.databinding.ActivityDetailUserBinding
import com.adillasyafira.githubapp.model.DetailUserResponse
import com.adillasyafira.githubapp.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding : ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)
        detailViewModel.showDetailUsers(username.toString())

        detailViewModel.detailUser.observe(this, { detail ->
            setUserDetail(detail)
        })

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        val sectionsDetailAdapter = DetailSectionAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsDetailAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun setUserDetail(userDetails: DetailUserResponse) {
        Glide.with(this)
            .load(userDetails.avatarUrl)
            .circleCrop()
            .into(binding.imgUser)
        binding.tvName.text = userDetails.name
        binding.tvUsername.text = userDetails.login
        binding.tvFollowers.text = userDetails.followers.toString()
        binding.tvFollowing.text = userDetails.following.toString()
        binding.tvCompany.text = userDetails.company
        binding.tvLocation.text = userDetails.location
        binding.tvRepos.text = userDetails.publicRepos.toString()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
        const val EXTRA_USERNAME = "extra_username"
    }
}