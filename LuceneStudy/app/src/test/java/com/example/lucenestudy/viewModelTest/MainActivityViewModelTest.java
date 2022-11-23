package com.example.lucenestudy.viewModelTest;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;

import com.example.lucenestudy.model.Jogo;
import com.example.lucenestudy.repository.Indexer;
import com.example.lucenestudy.repository.JogoRepository;
import com.example.lucenestudy.viewmodel.MainActivityViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lukhnos.portmobile.file.Path;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class MainActivityViewModelTest {


    @Mock
    JogoRepository repository;


    InputStream inputStream = null;
    List<Jogo> list= null;
    MainActivityViewModel mainActivityViewModel;

    @Before
    public void initMocks(){
        inputStream= new ByteArrayInputStream("test data".getBytes());
        list= new ArrayList<>();  list.add(new Jogo());   list.add(new Jogo());
        MutableLiveData<List<Jogo>> mList= new MutableLiveData<>();
        Mockito.when(repository.getDataset(inputStream)).thenReturn(mList);
        mainActivityViewModel= new MainActivityViewModel();
    }

    @Test
    public void getDatasetTest(){
        mainActivityViewModel.getDataset(inputStream);
        Assert.assertNotNull(mainActivityViewModel.getMListDocuments());
        Assert.assertNotNull(mainActivityViewModel.getAllDocs());
    }


}
