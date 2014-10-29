package com.thoughtworks.dolphin.util;


import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.common.IndexConfig;
import com.thoughtworks.dolphin.common.IndexTarget;
import com.thoughtworks.dolphin.common.SearchResult;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class IndexUtilTest {

    private IndexUtil indexUtil;

    private IndexConfig<TestObject> indexConfig;

    private List<TestObject> testDatas;

    @Before
    public void setUp() throws Exception {
        this.indexConfig = prepareIndexConfig();
        this.indexUtil = IndexUtil.getInstance();
        this.testDatas = prepareDatas();
    }

    @BeforeClass
    public static void setUpForClass() {
        cleanIndexDir();
    }

    @Test
    public void shouldCreateIndex() throws Exception {
        try {
            this.indexUtil.createIndex(this.indexConfig, this.testDatas);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void shouldSearchKeywordNoSizeLimit() throws Exception {
        try {
            SearchResult<TestObject> result = this.indexUtil.search(new String[] {"name"}, "Cathy", this.indexConfig);
            assertEquals(3, result.getTotalCount());
            assertEquals(3, result.getResultData().size());

            result = this.indexUtil.search(new String[] {"name"}, "John", this.indexConfig);
            assertEquals(1, result.getTotalCount());
            assertEquals(2, result.getResultData().get(0).getId());
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "======for dir:" + indexConfig.getDirectory());
        }
    }

    @Test
    public void shouldSearchKeywordWithSizeLimit() throws Exception {
        SearchResult<TestObject> result = this.indexUtil.search(new String[] {"name"}, "Cathy", this.indexConfig, 1, 2);
        assertEquals(1, result.getResultData().size());
    }

    @Test
    public void shouldUpdateIndex() throws Exception {
        TestObject testObject = this.testDatas.get(0);
        testObject.setContent("My Name is John.");
        this.testDatas.set(0, testObject);
        this.indexUtil.updateIndex(this.indexConfig, String.valueOf(testObject.getId()), testDatas);

        SearchResult<TestObject> result = this.indexUtil.search(new String[] {"name"}, "John", this.indexConfig);
        assertEquals(2, result.getTotalCount());
    }

    private IndexConfig<TestObject> prepareIndexConfig() throws IOException {
        IndexConfig<TestObject> config = IndexConfig.newConfig(TestObject.class);
        config.setKeyField("id");
        config.setDocTranslator(new Function<TestObject, Document>() {
            public Document apply(TestObject obj) {
                Document document = new Document();
                document.add(new IntField("id", obj.getId(), Field.Store.YES));
                document.add(new TextField("name", obj.getContent(), Field.Store.YES));
                return document;
            }
        });
        config.setTargetTranslator(new Function<Document, TestObject>() {
            public TestObject apply(Document document) {
                return new TestObject(Integer.parseInt(document.get("id")), document.get("name"));
            }
        });
        return config;
    }

    private class TestObject extends IndexTarget<TestObject> {

        private int id;
        private String content;

        private TestObject(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        @Override
        public int compare(TestObject o) {
            return 0;
        }
    }

    private List<TestObject> prepareDatas() {
        List<TestObject> datas = Lists.newArrayList();
        datas.add(new TestObject(1, "I am Cathy."));
        datas.add(new TestObject(2, "Who is John?"));
        datas.add(new TestObject(3, "Cathy"));
        datas.add(new TestObject(4, "ABC"));
        datas.add(new TestObject(5, "Cathy in Tencent."));
        return datas;
    }

    private static void cleanIndexDir() {
        String path = Constants.INDEX_DIRECTORY + File.separator + TestObject.class.getSimpleName();
        File dir = new File(path);
        if (dir.exists()) {
            dir.delete();
        }
    }
}
