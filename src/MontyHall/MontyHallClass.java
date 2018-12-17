package MontyHall;
import MontyHall.Game;

import java.util.Random;
import java.util.Scanner;


/**
 * Luokka joka huolhetii game luokan kutsumisen ja sille parametrien lähettämisen. 
 * JavaFx versiossa ei niin tärkeä 
 * @author Wiljami
 * @version 17.12.2018
 *
 */
public class MontyHallClass {
    int playerQuess = 0;
    Game game = new Game();

/**
 * Otetaan arvaus talteen
 * @param quess käyttäjän numero
 */ 
    public void makeGuess(int quess) {
        playerQuess = quess;
        
    }

    /**
     * Paljastetaan mikä ovi oli väärä
     * @return paljastettu ovi
     */
    public int RevealDoor() {
        game.Quess(this.playerQuess);
        return game.Reveal();
        
    }
    
    /**
     * Vaihdetaan ovea 
     * @return uusi valittu ovi
     */
    public int switchDoor(){
        int newQuess = game.SwitchDoors(playerQuess);
        this.playerQuess = newQuess;
        return this.playerQuess;
    }
    
    /**
     * lopetetaan peli, tarkistetaan voittiko pelaaja
     * @return voittiko vai häviskö pelaaja
     */
    public boolean endGame(){
        return game.endGame(this.playerQuess);
    }
    
    
    /**
     * Palautetaan oikea valittu ovi
     * @return oikea ovi
     */
    public int getRight(){
        return game.getRight();
    }
    
    /**
     * Tyhjennetään peli ja luodaan uudestaan
     */
    public void cleanAll(){
        Game newGame = new Game();
        game = newGame;
        
    }
    
    /**
     * Pelin smulointi tapahtuu täällä. 
     * @param makeSwitch tehdäänkö vahto
     * @return voitettiinko vai hävittiinkö peli
     */
    public boolean simulate(boolean makeSwitch){
        Random rand = new Random();
        this.playerQuess = rand.nextInt(3) + 0;
        game.Quess(this.playerQuess);
        game.Reveal();
        if(makeSwitch == true){
            int newQuess = game.SwitchDoors(playerQuess);
            this.playerQuess = newQuess;
            
        }
        
        
        return game.endGame(this.playerQuess);
        
        
    }
    
    

/**
 * Voidaan testata peliä komentoriviltä palautaa true jos voitetiin
 * @param args //
 */
public static void main(String [] args){
    
    Game olio = new Game();
    int quess=ReadInput();
    //Player p1 = new Player(quess);
    olio.Quess(quess);
    System.out.println("Your quess is "+quess+" And door where is no prize is "+olio.Reveal());
    boolean YN=Scan();
    if(YN==true){
        int door=olio.SwitchDoors(quess);
        System.out.println("your new door is "+door);
        System.out.println(olio.endGame(door));
        return;
    } 
    
    System.out.println(olio.endGame(quess));
    return;
    
    
}
    



/**
 * teksti version koodi
 * Luetaan haluaako käyttäjä vaihtaa oveaan.
 * @return palautetaan boolean
 */
public static boolean Scan(){
    Scanner scan = new Scanner(System.in);
    System.out.println("Do you want to switch door? [y/n] ");
    char input = scan.next().charAt(0);
    switch(input){
    case 'y': System.out.println("y");
    return true;
    case 'n': System.out.println("n");
    return false;
    default:
        System.out.println("plz isnert y or n");
        Scan();
        break;
}
  
    return false;
}
    



/**
 * Luetaan käyttäjän syöte minkä oven valitsee
 * @return pelaajan arvaus
 */

public static int ReadInput(){
    
    Scanner reader = new Scanner(System.in);
    System.out.println("select door, your options are ");
    System.out.println("0, 1, 2");
    int n = reader.nextInt();
    
    if (n > 2) {
        System.out.println("Wrong number");
        ReadInput();

    }
    if (n < 0) {
        System.out.println("No negatives plza");
        ReadInput();

       
    }

            
    return n;

     }
}

