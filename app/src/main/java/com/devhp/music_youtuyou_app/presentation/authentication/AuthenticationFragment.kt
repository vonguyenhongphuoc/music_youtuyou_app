package com.devhp.music_youtuyou_app.presentation.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.devhp.music_youtuyou_app.databinding.FragmentAuthenticationBinding
import com.devhp.music_youtuyou_app.presentation.signin.SignInFragment
import com.devhp.music_youtuyou_app.presentation.signup.SignUpFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AuthenticationFragment : Fragment() {

    private lateinit var binding: FragmentAuthenticationBinding
    private lateinit var fragments: ArrayList<Pair<Fragment, String>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAuthenticationBinding.inflate(inflater, container, false)
        initFragments()
        binding.apply {
            authenticationViewpager.adapter = initAdapter()
            initMediator(tabLayout, authenticationViewpager)
        }
        return binding.root
    }

    private fun initAdapter(): AuthenticationAdapter {
        return AuthenticationAdapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle
        )
    }

    private fun initFragments() {
        fragments = arrayListOf(
            Pair(SignInFragment(), "Sign In"),
            Pair(SignUpFragment(), "Sign Up")
        )
    }

    private fun initMediator(tabLayout: TabLayout, authenticationViewpager: ViewPager2) {
        TabLayoutMediator(tabLayout, authenticationViewpager) { tab, position ->
            // Set up the title for each tab
            tab.text = fragments[position].second

        }.attach()

    }

}