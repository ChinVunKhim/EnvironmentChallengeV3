package com.example.environmentchallenge.ui.daily_challenge

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.postDelayed
import androidx.lifecycle.Observer

import com.example.environmentchallenge.R
import com.example.environmentchallenge.database.challenge.Challenge

class DailyChallengeFragment : Fragment() {

    companion object {
        fun newInstance() = DailyChallengeFragment()
    }

    private lateinit var dailyChallengeViewModel: DailyChallengeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dailyChallengeViewModel = ViewModelProviders.of(this).get(DailyChallengeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_daily_challenge, container, false)
        val textView: TextView = root.findViewById(R.id.text_daily_challenge)
        val doneButton=root.findViewById<Button>(R.id.done_btn2)

        dailyChallengeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        doneButton.setEnabled(dailyChallengeViewModel.doneButton)
        doneButton.setBackgroundColor(dailyChallengeViewModel.color)

        doneButton.setOnClickListener {
            doneButton.setEnabled(dailyChallengeViewModel.isDone())
            doneButton.setBackgroundColor(dailyChallengeViewModel.color)
            doneButton.postDelayed(5000)
            {
                doneButton.setEnabled(dailyChallengeViewModel.notDone())
                doneButton.setBackgroundColor(dailyChallengeViewModel.color)

                val newChallenge:Challenge?=dailyChallengeViewModel.changeChallenge()
                if (newChallenge != null) {
                    textView.text= newChallenge.challengeName+"\n\n"+newChallenge.challengeDesc
                }
            }

        }


        return root
    }


}
