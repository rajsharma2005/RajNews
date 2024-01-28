package com.example.rajnews

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.rajnews.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse


class MainActivity : AppCompatActivity()  {


    private var binding : ActivityMainBinding? = null
    private  var articleList : MutableList<Article>? = null
    private lateinit var newsAdapter: NewsAdapter

    private val tabTitles = listOf("general", "india", "sports","entertainment", "health", "science","business", "technology")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.toolbarMain)

        binding!!.toolbarMain.setOnClickListener{
            getNews(null , null, null)
        }
        articleList = mutableListOf()
        getNews(null , null , null)
        //setUpRecyclerView()
        binding!!.rvArticles.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(articleList!!)
        binding!!.rvArticles.adapter = newsAdapter
        navigationView()

    }


    fun setUpProgressBAr(show : Boolean){
        if (show){
            binding!!.progressBar.visibility = View.VISIBLE
        }else{
            binding!!.progressBar.visibility = View.GONE
        }
    }

    private fun navigationView(){
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        val adapter = ViewPagerAdapter(this)
        adapter.addFragment(General())
        adapter.addFragment(India())
        adapter.addFragment(Sports())
        adapter.addFragment(Entertainment())
        adapter.addFragment(Health())
        adapter.addFragment(Science())
        adapter.addFragment(Business())
        adapter.addFragment(Technology())

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    // Change text color or background color for the selected tab
                    it.view.setBackgroundColor(resources.getColor(R.color.neutralGray))
                }

                // Code to execute when a tab is selected
                when (tab?.position) {
                    0 -> {
                        getNews(null , tabTitles[tab.position] , null)
                        }
                    1 -> {
                        getNews("in" ,null, null)
                        }
                    2 -> {
                        getNews(null , tabTitles[tab.position] , null)
                    }
                    3 -> {
                        getNews(null , tabTitles[tab.position] , null)
                    }
                    4 -> {
                        getNews(null , tabTitles[tab.position] , null)
                    }
                    5 -> {
                        getNews(null , tabTitles[tab.position] , null)
                    }
                    6 -> {
                        getNews(null , tabTitles[tab.position] , null)
                    }
                    7 -> {
                        getNews(null , tabTitles[tab.position] , null)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    // Reset text color or background color for unselected tab
                    it.view.setBackgroundColor(Color.TRANSPARENT)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    fun getNews(country: String?, category: String? , query : String?){

        //Here we are getting full News
        setUpProgressBAr(true)
        val newsApiClient = NewsApiClient("6d007d61802c4d708d62be0bd8f1f418")
        newsApiClient.getTopHeadlines(
            TopHeadlinesRequest.Builder()
                .language("en")
                .country(country)
                .category(category)
                .q(query)
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {

                    runOnUiThread {
                        setUpProgressBAr(false)
                        articleList = response.articles
                        newsAdapter.updateData(articleList!!)
                        newsAdapter.notifyDataSetChanged()
                    }

                }

                override fun onFailure(throwable: Throwable) {
                    Log.i("Fail" ,throwable.toString())
                }
            }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        //This is for the search option
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
               getNews(null , null ,query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               return false
            }
        })

        return true
    }

 }

//Here are all the Fragments

class Technology : Fragment() {

}

class Business : Fragment() {

}

class Science : Fragment() {

}

class Health : Fragment() {

}

class Entertainment : Fragment() {

}

class Sports : Fragment() {

}

class India : Fragment() {

}

class General : Fragment() {

}







