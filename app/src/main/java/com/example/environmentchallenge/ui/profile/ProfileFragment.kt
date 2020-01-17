package com.example.environmentchallenge.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide

import com.example.environmentchallenge.R
import com.example.environmentchallenge.ui.login.LoginViewModel
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var loginViewModel: LoginViewModel
    lateinit var name:TextView
    lateinit var age:TextView
    lateinit var circleImageView: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        name = root.findViewById(R.id.name_edit)
        age = root.findViewById(R.id.age_edit)
        circleImageView = root.findViewById(R.id.profile_pic)

        //loadUserProfile()

        return root
    }
    var tokenTracker: AccessTokenTracker = object: AccessTokenTracker(){
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?
        ) {
            if(currentAccessToken == null)
            {
                view?.findNavController()?.navigate(R.id.action_nav_profile_to_nav_login)
            }
        }
    }

//    private fun loadUserProfile(){
//
//
//    }
}
