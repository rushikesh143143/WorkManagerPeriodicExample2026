package com.example.quotesapp2026.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp2026.R
import com.example.quotesapp2026.model.QuoteListModel
import com.example.quotesapp2026.model.QuoteListModelItem

class QuotesAdapter: RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {

    private var quotes: List<QuoteListModelItem> = emptyList()

    fun setQuotes(data: List<QuoteListModelItem>) {
        quotes = data
        notifyDataSetChanged()
    }

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteText: TextView = itemView.findViewById(R.id.txtQuote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote, parent, false)

        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {

        val quote = quotes[position]

        holder.quoteText.text = quote.q
    }


    override fun getItemCount(): Int {
        return quotes.size
    }
}