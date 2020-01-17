package com.example.environmentchallenge.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.environmentchallenge.R
import com.example.environmentchallenge.ui.profile.ProfileFragment
import com.facebook.*
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.ErrorDialogFragment
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var loginViewModel: LoginViewModel
    lateinit var callbackManager: CallbackManager
    lateinit var loginButton: LoginButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        callbackManager = CallbackManager.Factory.create()
        tokenTracker.startTracking()
        loginButton = root.findViewById(R.id.login_button) as LoginButton

        loginButton.setPermissions(Arrays.asList("email", "public_profile", "user_age_range"))
        loginButton.setLoginBehavior(LoginBehavior.WEB_ONLY)
        loginButton.setFragment(this)
        checkLoginStatus()
        loginButton.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {

            }

            override fun onCancel() {
                Toast.makeText(activity, "Request Has Canceled", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {

            }
        })

        return root
    }

    var tokenTracker: AccessTokenTracker = object: AccessTokenTracker(){
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?
        ) {
            if(currentAccessToken != null)
            {
                loginButton.isVisible = false
                loadUserProfile(currentAccessToken)
            }else{
                loginButton.isVisible = true
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadUserProfile(newAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(newAccessToken, object: GraphRequest.GraphJSONObjectCallback {
            override fun onCompleted(`object`: JSONObject, response: GraphResponse) {
                try
                {
                    val first_name = `object`.getString("first_name")
                    val last_name = `object`.getString("last_name")
                    val email = `object`.getString("email")
                    val id = `object`.getString("id")
                    val image_url = "https://graph.facebook.com/" + id + "/picture?type=normal"
                    var age = `object`.getString("age_range")
                    age = age.substring(age.length-3 until age.length-1)
                    val requestOptions = RequestOptions()
                    requestOptions.dontAnimate()
                    /*Glide.with(this@LoginFragment).load(image_url).into(circleImageView)*/
                    Toast.makeText(activity, "Successfully login with " + first_name + " " + last_name, Toast.LENGTH_LONG).show()
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_nav_daily_challenge)
                }
                catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email,id, age_range")
        request.setParameters(parameters)
        request.executeAsync()

    }

    private fun checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null)
        {
            loginButton.isVisible = false
            loadUserProfile(AccessToken.getCurrentAccessToken())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tokenTracker.stopTracking()
    }
}
