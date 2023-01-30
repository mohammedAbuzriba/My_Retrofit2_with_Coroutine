package ly.youcan.my_retrofit2_with_coroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ly.youcan.my_retrofit2_with_coroutine.databinding.PostItemLaoutBinding

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding:PostItemLaoutBinding):
        RecyclerView.ViewHolder(binding.root)

    private val differCallback=object :DiffUtil.ItemCallback<PostItem>(){
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.equals(newItem)
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostItemLaoutBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun getItemCount()=differ.currentList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = differ.currentList[position]
        holder.binding.apply {
            tvBody.text=currentPost.body
            //tvBody.text=""
            tvTitle.text=currentPost.title
        }
        holder.itemView.setOnClickListener(){
            mView-> Snackbar.make(mView,"UserId: ${currentPost.userId}\nId: ${currentPost.id}",Snackbar.LENGTH_SHORT).show()
        }
    }

}