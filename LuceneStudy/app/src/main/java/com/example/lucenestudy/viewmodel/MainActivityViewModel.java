package com.example.lucenestudy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lucenestudy.model.Jogo;
import com.example.lucenestudy.repository.JogoRepository;
import com.example.lucenestudy.repository.Indexer;
import com.example.lucenestudy.repository.Searcher;

import org.lukhnos.portmobile.file.Path;

import java.io.InputStream;
import java.util.List;

public class MainActivityViewModel  extends ViewModel {

    private MutableLiveData<List<Jogo>> mListDocuments;
    private JogoRepository repository;
    private MutableLiveData<List<Jogo>> mAllDocs;



    public LiveData<List<Jogo>> getMListDocuments(){
        return mListDocuments;
    }

    public LiveData<List<Jogo>> getAllDocs(){
        return mAllDocs;
    }


    //acquiring
    public void getDataset(InputStream inputStream){
        repository= JogoRepository.getInstance();
        mListDocuments = repository.getDataset(inputStream);
        mAllDocs= mListDocuments;
    }

    //indexing
    public void indexDocs(Path path, List<Jogo> list){
        Indexer indexer= new Indexer(path);
        indexer.addDocuments(list);
    }

    //searching
    public void searchDocs(Path path, String search, int maxResults){
        Searcher searcher= new Searcher(path);
        mListDocuments= searcher.searchDocs(search, maxResults);
    }
}
