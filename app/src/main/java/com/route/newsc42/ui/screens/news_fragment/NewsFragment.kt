package com.route.newsc42.ui.screens.news_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.route.newsc42.R
import com.route.newsc42.api.model.ArticleDM
import com.route.newsc42.api.model.SourceDM
import com.route.newsc42.databinding.FragmentNewsBinding
import kotlin.collections.forEach

///View -> VM(State Holder) - > Vm
class NewsFragment(val categoryId: String) : Fragment() {
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentNewsBinding
    val articleAdapter = ArticlesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.errorView.categoryId = categoryId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.loadSources(categoryId)
        setUpArticlesRecyclerView()
    }

    private fun setUpObservers() {
//        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
//            if (isLoading) showLoading()
//            else hideLoading()
//        }
//        viewModel.sourcesErrorMessage.observe(viewLifecycleOwner) {
//            if (it.isNullOrEmpty()) {
//                hideError()
//            } else {
//                showError(it) {
//                    viewModel.loadSources(categoryId)
//                }
//            }
//        }
        viewModel.articlesErrorMessage.observe(viewLifecycleOwner){pair->
            if (pair.second.isNullOrEmpty()) {
                hideError()
            } else {
                showError(pair.second) {
                    viewModel.loadArticles(pair.first)
                }
            }
        }
        viewModel.sources.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            showTabLayout(it)
        }
        viewModel.articles.observe(viewLifecycleOwner){
            showArticlesList(it)
        }
    }

    private fun setUpArticlesRecyclerView() {
        binding.articlesRecycler.adapter = articleAdapter
    }

    private fun showTabLayout(sources: List<SourceDM>) {
        binding.tabLayout.isVisible = true
        sources.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source.name
            tab.tag = source.id
            binding.tabLayout.addTab(tab)
        }
        viewModel.loadArticles(sources[0].id ?: "")
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.loadArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
    }


    private fun showArticlesList(articles: List<ArticleDM>) {
        articleAdapter.submitList(articles)
    }


    fun showError(errorMessages: String, onRetryClick: () -> Unit) {
        binding.errorView.isVisible = true
//        binding.errorView
//        binding.errorView.retryButton.setOnClickListener {
//            onRetryClick()
//        }
    }

    fun hideError() {
       // binding.errorView.root.isVisible = false
    }

}
