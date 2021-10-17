package com.adillasyafira.githubapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adillasyafira.githubapp.adapter.FollowAdapter
import com.adillasyafira.githubapp.adapter.FollowingAdapter
import com.adillasyafira.githubapp.databinding.FragmentFollowingBinding
import com.adillasyafira.githubapp.model.FollowResponseItem
import com.adillasyafira.githubapp.viewmodel.FollowViewModel
import java.util.ArrayList

class FollowingFragment : Fragment() {
    private val list = ArrayList<FollowResponseItem>()
    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followViewModel: FollowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)

        val username = activity?.intent?.getStringExtra(EXTRA_USERS)
        followViewModel.showFollowing(username.toString())

        followViewModel.listFollowing.observe(viewLifecycleOwner, { following ->
            list.addAll(following)
            showRecyclerList()
        })

        followViewModel.isLoading.observe(viewLifecycleOwner, { loading ->
            showLoading(loading)
        })
    }

    private fun showRecyclerList() {
        binding.rvFollowing.layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.setHasFixedSize(true)
        val adapter = FollowingAdapter(list)
        binding.rvFollowing.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_USERS = "extra_users"
    }
}
