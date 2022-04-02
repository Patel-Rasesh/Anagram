package com.example.anagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anagram.MainActivity
import com.example.anagram.Post
import com.example.anagram.PostAdapter
import com.example.anagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

open class FeedFragment : Fragment() {

    lateinit var postsRv : RecyclerView
    lateinit var adapter : PostAdapter
    val allPosts : MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup views and click listeners
        postsRv = view.findViewById(R.id.postRecyclerView)

        // 1. Create layout for each row in RV
        // Refer to item_post.xml
        // 2. Give data source to each row
        // Refer Post.kt
        // 3. Adapter for bridging
        // 4. Set adapter on RV
        adapter = PostAdapter(requireContext(), allPosts)
        postsRv.adapter = adapter
        // 5. Layout manager on RV
        postsRv.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()
    }

    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USERNAME)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null){
                    Log.e(TAG, "Something has gone wrong!")
                }
                else {
                    Log.i(MainActivity.TAG, "Successfully proceeded")
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(
                                TAG,
                                "Post " + post.getDescription() + ", username: " + post.getUsername()?.username
                            )
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
    companion object{
       const val TAG = "FeedFragment"
    }
}
