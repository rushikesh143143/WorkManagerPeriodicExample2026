package com.example.quotesapp2026.mapper

import com.example.quotesapp2026.database.QuotesTable
import com.example.quotesapp2026.model.QuoteListModelItem

fun QuoteListModelItem.toQuotesTable(): QuotesTable {
    return QuotesTable(
        id = 0,
        q = q,
        a = a,
        c = c,
        h = h
    )
}

fun QuotesTable.toQuoteListModelItem(): QuoteListModelItem {
    return QuoteListModelItem(
        q = q,
        a = a,
        c = c,
        h = h
    )
}
