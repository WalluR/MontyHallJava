package MonthyHallFx;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import MontyHall.MontyHallClass;


/**
 * Controlleri JavaFx:lle, Huolehtii käyttäjän syötteiden lähettämisestä peli luokalle. 
 * Peliä voidaan pelata 1 peli kerrallaan, tai peliä voidaan simuloida n kertaa vaihtamalla,tai vaihtamatta ovea
 * @author Wiljam Rautiainen
 * @version 17.12.2018
 *
 */
public class MonthyHallController implements Initializable {
    
    @FXML private Label systemPrint;
    @FXML private Label wins;
    @FXML private Label lost;
    @FXML private Button Choice1;
    @FXML private Button Choice2;
    @FXML private Button one;
    @FXML private Button two;
    @FXML private Button three;
    @FXML private TextField number;
    @FXML private CheckBox yesSwitch;

    //Tässä @FXML koodit
    
    
    @FXML
    void startSimulation(@SuppressWarnings("unused") ActionEvent event) {
        startSimulation();

    }
    
    
    @FXML
    void Nullify(@SuppressWarnings("unused") ActionEvent event) {
        nullFy();
    }

    
    @FXML
    private void click(ActionEvent event) {
       Button button = (Button) event.getSource(); 
       int number = Integer.parseInt(button.getText());
       makeGuess(number);
        

    }
    @FXML
    private void Switch(ActionEvent event){
        Button button = (Button) event.getSource(); 
        String choice = button.getText();
        makeSwitch(choice);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       //
        
    }
    
    
    @FXML
    void startAgain(@SuppressWarnings("unused") MouseEvent event) {
        Choice1.setVisible(false);
        Choice2.setVisible(false);
        one.setVisible(true);
        two.setVisible(true);
        three.setVisible(true);
        montyClass.cleanAll();
        main();

    }


    
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Toiminnallisuus alkaa
    
    
    private MontyHallClass montyClass;
    private int winCount = 0;
    private int lossCount = 0;
    
    /**
     * Tehdään alustukset 
     */
    void main() {
        systemPrint.setText("Welcome to play Monty Hall game, select door, your options are in below");
        
    }
    
    
    /**
     * Tekee arvauksen sillä perustellaa mitä ovea käyttäjä on painanut, kysytään haluaako käyttäjä vaihtaa ovea, asetetaan myös ovet piiloon.
     */
    private void makeGuess(int number){
        montyClass.makeGuess(number);
        System.out.println(number);
        int reaveled = montyClass.RevealDoor();
        systemPrint.setText("The host is now showing that door " + reaveled + " is wrong door,your door is "+ number +" Do you want to switch your door ?");
        //hideWrongDoor(reaveled);
        Choice1.setVisible(true);
        Choice2.setVisible(true);
        one.setVisible(false);
        two.setVisible(false);
        three.setVisible(false);
        return;
    }
    
    /**
     * Simuloidaan peliä n kertaa, boolean voitto = montyClass.simulate(yesNo); tuo funktio hoitaa itse pelin täällä sitten vain tarkastetaan tuliko voitta vai ei 
     * ja päivitetään win/loss tilastoja sen mukaan
     */
    private void startSimulation(){
        int n = Integer.parseInt(number.getText());
        boolean yesNo = yesSwitch.isSelected();
        Random rand = new Random();
        int  right = rand.nextInt(3) + 0;
        System.out.println(right);
        for (int i = 0; i < n;i++){
            boolean voitto = montyClass.simulate(yesNo);
            montyClass.cleanAll();
            
            if(voitto == true){
                winCount ++;
            }
            
            if(voitto == false) {
                lossCount ++;
            }
        }
        wins.setText(String.valueOf(winCount));
        lost.setText(String.valueOf(lossCount));
        
        
    }
    
    
    /**
     * @param monty  alustettu montyluokka joka hoitaa logiikan pelaamisessa
     */
    public void setMonty(MontyHallClass monty) {
        this.montyClass = monty;
    }
    
    
    /**
     * Nyt tarkistetaan haluaako pelaaja tehdä oven vaihdon 
     * @param choice String 'Yes' or 'No'
     */
    private void makeSwitch(String choice){
        if(choice.equals("Yes")){
            int newDoor = montyClass.switchDoor();
            systemPrint.setText("You chose to switch, your new door is now " + newDoor);
            System.out.println(newDoor);
            
            
        }
        //lopetetaan peliu
        endGame();
        return;
    }
    
    
    /**
     * Nyt tarkistetaan voittiko pelaaja, ja päivitetään win/loss countit sen mukaan
     */
    private void endGame(){
        boolean winLost = montyClass.endGame();
        System.out.println(montyClass.getRight());
        if( winLost== true){
            systemPrint.setText("Congrats, yoy win, to play again click the screen");
            winCount ++;
            wins.setText(String.valueOf(winCount));
            return;
        } 
        if (winLost == false){
        System.out.println("kävit");
        systemPrint.setText("Sorry you just lost , to play again click the screen");
        lossCount ++;
        lost.setText(String.valueOf(lossCount));
        return;
        }
        return;
        
    }
    
    
    /**
     * Pyyhitään win/loss Countit takaisin nollaksi
     */
    private void nullFy(){
        winCount = 0;
        lossCount = 0;
        wins.setText(String.valueOf(winCount));
        lost.setText(String.valueOf(lossCount));
    }
    
    
    /**
    private void hideWrongDoor(int revealed){
        System.out.println(revealed);
        
        switch(revealed){
        
        case 0 : one.setVisible(false);
        break;
        case 1 : two.setVisible(false);
        break;
        case 2 : three.setVisible(false);
        break;
        
        default:
            break;
        
        }
        */
        
        
        
    }
        
    

    
    
	

