package com.tripleThreads.taxiyaz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.databinding.FragmentCommentAndRatingBinding
import com.tripleThreads.taxiyaz.viewModel.CommentViewModel
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import java.util.*


class CommentAndRatingFragment : DialogFragment(), AddCommentEventListener {
    private lateinit var userViewModel: UserViewModel
    private lateinit var routeViewModel: RouteViewModel
    private lateinit var commentViewModel: CommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCommentAndRatingBinding>(
            inflater,
            R.layout.fragment_comment_and_rating,
            container,
            false
        )
        val routeTitle = arguments?.getString(ROUTE_KEY_COMMENT)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        routeViewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel::class.java)

        routeViewModel.getRouteByName(routeTitle!!)

        val comment = Comment(1, 0, userViewModel.user!!.phoneNumber, "new comment", Date())

//        routeViewModel.searchedRoute.observe(this, androidx.lifecycle.Observer {
//            run {
//                comment.routeId = it[0].routeId
//            }
//        })
        binding.comment = comment
        binding.listener = this

        return binding.root
    }

    override fun onButtonClick(comment: Comment) {
        Toast.makeText(context, comment.comment, Toast.LENGTH_LONG).show()
        this.dismiss()
    }


}

interface AddCommentEventListener {
    fun onButtonClick(comment: Comment)
}