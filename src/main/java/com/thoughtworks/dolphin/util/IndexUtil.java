package com.thoughtworks.dolphin.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thoughtworks.dolphin.common.Constants;
import com.thoughtworks.dolphin.common.IndexConfig;
import com.thoughtworks.dolphin.common.IndexTarget;
import com.thoughtworks.dolphin.common.SearchResult;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.sandbox.queries.DuplicateFilter;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.util.List;


public class IndexUtil {

    private IndexWriter indexWriter;

    private static Logger LOG = Logger.getLogger(IndexUtil.class);

    private IndexUtil() throws IOException {

    }

    public static IndexUtil getInstance() {
        try {
            return new IndexUtil();
        } catch (IOException e) {
            return null;
        }
    }

    public <T extends IndexTarget> void createIndex(IndexConfig indexConfig, List<T> targets) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        indexWriter = new IndexWriter(indexConfig.getDirectory(), config);
        indexWriter.addDocuments(Lists.transform(targets, indexConfig.getDocumentTranslateFunction()));
        indexWriter.close();
    }

    public <T extends IndexTarget> SearchResult<T> search(String[] searchFields, String keyword, IndexConfig<T> indexConfig, int fromIndex, int toIndex) throws IOException, ParseException {
        IndexReader reader = DirectoryReader.open(indexConfig.getDirectory());
        final IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new MultiFieldQueryParser(searchFields, new StandardAnalyzer());
        Query query = parser.parse(keyword);

        TopDocs result = searcher.search(query, Constants.MAX_SEARCH_COUNT);

        List<ScoreDoc> scoreDocs = Lists.newArrayList(result.scoreDocs);
        Function<Document, T> targetTranslator = indexConfig.getTargetTranslateFunction();

        int minIndex = Math.min(scoreDocs.size(), toIndex);

        List<T> targets = Lists.newArrayList();
        for (int i = fromIndex; i < minIndex; i++) {
            ScoreDoc scoreDoc = scoreDocs.get(i);
            Document document = searcher.doc(scoreDoc.doc);

            T target = targetTranslator.apply(document);
            if (target != null) {
                target.setScore(scoreDoc.score);
                targets.add(target);
            }
        }
        LOG.info("Search " + targets.size() + " results.");
        reader.close();
        return new SearchResult<T>(targets, result.totalHits);
    }

    public <T extends IndexTarget> SearchResult<T> search(String[] searchFields, String keyword, IndexConfig<T> indexConfig) throws IOException, ParseException {
        return search(searchFields, keyword, indexConfig, 0, Constants.MAX_SEARCH_COUNT);
    }

    public <T extends IndexTarget> void updateIndex(IndexConfig indexConfig, String toDeleteKey, List<T> target) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        indexWriter = new IndexWriter(indexConfig.getDirectory(), config);
        indexWriter.updateDocuments(new Term(indexConfig.getKeyField(), toDeleteKey), Lists.transform(target, indexConfig.getDocumentTranslateFunction()), new StandardAnalyzer());
        indexWriter.close();
    }
}
