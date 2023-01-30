package ly.youcan.my_retrofit2_with_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ly.youcan.my_retrofit2_with_coroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.isVisible=true
        setupRV()
        getAllPosts()
    }


    private fun setupRV(){

        postAdapter = PostAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
            setHasFixedSize(true)
        }
    }
    private fun getAllPosts(){
        lifecycleScope.launchWhenCreated {

            val response=RetrofitInstance.retrofit.getAllPosts()
            if (response.isSuccessful && response.body()!=null){
                postAdapter.differ.submitList(response.body())
                Log.d("respons0","getAllPosts ${response.body()}")
            }else{
                Toast.makeText(
                    this@MainActivity,
                    "Error code : ${response.code()}",
                    Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.isVisible=false
        }
    }
}