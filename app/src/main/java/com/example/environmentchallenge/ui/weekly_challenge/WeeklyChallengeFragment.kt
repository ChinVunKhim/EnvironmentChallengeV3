package com.example.environmentchallenge.ui.weekly_challenge

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.postDelayed
import androidx.lifecycle.Observer

import com.example.environmentchallenge.R
import com.example.environmentchallenge.database.challenge.Challenge

class WeeklyChallengeFragment : Fragment() {

    companion object {
        fun newInstance() = WeeklyChallengeFragment()
    }

    private lateinit var weeklyChallengeViewModel: WeeklyChallengeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        weeklyChallengeViewModel = ViewModelProviders.of(this).get(WeeklyChallengeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weekly_challenge, container, false)


        var doneButton=root.findViewById<Button>(R.id.done_btn)
        var progressBar=root.findViewById<ProgressBar>(R.id.progress_bar)
        var progressBarText=root.findViewById<TextView>(R.id.progress_bar_text)
        val textView: TextView = root.findViewById(R.id.text_weekly_challenge)

        if(savedInstanceState!=null)
        {
            weeklyChallengeViewModel.count=savedInstanceState.getInt("MyCurrentProgress")
        }

        weeklyChallengeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        progressBarText.text=weeklyChallengeViewModel.getText()
        progressBar.setProgress(weeklyChallengeViewModel.count)
        doneButton.setEnabled(weeklyChallengeViewModel.doneButton)
        doneButton.setBackgroundColor(weeklyChallengeViewModel.color)

        doneButton.setOnClickListener {
            progressBar.setProgress(weeklyChallengeViewModel.addProgress())
            progressBarText.text=weeklyChallengeViewModel.getText()
            if(weeklyChallengeViewModel.count==7)
            {
                weeklyChallengeViewModel.count=0


            }
            doneButton.setEnabled(weeklyChallengeViewModel.isDone())
            doneButton.setBackgroundColor(weeklyChallengeViewModel.color)
            doneButton.postDelayed(5000)
            {
                doneButton.setEnabled(weeklyChallengeViewModel.notDone())
                doneButton.setBackgroundColor(weeklyChallengeViewModel.color)
                val newChallenge: Challenge?=weeklyChallengeViewModel.changeChallenge()
                if (newChallenge != null) {
                    textView.text= newChallenge.challengeName+"\n\n"+newChallenge.challengeDesc
                }
            }



        }



        return root    }

    override fun onSaveInstanceState(savedInstanceState:Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("MyCurrentProgress",weeklyChallengeViewModel.count )


    }



}
