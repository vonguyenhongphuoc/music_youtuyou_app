package com.devhp.music_youtuyou_app.presentation.webview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.devhp.music_youtuyou_app.databinding.FragmentWebViewBinding
import com.devhp.music_youtuyou_app.presentation.main.MainActivity
import java.io.ByteArrayInputStream
import java.io.InputStream


class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val url = requireArguments().getString("url")
        setupWebView(requireActivity(), binding.webView, url ?: "https://google.com")
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack();
            } else {
                findNavController().popBackStack()
            }

        }
        return binding.root
    }

    fun setupWebView(context: Context, webView: WebView, url: String) {
        // Khởi tạo WebViewClient để xử lý các sự kiện liên quan đến trang web
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
//                binding.webView.loadUrl(
//                    "javascript:(function() { " +
//                            "var images = document.getElementsByTagName('img');" +
//                            "for (var i = 0; i < images.length; i++) {" +
//                            "   var image = images[i];" +
//                            "   var src = image.src.toLowerCase();" +
//                            "   if (src.endsWith('.gif')) {" + // Kiểm tra định dạng hình ảnh
//                            "       image.style.display = 'none';" +
//                            "   }" +
//                            "}" +
//                            "})()"
//                );
            }

            @Deprecated("Deprecated in Java")
            override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                Log.i(MainActivity.TAG, url)

//                if (!url.contains("/uploads")) {
//                    if (url.contains("ads") || url.contains("shopee") || url.contains("shop") || url.contains(
//                            "fb88"
//                        ) || url.contains(
//                            "i9bet"
//                        )
//                    ) {
//                        val textStream = ByteArrayInputStream("".toByteArray())
//                        return getTextWebResource(textStream)
//                    }
//
//                }

                /*   return if (url.contains("https://www.youtube.com/pagead/interaction/?") || url.contains(" https://m.youtube.com/api/stats/") || url.contains("doubleclick") ||  url.contains("https://www.youtube.com/pagead/interaction/") || url.contains("https://tpc.googlesyndication.com/") || url.contains("ads") || url.contains("https://rr8") || url.contains("https://play.google.com/log?")) {
                       val textStream = ByteArrayInputStream("".toByteArray())
                       getTextWebResource(textStream)
                   } else {
                       super.shouldInterceptRequest(view, url)
                   }*/

                val param1 = "https://m.youtube.com/api/stats/"
                val param14 = "https://www.youtube.com/watch"
                val param15 = "https://m.youtube.com/s/search/"
                val param16 = "https://m.youtube.com/s/_/ytmweb/"
                val param17 = "https://m.youtube.com/static/"
                val param18 = "https://m.youtube.com/s/player/"
                val param3 = "https://rr"
                val param11 = "https://play.google.com/log"
                val param6 = "https://i.ytimg.com/"
                val param13 = "https://m.youtube.com/youtubei/v1/next"
                val param19 = "https://m.youtube.com/?pbj"
                val param20 = "https://yt3.ggpht.com/"
                val param21 = "https://m.youtube.com/youtubei/v1/guide"
                val param22 = "https://m.youtube.com/youtubei/v1/att/get"
                val param25 = "https://m.youtube.com/youtubei/v1/browse"
                val param26 = "https://m.youtube.com/youtubei/v1/player"
                val textStream = ByteArrayInputStream("".toByteArray())


                return if (!url.contains(param13) && (url.contains(param1)
                            || url.contains(param3) || url.contains(
                        param6
                    ) || url.contains(
                        param3
                    ) || url.contains(param11) || url.contains(param14) || url.contains(param15) || url.contains(
                        param16
                    )) || url.contains(param17) || url.contains(param18) || url.contains(param19) || url.contains(
                        param20
                    )  || url.contains(param25) || url.contains(param21) || url.contains(param22) || url.contains(param26)
                ) {
                    Log.d("Unlock url", url)
                    super.shouldInterceptRequest(view, url)
                } else {
                    Log.d("Blocked url", url)
                    return getTextWebResource(textStream)
                }


            }


//                return if (
//                    url.contains(param1)
//                    || url.contains(param3) || url.contains(
//                        param6
//                    ) || url.contains(
//                        param3
//                    ) || url.contains(param11) || url.contains(param14) || url.contains(param15) || url.contains(
//                        param16
//                    )
//                ) {
//                    Log.d("Unlock url", url)
//                    super.shouldInterceptRequest(view, url)
//                } else {
//                    Log.d("Blocked url", url)
//                    val textStream = ByteArrayInputStream("".toByteArray())
//                    getTextWebResource(textStream)
//
//                }


//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                return if (url != null) {
//                    if (url.contains("https://phimmoiyyy.net/") || url.contains("https://www.youtube.com/")) {
//                        super.shouldOverrideUrlLoading(view, url)
//                    } else {
//                        true
//                    }
//                } else {
//                    true
//                }
//
//            }
        }

        // Khởi tạo cài đặt cho WebView
        val webSettings: WebSettings = webView.settings


        webView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY;
        webSettings.setEnableSmoothTransition(true);
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        // Cho phép JavaScript chạy trên trang web
        webSettings.javaScriptEnabled = true

        // Cho phép lưu trữ localStorage
        webSettings.domStorageEnabled = true

        // Cho phép chạy bên ngoài WebView, ví dụ: gọi từ JavaScript
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        // Cho phép phóng to và thu nhỏ trang web
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = true

        // Thiết lập WebView nhận các sự kiện touch
//        webView.requestFocusFromTouch()

        // Load URL vào WebView
        webView.loadUrl(url)
    }


    private fun getTextWebResource(data: InputStream): WebResourceResponse? {
        return WebResourceResponse("text/plain", "UTF-8", data)
    }


}