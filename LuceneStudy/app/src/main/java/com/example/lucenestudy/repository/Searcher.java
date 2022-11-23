package com.example.lucenestudy.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.lucenestudy.model.Jogo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.lukhnos.portmobile.file.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private IndexReader indexReader;
    private Analyzer analyzer;


//class responsible for searching docs from index
    public Searcher(Path path){
        try {
            Directory directory= FSDirectory.open(path);
            indexReader= DirectoryReader.open(directory);
            analyzer= Indexer.getAnalyzer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



// It´s being used a MuliTfieldQueryParser as parser
    public MutableLiveData<List<Jogo>> searchDocs(String search, int maxResults){
        List<Jogo> list= new ArrayList<>();
        MutableLiveData<List<Jogo>> mList= new MutableLiveData<>();
        try {
            IndexSearcher indexSearcher= new IndexSearcher(indexReader);
            String fields[]= {Indexer.TIMES_FIELD, Indexer.DATA_FIELD, Indexer.MANDANTE_PLACAR_FIELD, Indexer.VISITANTE_PLACAR_FIELD};
            QueryParser queryParser= new MultiFieldQueryParser(fields, analyzer);
            queryParser.setDefaultOperator(QueryParser.Operator.AND);
            Query query= queryParser.parse(search);
            TopDocs topDocs= indexSearcher.search(query, maxResults);
            Jogo myDoc;

            if(topDocs.scoreDocs.length == 0){
                queryParser.setDefaultOperator(QueryParser.Operator.OR);
                query= queryParser.parse(search);
                topDocs= indexSearcher.search(query, maxResults);
            }

            for(ScoreDoc scoreDoc: topDocs.scoreDocs){

                int docId= scoreDoc.doc;
                Document luceneDoc= indexSearcher.doc(docId);

                myDoc= new Jogo();
                myDoc.setId(Integer.parseInt(luceneDoc.get(Indexer.ID_FIELD)));
                myDoc.setRodada(Integer.parseInt(luceneDoc.get(Indexer.RODADA_FIELD)));
                myDoc.setData(luceneDoc.get(Indexer.DATA_FIELD));
                myDoc.setDia(luceneDoc.get(Indexer.DIA_FIELD));
                myDoc.setHora(luceneDoc.get(Indexer.HORA_FIELD));
                myDoc.setMandante(luceneDoc.get(Indexer.MANDANTE_FIELD));
                myDoc.setVisitante(luceneDoc.get(Indexer.VISITANTE_FIELD));
                myDoc.setMandante_placar(luceneDoc.get(Indexer.MANDANTE_PLACAR_FIELD));
                myDoc.setVisitante_placar(luceneDoc.get(Indexer.VISITANTE_PLACAR_FIELD));

                list.add(myDoc);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mList.setValue(list);
        return mList;
    }
}
