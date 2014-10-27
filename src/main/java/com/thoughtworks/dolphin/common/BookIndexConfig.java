package com.thoughtworks.dolphin.common;

import com.google.common.base.Function;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.util.CacheUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;

import java.io.IOException;
import java.util.Map;

public final class BookIndexConfig {

    private static CacheUtil cacheUtil = CacheUtil.getInstance();


    public static IndexConfig<Book> getBookIndexConfig() throws IOException {
        IndexConfig<Book> bookIndexConfig = IndexConfig.newConfig(Book.class);
        bookIndexConfig.setKeyField("id");
        bookIndexConfig.setDocTranslator(new Function<Book, Document>() {
            public Document apply(Book book) {
                Document doc = new Document();
                doc.add(new IntField("id", book.getId(), Field.Store.YES));
                doc.add(new TextField("name", book.getName(), Field.Store.YES));
                doc.add(new TextField("author", book.getAuthor(), Field.Store.YES));
                doc.add(new TextField("isbn", book.getIsbn(), Field.Store.YES));
                doc.add(new TextField("publisher", book.getPublisher(), Field.Store.YES));
                return doc;
            }
        });
        bookIndexConfig.setTargetTranslator(new Function<Document, Book>() {
            public Book apply(Document doc) {
                return ((Map<Integer, Book>) cacheUtil.get("books")).get(Integer.parseInt(doc.get("id")));
            }
        });

        return bookIndexConfig;
    }
}
