package com.example.rajnews

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.rajnews.databinding.ActivityFullNewsBinding

class FullNews : AppCompatActivity() {

    private var binding : ActivityFullNewsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullNewsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        var url : String? = intent.getStringExtra("url")
        val webSettings : WebSettings = binding!!.webView.settings
        webSettings.javaScriptEnabled = true
        binding!!.webView.webViewClient = WebViewClient()
        binding!!.webView.loadUrl(url!!)
    }

    override fun onBackPressed() {
        if (binding!!.webView.canGoBack()){
            binding!!.webView.goBack()
        }else {
            super.onBackPressed()
        }
    }
}