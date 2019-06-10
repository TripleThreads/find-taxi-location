package com.tripleThreads.taxiyaz.fragments


import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.data.route.Route
import com.tripleThreads.taxiyaz.viewModel.CommentViewModel
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.fragment_comments.view.*
import kotlin.random.Random


class CommentsFragment : Fragment() {

    val nickNames = listOf<String>(
        "ঊʏᎾℒｏ(͡° ͜ʖ ͡°)",
        "FaargoGenius",
        "bullcheese",
        "titanum"
    )


    lateinit var viewModel: CommentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_comments, container, false)

        val route = arguments?.getSerializable("route") as Route


        val activityContext = activity as Context
        var recyclerView = view.commentsRecycler
        var adapter = CommentListAdapter(activityContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activityContext)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(CommentViewModel::class.java)

        viewModel.getComments(route.routeId)
        viewModel.comments.observe(this, Observer {
            comments -> comments.let { adapter.setComment(comments) }

        })

        return view
    }




    inner class CommentListAdapter(context: Context) : RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>(){
        var comments: List<Comment> = emptyList()

        private val inflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val recyclerViewItem = inflater.inflate(R.layout.comment_item,parent,false)

            return CommentViewHolder(recyclerViewItem)
        }

        override fun getItemCount(): Int = comments.size

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val comment = comments[position]
            holder.commentContent.text = comment.comment
            val index = Random(nickNames.size).nextInt()
            holder.userName.text = nickNames[index]
        }
        fun setComment(comments: List<Comment>){
            this.comments = comments
            notifyDataSetChanged()

        }


        inner class CommentViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
            val userName = itemView.commenterUserName
            val commentContent = itemView.comment
        }
    }


}