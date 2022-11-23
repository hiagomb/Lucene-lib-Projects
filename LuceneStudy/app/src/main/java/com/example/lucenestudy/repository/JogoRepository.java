package com.example.lucenestudy.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.lucenestudy.model.Jogo;
import com.example.lucenestudy.view.MainActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//There are 7645 games in the dataset. Each game has the following attributes:
/*        ID - ID da partida
        Rodada : Rodada que aconteceu a partida
        Data : Data que ocorreu a partida
        Horário :  Horário que ocorreu a partida
        Dia : Dia da semana que ocorreu a partida
        Mandante : Clube mandante
        Visitante : Clube Visitante
        formacao_mandante: Formação do mandante
        formacao_visitante: Formação do visitante
        tecnico_mandante: Técnico do mandante
        tecnico_visitante: Técnico do visitante
        Vencedor : Clube vencedor da partida. Quando tiver "-", é um empate
        Arena : Arena que ocorreu a partida
        Mandante Placar : Gols que o clube mandante fez na partida
        Visitante Placar : Gols que o clube visitante fez na partida
        Estado Mandante : Estado do clube mandatorio
        Estado Visitante : Estado do clube visitante
        Estado Vencedor : Estado do clube vencedor. Quando tiver "-", é um empate*/


//class responsible for acquiring the dataset into a list of objects
public class JogoRepository {

    private static JogoRepository jogoRepository;

    private JogoRepository(){

    }

    public static JogoRepository getInstance(){
        if(jogoRepository == null){
            jogoRepository = new JogoRepository();
        }
        return jogoRepository;
    }


    public MutableLiveData<List<Jogo>> getDataset(InputStream inputStream){
        List<Jogo> list= new ArrayList<>();
        try {
            Jogo jogo;
            BufferedReader br= new BufferedReader(new InputStreamReader(inputStream));
            String line= "";
            int aux=0;
            while((line = br.readLine())!= null){
                if(aux!=0){ //not including label line
                    String[] array= line.split("(?=,)");
                    jogo = new Jogo();
                    jogo.setId(Integer.parseInt(array[0].replaceAll(",", "")));
                    jogo.setRodada(Integer.parseInt(array[1].replaceAll(",", "")));
                    jogo.setData(array[2].replaceAll(",", ""));
                    jogo.setHora(array[3].replaceAll(",", ""));
                    jogo.setDia(array[4].replaceAll(",", ""));
                    jogo.setMandante(array[5].replaceAll(",", ""));
                    jogo.setVisitante(array[6].replaceAll(",", ""));
                    jogo.setFormacao_mandante(array[7].replaceAll(",", ""));
                    jogo.setFormacao_visitante(array[8].replaceAll(",", ""));
                    jogo.setTecnico_mandante(array[9].replaceAll(",", ""));
                    jogo.setTecnico_visitante(array[10].replaceAll(",", ""));
                    jogo.setVencedor(array[11].replaceAll(",", ""));
                    jogo.setArena(array[12].replaceAll(",", ""));
                    jogo.setMandante_placar(array[13].replaceAll(",", ""));
                    jogo.setVisitante_placar(array[14].replaceAll(",", ""));
                    jogo.setMandante_estado(array[15].replaceAll(",", ""));
                    jogo.setVisitante_estado(array[16].replaceAll(",", ""));
                    jogo.setEstado_vencedor(array[17].replaceAll(",", ""));
                    jogo.setParticipantes(array[5]+array[6]+" x, vs, contra, versus");

                    list.add(jogo);
                }
                aux++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MutableLiveData<List<Jogo>> mList= new MutableLiveData<>();
        mList.postValue(list);

        return mList;
    }

}
