package com.uninet.wartarancage

import android.content.Intent
import android.net.MailTo
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uninet.wartarancage.utils.SharedPreferences
import java.io.File

class MainActivity2 : AppCompatActivity() {
    lateinit var webView : WebView
    lateinit var webSettings: WebSettings
    lateinit var progressBar: ProgressBar
    lateinit var fab : FloatingActionButton
    lateinit var fabaccount : FloatingActionButton
    var bundle : Bundle? = null
    lateinit var sp : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        webView = findViewById(R.id.wartarancagewebview)
        fabaccount = findViewById(R.id.accountmenu)
        progressBar = findViewById(R.id.loading)
        fab = findViewById(R.id.tulisberita)
        bundle = intent.extras
        sp = SharedPreferences(this)
        if (sp.isLogin == true){
            fabaccount.visibility = View.VISIBLE
        }

        if (bundle == null){
            webView.loadUrl("https://wartarancage.co")
        }
        else{
            val url = bundle?.getString("url")
            webView.loadUrl(url!!)
        }
        webViewSetup()

        fab.setOnClickListener {

            if (sp.isLogin == true){
                startActivity(Intent(this, WriteNewsActivity::class.java))
            }else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        fabaccount.setOnClickListener {
            startActivity(Intent(this,AccountActivity::class.java))
        }

    }

    private fun getPhotoFile(fileName: String): File {
        val fileStorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,".jpg",fileStorageDir)
    }

    fun webViewSetup(){
        progressBar.max = 100
        webSettings = webView.settings
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressBar.setProgress(newProgress)
            }

        }
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: String): Boolean {
                if(request.startsWith("https://wartarancage.co/bewara")){
                    val i = Intent(this@MainActivity2,LoginActivity::class.java)
                    i.setData(Uri.parse(request))
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    return false
                }else{
                    return false
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }

            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                super.onReceivedHttpError(view, request, errorResponse)
                startActivity(intent)
            }
        }
    }
}