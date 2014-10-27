package com.thoughtworks.dolphin.common;


import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.thoughtworks.dolphin.model.Book;
import com.thoughtworks.dolphin.service.TestObject;
import com.thoughtworks.dolphin.util.CacheUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CacheUtil.class})
public class BookIndexConfigTest {

    private IndexConfig<Book> bookIndexConfig;
    private Book book;


    @Before
    public void setUp() throws IOException {
        bookIndexConfig = BookIndexConfig.getBookIndexConfig();
        book = TestObject.prepareOneBook(1, "author", "name", "123", "publisher", "intro");
    }

    @Test
    public void shouldConvertBookToDocument() {

        Function<Book, Document> targetTranslateFunction = bookIndexConfig.getDocumentTranslateFunction();
        Document doc = targetTranslateFunction.apply(book);

        assertThat(bookIndexConfig.getKeyField(), is("id"));
        assertThat(doc.get("id"), is("1"));
        assertThat(doc.get("name"), is("name"));
        assertThat(doc.get("author"), is("author"));
        assertThat(doc.get("isbn"), is("123"));
        assertThat(doc.get("publisher"), is("publisher"));
    }

    @Test
    public void shouldConvertDocumentToBook() throws Exception {
        Document document = createDocument();
        CacheUtil instance = mockCacheUtil();
        Whitebox.setInternalState(BookIndexConfig.class, "cacheUtil", instance);

        Function<Document, Book> targetTranslateFunction = bookIndexConfig.getTargetTranslateFunction();
        Book cachedBook = targetTranslateFunction.apply(document);

        assertNotNull(cachedBook);
        assertThat(cachedBook.getId(), is(1));
        assertThat(cachedBook.getName(), is("name"));
        assertThat(cachedBook.getAuthor(), is("author"));
        assertThat(cachedBook.getIsbn(), is("123"));
        assertThat(cachedBook.getPublisher(), is("publisher"));
    }

    private Document createDocument() {
        Document document = new Document();
        document.add(new IntField("id", 1, Field.Store.YES));
        document.add(new TextField("name", "name", Field.Store.YES));
        document.add(new TextField("author", "author", Field.Store.YES));
        document.add(new TextField("isbn", "123", Field.Store.YES));
        document.add(new TextField("publisher", "publisher", Field.Store.YES));
        return document;
    }

    private CacheUtil mockCacheUtil() {
        mockStatic(CacheUtil.class);
        CacheUtil instance = mock(CacheUtil.class);
        when(CacheUtil.getInstance()).thenReturn(instance);
        Map<Integer, Book> cache = Maps.newHashMap();
        cache.put(1, book);
        when(instance.get("books")).thenReturn(cache);
        return instance;
    }
}
