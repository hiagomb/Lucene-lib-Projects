package com.example.lucenestudy.repository;

import com.example.lucenestudy.model.Jogo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.pt.PortugueseAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.lukhnos.portmobile.file.Path;

import java.io.IOException;
import java.util.List;

//class responsible for indexing documents
public class Indexer {

    public static final String ID_FIELD = "id";
    public static final String RODADA_FIELD= "rodada";
    public static final String DATA_FIELD= "data";
    public static final String HORA_FIELD= "hora";
    public static final String DIA_FIELD= "dia";
    public static final String MANDANTE_FIELD= "mandante";
    public static final String VISITANTE_FIELD= "visitante";
    public static final String FORMACAO_MANDANTE_FIELD= "formacao_mandante";
    public static final String FORMACAO_VISITANTE_FIELD= "formacao_visitante";
    public static final String TECNICO_MANDANTE_FIELD= "tecnico_mandante";
    public static final String TECNICO_VISITANTE_FIELD= "tecnico_visitante";
    public static final String VENCEDOR_FIELD= "vencedor";
    public static final String ARENA_FIELD= "arena";
    public static final String MANDANTE_PLACAR_FIELD= "mandante_placar";
    public static final String VISITANTE_PLACAR_FIELD= "visitante_placar";
    public static final String ESTADO_MANDANTE_FIELD= "estado_mandante";
    public static final String ESTADO_VISITANTE_FIELD= "estado_visitante";
    public static final String ESTADO_VENCEDOR_FIELD= "estado_vencedor";
    public static final String TIMES_FIELD= "times";


    private IndexWriter indexWriter;
    private Analyzer analyzer;

    public Indexer(Path path){
        try {
            analyzer= getAnalyzer();
            Directory directory= FSDirectory.open(path);
            IndexWriterConfig iwc= new IndexWriterConfig(analyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            indexWriter= new IndexWriter(directory, iwc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDocuments(List<Jogo> list){

        Field idField= new IntField(ID_FIELD, 0, Field.Store.YES);
        Field rodadaField= new IntField(RODADA_FIELD, 0, Field.Store.YES);
        Field dataField= new TextField(DATA_FIELD, "", Field.Store.YES);
        Field horaField= new TextField(HORA_FIELD, "", Field.Store.YES);
        Field diaField= new TextField(DIA_FIELD, "", Field.Store.YES);
        Field mandanteField= new TextField(MANDANTE_FIELD, "", Field.Store.YES);
        Field visitanteField= new TextField(VISITANTE_FIELD, "", Field.Store.YES);
        Field formacao_mandanteField= new TextField(FORMACAO_MANDANTE_FIELD, "", Field.Store.YES);
        Field formacao_visitanteField= new TextField(FORMACAO_VISITANTE_FIELD, "", Field.Store.YES);
        Field tecnico_mandanteField= new TextField(TECNICO_MANDANTE_FIELD, "", Field.Store.YES);
        Field tecnico_visitanteField= new TextField(TECNICO_VISITANTE_FIELD, "", Field.Store.YES);
        Field vencedorField= new TextField(VENCEDOR_FIELD, "", Field.Store.YES);
        Field arenaField= new TextField(ARENA_FIELD, "", Field.Store.YES);
        Field mandante_placarField= new TextField(MANDANTE_PLACAR_FIELD, "", Field.Store.YES);
        Field visitante_placarField= new TextField(VISITANTE_PLACAR_FIELD, "", Field.Store.YES);
        Field estado_mandanteField= new TextField(ESTADO_MANDANTE_FIELD, "", Field.Store.YES);
        Field estado_visitanteField= new TextField(ESTADO_VISITANTE_FIELD, "", Field.Store.YES);
        Field estado_vencedorField= new TextField(ESTADO_VENCEDOR_FIELD, "", Field.Store.YES);
        Field times_field= new TextField(TIMES_FIELD, "", Field.Store.YES);


        try{
            org.apache.lucene.document.Document document= new org.apache.lucene.document.Document();
            //reusing the same document object
            for(Jogo j: list){
                document.removeField(ID_FIELD);
                document.removeField(RODADA_FIELD);
                document.removeField(DATA_FIELD);
                document.removeField(HORA_FIELD);
                document.removeField(DIA_FIELD);
                document.removeField(MANDANTE_FIELD);
                document.removeField(VISITANTE_FIELD);
                document.removeField(FORMACAO_MANDANTE_FIELD);
                document.removeField(FORMACAO_VISITANTE_FIELD);
                document.removeField(TECNICO_MANDANTE_FIELD);
                document.removeField(TECNICO_VISITANTE_FIELD);
                document.removeField(VENCEDOR_FIELD);
                document.removeField(ARENA_FIELD);
                document.removeField(MANDANTE_PLACAR_FIELD);
                document.removeField(VISITANTE_PLACAR_FIELD);
                document.removeField(ESTADO_MANDANTE_FIELD);
                document.removeField(ESTADO_VISITANTE_FIELD);
                document.removeField(ESTADO_VENCEDOR_FIELD);
                document.removeField(TIMES_FIELD);

                idField.setIntValue(j.getId());
                rodadaField.setIntValue(j.getRodada());
                dataField.setStringValue(j.getData());
                horaField.setStringValue(j.getHora());
                diaField.setStringValue(j.getDia());
                mandanteField.setStringValue(j.getMandante());
                visitanteField.setStringValue(j.getVisitante());
                formacao_mandanteField.setStringValue(j.getFormacao_mandante());
                formacao_visitanteField.setStringValue(j.getFormacao_visitante());
                tecnico_mandanteField.setStringValue(j.getTecnico_mandante());
                tecnico_visitanteField.setStringValue(j.getTecnico_visitante());
                vencedorField.setStringValue(j.getVencedor());
                arenaField.setStringValue(j.getArena());
                mandante_placarField.setStringValue(j.getMandante_placar());
                visitante_placarField.setStringValue(j.getVisitante_placar());
                estado_mandanteField.setStringValue(j.getMandante_estado());
                estado_visitanteField.setStringValue(j.getVisitante_estado());
                estado_vencedorField.setStringValue(j.getEstado_vencedor());
                times_field.setStringValue(j.getParticipantes());

                document.add(idField);
                document.add(rodadaField);
                document.add(dataField);
                document.add(horaField);
                document.add(diaField);
                document.add(mandanteField);
                document.add(visitanteField);
                document.add(formacao_mandanteField);
                document.add(formacao_visitanteField);
                document.add(tecnico_mandanteField);
                document.add(tecnico_visitanteField);
                document.add(vencedorField);
                document.add(arenaField);
                document.add(mandante_placarField);
                document.add(visitante_placarField);
                document.add(estado_mandanteField);
                document.add(estado_visitanteField);
                document.add(estado_vencedorField);
                document.add(times_field);

                indexWriter.addDocument(document);
            }
            indexWriter.commit();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static Analyzer getAnalyzer(){
        return new PortugueseAnalyzer();
    }


}
