package com.tripleThreads.taxiyaz.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tripleThreads.taxiyaz.R
import com.tripleThreads.taxiyaz.data.comment.Comment
import com.tripleThreads.taxiyaz.data.newRoute.Route
import com.tripleThreads.taxiyaz.data.user.User
import com.tripleThreads.taxiyaz.viewModel.CommentViewModel
import com.tripleThreads.taxiyaz.viewModel.RouteViewModel
import com.tripleThreads.taxiyaz.viewModel.UserViewModel
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
    lateinit var routeViewModel: RouteViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comments, container, false)


        val route = arguments?.getSerializable("route") as Route


        val activityContext = activity as Context
        val recyclerView = view.commentsRecycler
        val adapter = CommentListAdapter(activityContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activityContext)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(CommentViewModel::class.java)
        routeViewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)

        viewModel.getComments(route.routeId)
        viewModel.comments.observe(this, Observer { comments ->
            comments.let { adapter.setComment(comments) }

        })

        val routeMap = BestRouteFragment()

        var args = Bundle()
        args.putSerializable("route", route)
        routeMap.arguments = args

        childFragmentManager.beginTransaction()
            .replace(R.id.route_fragment_viewpager, routeMap)
            .commit()
        return view
    }

    inner class CommentListAdapter(context: Context) : RecyclerView.Adapter<CommentListAdapter.CommentViewHolder>() {
        var comments: List<Comment> = emptyList()

        private val inflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val recyclerViewItem = inflater.inflate(R.layout.comment_item, parent, false)

            var sharedPreference = context?.getSharedPreferences("user", Context.MODE_PRIVATE)
            var userPhone: String = sharedPreference!!.getString("user", "")!!

            recyclerViewItem.setOnClickListener {
                if (comments[viewType].userId.equals(userPhone)) {
                    //Toast.makeText(context,"${comments[viewType]}",Toast.LENGTH_SHORT).show()

                    val dialog = CommentAndRatingFragment()
                    val bundle = Bundle()
                    bundle.putString(ROUTE_KEY_COMMENT, "")
                    bundle.putSerializable("comment", comments[viewType])
                    dialog.arguments = bundle
                    dialog.show(
                        fragmentManager!!,
                        "add_comment_dialog"
                    )
                }

            }
            return CommentViewHolder(recyclerViewItem)
        }

        override fun getItemCount(): Int = comments.size

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            val comment = comments[position]
            holder.commentContent.text = comment.comment
            val index = Random(nickNames.size - 1).nextInt()
            holder.userName.text = comment.userId
        }

        fun setComment(comments: List<Comment>) {
            this.comments = comments
            notifyDataSetChanged()

        }


        inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val userName = itemView.commenterUserName
            val commentContent = itemView.comment

        }
    }


}
