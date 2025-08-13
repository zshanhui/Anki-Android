package com.ichi2.anki.pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.ichi2.anki.R
import com.ichi2.anki.SingleFragmentActivity
import androidx.core.view.isVisible

class ArticlesFragment : Fragment(R.layout.page_fragment) {
    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = view.findViewById(R.id.webview)
        webView.apply {
            isVisible = true
            settings.javaScriptEnabled = true
            settings.displayZoomControls = false
            settings.builtInZoomControls = true
            settings.setSupportZoom(true)
            webViewClient = WebViewClient()
            // Ensure text is laid out for readability
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.textZoom = 115
            settings.defaultTextEncodingName = "utf-8"
            settings.loadsImagesAutomatically = true
            settings.domStorageEnabled = true
            loadUrl(ASSET_URL)
        }

        view.findViewById<MaterialToolbar?>(R.id.toolbar)?.apply {
            title = getString(R.string.articles)
            setNavigationOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    companion object {
        private const val ASSET_URL = "file:///android_asset/articles/index.html"

        fun getIntent(context: Context): Intent {
            return SingleFragmentActivity.getIntent(context, ArticlesFragment::class)
        }
    }
}