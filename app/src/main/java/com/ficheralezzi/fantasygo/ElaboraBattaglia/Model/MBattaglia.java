package com.ficheralezzi.fantasygo.ElaboraBattaglia.Model;

import android.util.Log;

public class MBattaglia {

    private static final String TAG = "MBattaglia";
    private Risultato risultato = null;
    private MCombattente combattenteA = null;
    private MCombattente combattenteB = null;
    private static MBattaglia singletoneinstance = null;
    private int turno = 0;

    public MBattaglia() {
    }

    public void init(MCombattente combattenteA,MCombattente combattenteB){

        if(this.combattenteA == null & this.combattenteB == null & this.risultato == null) {
            this.combattenteA = combattenteA;
            this.combattenteB = combattenteB;
            this.risultato = new Risultato();
        }
    }

    public static MBattaglia getSingletoneInstance() {

        if(singletoneinstance == null){
            Log.d("battaglia", "no");
            singletoneinstance = new MBattaglia();
        } else Log.d("battaglia", "si");


        return singletoneinstance;
    }

     public MCombattente getCombattenteA() {

        return combattenteA;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public void setCombattenteA(MCombattente combattenteA) {
        this.combattenteA = combattenteA;
    }

    public MCombattente getCombattenteB() {
        return combattenteB;
    }

    public void setCombattenteB(MCombattente combattenteB) {
        this.combattenteB = combattenteB;
    }

    public MCombattente getCombattenteById(int id){
        if (combattenteA.getId() == id) return combattenteA;
        else return combattenteB;
    }

    public Risultato getRisultato() {
        return risultato;
    }

    public void elaboraBattaglia(){

        while (combattenteA.getCaratteristiche().getPuntiFerita() > 0 && combattenteB.getCaratteristiche().getPuntiFerita() > 0){
            if ((combattenteA.getCaratteristiche().getVelocitàdAttacco() >= 0  & turno%2 == 0 )||
                    (combattenteA.getCaratteristiche().getVelocitàdAttacco()< combattenteB.getCaratteristiche().getVelocitàdAttacco()
                    & turno%2 != 0)) {
                Log.d(TAG, "Turno di A");
                combattenteA.eseguiAzione();

            }else {
                Log.d(TAG, "Turno di B");
                combattenteB.eseguiAzione();

            }
            Log.d(TAG, "Turno " + turno + " finito");
            Log.d(TAG, this.toString());
            turno++;
        }

        if(combattenteA.getCaratteristiche().getPuntiFerita() > combattenteB.getCaratteristiche().getPuntiFerita()){

            risultato.setRisultato(true);
        }

        risultato.setPuntiferitaA(combattenteA.getCaratteristiche().getPuntiFerita());
        risultato.setPuntiferitaB(combattenteB.getCaratteristiche().getPuntiFerita());
        risultato.setNumeroturni(this.turno);
    }

    @Override
    public String toString() {
        return "MBattaglia{" +
                "combattenteA=" + combattenteA.toString() +
                ", combattenteB=" + combattenteB.toString() +
                ", turno=" + turno +
                '}';
    }
}

class Risultato {
    private  boolean risultato = false;
    private  int puntiFeritaA = 0;
    private  int puntiFeritaB = 0;
    private  int numeroturni = 0;

    public Risultato (){}

    public void setRisultato(boolean risultato) {
        this.risultato = risultato;
    }

    public boolean isRisultato() {
        return risultato;
    }

    public int getNumeroturni() {
        return numeroturni;
    }

    public int getPuntiFeritaB() {
        return puntiFeritaB;
    }

    public int getPuntiferitaA() {
        return puntiFeritaA;
    }

    public void setPuntiferitaA(int puntiferitaA) {
        this.puntiFeritaA = puntiferitaA;
    }

    public void setPuntiferitaB(int puntiferitaB) {
        puntiFeritaB = puntiferitaB;
    }

    public void setNumeroturni(int numeroturni) {
        this.numeroturni = numeroturni;
    }

    @Override
    public String toString() {
        return "Risultato{" +
                "risultato=" + risultato +
                ", puntiFeritaA=" + puntiFeritaA +
                ", puntiFeritaB=" + puntiFeritaB +
                ", numeroturni=" + numeroturni +
                '}';
    }
}