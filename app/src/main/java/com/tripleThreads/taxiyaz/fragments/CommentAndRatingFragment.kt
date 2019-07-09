package com.tripleThreads.taxiyaz.fragments

import android.content.Context
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
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.databinding.FragmentCommentAndRatingBinding
import com.tripleThreads.taxiyaz.viewModel.CommentViewModel
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_comment_and_rating.*
import java.util.*


class CommentAndRatingFragment : DialogFragment(), AddCommentEventListener {
    private lateinit var userViewModel: UserViewModel
    private lateinit var routeViewModel: RouteViewModel
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var route: Route

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


        if (arguments?.getSerializable("comment") != null){
            var comment = arguments?.getSerializable("comment") as Comment

            binding.comment = comment
            binding.listener = this

            return binding.root
        }
        route = arguments?.getSerializable("route") as Route
        var sharedPreference = context?.getSharedPreferences("user",Context.MODE_PRIVATE)
        var userPhone: String = sharedPreference!!.getString("user", "")!!

        var comment = Comment(-1, route.routeId, userPhone, route.title, null)

        binding.comment = comment
        binding.listener = this

        return binding.root
    }

    override fun onButtonClick(comment: Comment) {
        if(comment.comment.trim() != "" && comment.id.toInt() == -1){
            Toast.makeText(context, comment.comment, Toast.LENGTH_LONG).show()
            commentViewModel.insert(comment)
            var ratingBar = route_rating_bar

            routeViewModel.rateRoute(route.routeId,ratingBar.rating)


            Toast.makeText(context,"Thank you for your contribution",Toast.LENGTH_SHORT).show()
            this.dismiss()
        }
        else{

            if(comment.comment.trim() !=""){
                comment.date = null
                commentViewModel.update(comment)
                Toast.makeText(context,"Comment being updated",Toast.LENGTH_SHORT).show()
            }
            else{
                onDeleteClick(comment)
            }

            this.dismiss()
        }

    }

    override fun onDeleteClick(comment: Comment) {
        commentViewModel.delete(comment)
        Toast.makeText(context,"Comment being deleted",Toast.LENGTH_SHORT).show()
        this.dismiss()
    }


}

interface AddCommentEventListener {
    fun onButtonClick(comment: Comment)
    fun onDeleteClick(comment:Comment)
}