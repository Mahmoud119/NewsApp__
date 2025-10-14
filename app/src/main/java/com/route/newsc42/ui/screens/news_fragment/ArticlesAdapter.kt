package com.route.newsc42.ui.screens.news_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.route.newsc42.R
import com.route.newsc42.data.api.model.ArticleDM
import com.route.newsc42.databinding.ItemArticleBinding

val diffUtilCallback = object: DiffUtil.ItemCallback<ArticleDM>() {
    override fun areItemsTheSame(
        oldItem: ArticleDM,
        newItem: ArticleDM
    ): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(
        oldItem: ArticleDM,
        newItem: ArticleDM
    ): Boolean {
       return oldItem == newItem
    }

}
class ArticlesAdapter(var articles: List<ArticleDM> = emptyList()) :
    ListAdapter<ArticleDM, ArticlesAdapter.ArticleViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val binding: ItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent, false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int
    ) {
        val article = articles[position]
        holder.binding.article = article
    }

    fun submitList(articles: List<ArticleDM>){
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = articles.size


    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)
}