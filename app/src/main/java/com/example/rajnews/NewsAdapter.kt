package com.example.rajnews

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kwabenaberko.newsapilib.models.Article
import com.squareup.picasso.Picasso


class NewsAdapter(private val news: MutableList<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var articleList: MutableList<Article> = news


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = articleList[position]
        holder.newsTittle.setText(article.title)
        holder.newsSource.setText(article.title)
        Picasso.get().load(article.urlToImage)
            .error(R.drawable.image_not_suported)
            .placeholder(R.drawable.image_not_suported)
            .into(holder.newsImage)


        holder.itemView.setOnClickListener { view ->
            var intent = Intent(view.context , FullNews::class.java)
            intent.putExtra("url" , article.url)
            view.context.startActivity(intent)
        }




    }

    fun updateData(list: MutableList<Article>){
        articleList.clear()
        articleList.addAll(list)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }



    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val newsImage : ImageView = itemView.findViewById(R.id.news_image)
        val newsTittle : TextView = itemView.findViewById(R.id.news_tittle)
        val newsSource : TextView = itemView.findViewById(R.id.news_source)

    }
}