package com.example.quotesapp2026.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp2026.R
import com.example.quotesapp2026.model.QuoteListModelItem

class QuotesAdapt : ListAdapter<QuoteListModelItem, QuotesAdapt.QuotesViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote, parent, false)
        return QuotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quote = getItem(position)
        holder.bind(quote)
    }

    class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val quoteText: TextView = itemView.findViewById(R.id.txtQuote)

        fun bind(quote: QuoteListModelItem) {
            quoteText.text = quote.q
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<QuoteListModelItem>() {
        override fun areItemsTheSame(oldItem: QuoteListModelItem, newItem: QuoteListModelItem): Boolean {
            // Use a unique identifier if available. Using quote text is OK for now.
            return oldItem.q == newItem.q
        }

        override fun areContentsTheSame(oldItem: QuoteListModelItem, newItem: QuoteListModelItem): Boolean {
            return oldItem == newItem
        }
    }
}