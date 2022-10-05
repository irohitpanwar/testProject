package com.project.cargoproj

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.cargoproj.repository.ApiHelper
import com.project.cargoproj.model.ResponseModel
import com.project.cargoproj.network.RetrofitBuilder
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var photoRV: RecyclerView
    lateinit var photoRVAdapter: ImageAdapter
    lateinit var progress: ProgressBar
    lateinit var photoList: ArrayList<ResponseModel>
    private var currentPage: Int = 1
    private var isLoading = false

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setUp viewModel
        progress = findViewById(R.id.progress)
        setUpList()
        setupViewModel()
        getDataFromApi()
        var testBtn = findViewById<TextView>(R.id.test_btn)
        testBtn.setOnClickListener {
            getDataFromApi()
        }
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]

    }
    private fun getDataFromApi(){
        //?limit=100&page=10&order=Desc
        var response =viewModel.getImage(page = currentPage)
        response?.enqueue(object : Callback<List<ResponseModel>?> {
            override fun onResponse(call: Call<List<ResponseModel>?>, response: Response<List<ResponseModel>?>) {
                Log.e("testIt",response.toString())
                for(data in response.body()!!){
                    photoList.add(data)
                    photoRVAdapter.notifyItemChanged(photoList.size-1)
                }
                isLoading = false
                progress.visibility = View.GONE
            }
            override fun onFailure(call: Call<List<ResponseModel>?>, t: Throwable) {
                Log.e("testIt",t.toString())
                Toast.makeText(baseContext,"API call failed",Toast.LENGTH_SHORT).show()
                isLoading = false
                progress.visibility = View.GONE
            }
        })
    }
    private fun setUpList(){
        //init recycle view and adapter
        photoRV = findViewById(R.id.image_view)
        photoList = ArrayList()
        photoRVAdapter = ImageAdapter(photoList)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        photoRV.layoutManager = staggeredGridLayoutManager
        // adapter to our recycler view.
        photoRV.adapter = photoRVAdapter
        photoRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) //check for scroll down
                {
                    val totalItemCount: Int = (photoRV.layoutManager as StaggeredGridLayoutManager).itemCount
                    val lastVisibleItemPositions =
                        (photoRV.layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(
                            null
                        )
                    var lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
                    if (!isLoading && lastVisibleItemPosition == totalItemCount - 1) {
                        currentPage += 1
                        isLoading = true
                        progress.visibility = View.VISIBLE

                        getDataFromApi()
                    }
                }
            }
        })
        //network call
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

}