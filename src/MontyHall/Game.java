package MontyHall;


import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Luokka monthyHall pelille, huolehtii pelin logiikasta esim. ovien valitsemisesta, sek‰ niiden avaamisesta
 * @author Wiljami
 * @version 17.12.2018
 *
 */
public class Game {
    
    private int[] doors={0,1,2};
    private int RightDoor;
    private ArrayList<Integer> doorsAfter;
    private int Reveal;
    //private int playerQuess; t‰t‰ tarvitaan vain teksti versiossa
    
    
    
    public Game(){
        doorsAfter=new ArrayList<Integer>();
        Random rand = new Random();
        int  right = rand.nextInt(3) + 0;
        RightDoor=right;
    }
    
    
    /**
     * 
     * @return Palautetaan lista ovista jotka en‰‰n j‰ljell‰
     */
    public String retList(){
        String str = Arrays.toString(doorsAfter.toArray()); 
        return str;
    }
    
    /**
     * Paljastetaan v‰‰r‰ ovi. Jos doorsAfter size >1 eli pelaaja on arvannut oikean oven alussa, palautetaan jompi kumpi numero listasta ja poistetaan se.
     * Jos doorsAfter size =1 pelaaja arvannut v‰‰rin joten palautetaan vaan arvo listatsa.
     * @return paljastettava ovi
     */
    public int Reveal(){
        int a =doorsAfter.get(new Random().nextInt(doorsAfter.size()));
        if(doorsAfter.size()>1){
            int index=doorsAfter.indexOf(a);
            doorsAfter.remove(index);
        }
        return a;
    }
    
    /**
     * Tarkistetaan oisuiko pelaaja oikein
     * @param quess pelaajan arvaus
     * @return true/false eli voittiko vai ei
     */
    public boolean endGame(int quess){
        if ( this.RightDoor == quess){
            return true;
        }
        return false;
    }
    
    /**
     * palautetaan arvottu oikeaovi
     * @return oikea arvo
     */
    public int getRight(){
        return this.RightDoor;
    }
    
   
    /**
    public String endGame(){
        if(  playerQuess == RightDoor ) {
            return "You won the right door was "+RightDoor+" and your answer was "+playerQuess;
         }
        if( playerQuess!=RightDoor){
            return "You lose the right door was"+RightDoor+"and your answer was "+playerQuess;
        }
        return null;
        
    }
    
    /**
     * Vaihdetaan ovet jos pelaaja niin haluaa. Ehtona jos pelaaja arvannut oikean oven palautetaan ovi listasta
     * koska esim. playerQuess=1, RightDoor=1, doorsAfter{0,2}. Aliohjelma Reveal muutta listan doorsAfter{0 or 2}.Palauttamalla kumpi vaan opikein koska aliohjelma Reveal on poistanut listasta avatun oven
     * Jos arvannut v‰‰rin palautetaan RightDoor, koska esim. playerQuess=1, RightDoor=2 doorsAfter{0}. Ollaan avattu ovi doorsAfter listasta, jotewn ei voida vaihtaa pelaajan 
     * arvausta siihen
     * @param playerQuess pelaajan arvaus
     * @return mihin numeroon pelaaja vaihtoi
     */
    public int SwitchDoors(int playerQuess){
        int returnValue=0;
        
        if(playerQuess==RightDoor){
            //plauata listatsta
            returnValue=doorsAfter.get(0);
            return returnValue;
            
        }
        if(playerQuess!=RightDoor){
            return RightDoor;
        }
        return 0;
    }
    
    
   
    
    
    /**
     * T‰ss‰ katsotaan mit‰ pelaaja on arvannut ja lis‰t‰‰n ovet jotka voidaan paljastaa listaan.
     * T‰ss‰ siis ehto jos pelaaja arvannut saman oven kuin voittava ovi tai arvannut oven joka ei voita.
     * @param quessed pelaajan arvaava numero
     */
    public void Quess(int quessed){
        //playerQuess=quessed;
       // Jos pelaaja arvannut okein siirryt‰‰n t‰h‰n
        if(quessed==RightDoor){
            
            for (int i=0; i<=2;i++){
                
                if(doors[i]!=RightDoor){
                    
                    doorsAfter.add(doors[i]);
                    
                }
                
                
            }  
            return;
        }
        
            //jos pelaaja arvannut v‰‰rin menn‰‰n t‰h‰n
            for (int i=0; i<=2;i++){
                if(doors[i]!=RightDoor && doors[i]!=quessed){
                    doorsAfter.add(doors[i]);
                    
                }
                
            }    
            return;
        
        
    }
    
    
    
    
}