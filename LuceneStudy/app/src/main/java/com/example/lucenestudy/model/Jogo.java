package com.example.lucenestudy.model;

public class Jogo {

    //The atributtes are the same ones from the dataset

    private int id;
    private int rodada;
    private String data;
    private String hora;
    private String dia;
    private String mandante;
    private String visitante;
    private String formacao_mandante;
    private String formacao_visitante;
    private String tecnico_mandante;
    private String tecnico_visitante;
    private String vencedor;
    private String arena;
    private String mandante_placar;
    private String visitante_placar;
    private String mandante_estado;
    private String visitante_estado;
    private String estado_vencedor;
    private String participantes;


    public Jogo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMandante() {
        return mandante;
    }

    public void setMandante(String mandante) {
        this.mandante = mandante;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getFormacao_mandante() {
        return formacao_mandante;
    }

    public void setFormacao_mandante(String formacao_mandante) {
        this.formacao_mandante = formacao_mandante;
    }

    public String getFormacao_visitante() {
        return formacao_visitante;
    }

    public void setFormacao_visitante(String formacao_visitante) {
        this.formacao_visitante = formacao_visitante;
    }

    public String getTecnico_mandante() {
        return tecnico_mandante;
    }

    public void setTecnico_mandante(String tecnico_mandante) {
        this.tecnico_mandante = tecnico_mandante;
    }

    public String getTecnico_visitante() {
        return tecnico_visitante;
    }

    public void setTecnico_visitante(String tecnico_visitante) {
        this.tecnico_visitante = tecnico_visitante;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getMandante_placar() {
        return mandante_placar;
    }

    public void setMandante_placar(String mandante_placar) {
        this.mandante_placar = mandante_placar;
    }

    public String getVisitante_placar() {
        return visitante_placar;
    }

    public void setVisitante_placar(String visitante_placar) {
        this.visitante_placar = visitante_placar;
    }

    public String getMandante_estado() {
        return mandante_estado;
    }

    public void setMandante_estado(String mandante_estado) {
        this.mandante_estado = mandante_estado;
    }

    public String getVisitante_estado() {
        return visitante_estado;
    }

    public void setVisitante_estado(String visitante_estado) {
        this.visitante_estado = visitante_estado;
    }

    public String getEstado_vencedor() {
        return estado_vencedor;
    }

    public void setEstado_vencedor(String estado_vencedor) {
        this.estado_vencedor = estado_vencedor;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }
}
