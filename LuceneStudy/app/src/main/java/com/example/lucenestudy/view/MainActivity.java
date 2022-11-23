package com.example.lucenestudy.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucenestudy.R;
import com.example.lucenestudy.adapter.RecyclerAdapter;
import com.example.lucenestudy.helper.LoadingDialog;
import com.example.lucenestudy.model.Jogo;
import com.example.lucenestudy.viewmodel.MainActivityViewModel;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.lukhnos.portmobile.file.Paths;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private MainActivityViewModel mainActivityViewModel;
    private final String INDEX_DIR_NAME= "index";
    private SeekBar seekBar;
    private TextView txt_seek;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar= findViewById(R.id.seekBar);
        txt_seek= findViewById(R.id.txtSeek);
        mainActivityViewModel= new ViewModelProvider(this).get(MainActivityViewModel.class);
        MyAsyncTask myAsyncTask= new MyAsyncTask();
        myAsyncTask.execute();

        int minimum= 10;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_seek.setText(""+(progress + minimum));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Make a search again after adjusting the number of results", Toast.LENGTH_SHORT).show();
            }
        });
    }



    //instantiating an observer for the list
    private void setListObserver(){
        mainActivityViewModel.getMListDocuments().observe(this, new Observer<List<Jogo>>() {
            @Override
            public void onChanged(List<Jogo> jogos) {
                updatingAdapter(mainActivityViewModel.getMListDocuments().getValue());
            }
        });
    }


    File getIndexRootDir() {
        return new File(getCacheDir(), INDEX_DIR_NAME);
    }



    private void initRecycler(){
        recyclerView= findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        updatingAdapter(mainActivityViewModel.getMListDocuments().getValue());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem= menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    updatingAdapter(mainActivityViewModel.getAllDocs().getValue());
                }else{
                    mainActivityViewModel.searchDocs(Paths.get(getIndexRootDir().getAbsolutePath()), newText+"~", Integer.parseInt(txt_seek.getText().toString()));
                    setListObserver();
                }
                return true;
            }
        });
        return true;
    }



    private void updatingAdapter(List<Jogo> list){
        adapter= new RecyclerAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    //calling the dataset reading in a background thread
    class MyAsyncTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            loadingDialog= new LoadingDialog(MainActivity.this);
            loadingDialog.load_alert_dialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream inputStream = getAssets().open("campeonato-brasileiro-full.csv");
                mainActivityViewModel.getDataset(inputStream); //acquire data
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mainActivityViewModel.indexDocs(Paths.get(getIndexRootDir().getAbsolutePath()), mainActivityViewModel.getMListDocuments().getValue()); //index docs
            initRecycler();
            loadingDialog.dismiss_dialog();
        }
    }


}