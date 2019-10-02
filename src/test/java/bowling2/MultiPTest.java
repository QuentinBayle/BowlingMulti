/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling2;
import bowling2.MultiPlayer;
import bowling.SinglePlayerGame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author florian mouly
 */
public class MultiPTest {
    
    MultiPlayer game;
    String[] players;
    
    @Before
    public void setUp() throws Exception {
        players = new String[]{"Toto","Tutu","Truc","Much"};
        game = new MultiPlayer();
        game.startNewGame(players);
    }
    
    
    /**
     * Initialisation liste joueur vide
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testEmptyPlayerList() throws Exception{
        game.startNewGame(new String[]{});
    }
    
    /**
     * Initialisation correct de la partie
     */
    @Test
    public void testInitialize() throws Exception{
        String message = "Prochain tir : joueur Toto, tour n° 1, boule n° 1";
        assertEquals(message,game.startNewGame(players));
    }
    
    /**
     * Boule suivant un strike
     */
    @Test
    public void testNextBowlingBall() throws Exception{
        String message = "Prochain tir : joueur Toto, tour n° 1, boule n° 2";
        assertEquals(message,game.lancer(6));
        
    }
    
    /**
     * Tour suivant
     */
    @Test
    public void testNextTurn() throws Exception{
        String message = "Prochain tir : joueur Tutu, tour n° 1, boule n° 1";
        assertEquals(message,game.lancer(10));
    }
    
    /**
     * Fin correct
    */
    @Test
    public void testgameEnded() throws Exception{
        rollMany(79, 1);
        assertEquals("Partie terminée.",game.lancer(1));
    }
    
    /**
     * Score pour une personnes pas présente dans la partie
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testScoreUnexistingPlayer() throws Exception{
        game.scoreFor("Julien");
    }
    
    /**
     * Score correct
    */
    @Test
    public void testScore() throws Exception{
        assertEquals(0,game.scoreFor("Toto"));
        game.lancer(10);
        assertEquals(10,game.scoreFor("Toto"));
    }
    
    // Quelques methodes utilitaires pour faciliter l'écriture des tests
	private void rollMany(int n, int pins) throws Exception {
		for (int i = 0; i < n; i++) {
			game.lancer(pins);
		}
	}    
}