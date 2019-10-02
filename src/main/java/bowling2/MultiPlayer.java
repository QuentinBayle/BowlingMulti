/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling2;
import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;

/**
 *
 * @author florian mouly
 */
public class MultiPlayer implements MultiPlayerGame {

    public SinglePlayerGame[] games;
    public String[] players;
    public int turn;
    public int currentPlayer;

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if (playerName == null || playerName.length == 0){
            throw new UnsupportedOperationException("Erreur liste joueur vide/nulle");
        }
        this.players = playerName;
        this.games = new SinglePlayerGame[playerName.length];
        for (int i=0;i<this.games.length;i++){
            this.games[i] = new SinglePlayerGame();
        }
        this.turn = 0;
        this.currentPlayer = 0;
        return "Prochain tir : joueur "+this.players[0]+", tour n° 1, boule n° 1";
    }
    
    public void nextTurn(){
        if (currentPlayer == this.players.length-1){
                this.currentPlayer = 0;
                this.turn++;
        }else{
                this.currentPlayer++;
        }
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (this.games == null){
            throw new UnsupportedOperationException("Partie non lancée");
        }
        if (this.games[this.games.length-1].isFinished() == false){
            this.games[currentPlayer].lancer(nombreDeQuillesAbattues);
            if (this.games[this.currentPlayer].isFinished() || this.games[currentPlayer].hasCompletedFrame()){
                nextTurn();
            }
            if (this.games[this.games.length-1].isFinished()){
                return "Partie terminée.";
            } else {
                return "Prochain tir : joueur "+this.players[currentPlayer]+", tour n° "+(this.turn+1)+", boule n° "+this.games[currentPlayer].getNextBallNumber();   
            }
        } else {
            return "Partie terminée.";
        }
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        int cpt = -1;
        for (int i=0;i<this.players.length;i++){
            if (this.players[i].equals(playerName)){
               cpt = this.games[i].score();
            }
        }
        if (cpt == -1){
            throw new UnsupportedOperationException("Error, not here");
        }
        return cpt; 
    }
    
}