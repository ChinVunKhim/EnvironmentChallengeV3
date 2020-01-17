package com.example.environmentchallenge.ui.ranking

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer

import com.example.environmentchallenge.R

class RankingFragment : Fragment() {

    companion object {
        fun newInstance() = RankingFragment()
    }

    private lateinit var rankingViewModel: RankingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rankingViewModel = ViewModelProviders.of(this).get(RankingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_ranking, container, false)

        var title = root.findViewById<TextView>(R.id.world_ranking_title)
        var world_btn = root.findViewById<Button>(R.id.world_btn)
        var friend_btn = root.findViewById<Button>(R.id.friend_btn)

        world_btn.setOnClickListener {
            title.text = "World"
        }
        friend_btn.setOnClickListener {
            title.text = "Friend"
        }
        return root
    }

}
