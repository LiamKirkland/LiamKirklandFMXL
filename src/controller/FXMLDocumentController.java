package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Matcheduser;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private Button readButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button createButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button findByDateButton;

    @FXML
    private Button findByNameButton;

    @FXML
    void quit(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void createAction(ActionEvent event) throws SQLException {
        Scanner input = new Scanner(System.in);
        //My create function will only ask for 1 attribute from the user, since ID and Date will be auto generated
        System.out.println("Please enter your friend's name:");
        String name = input.nextLine();
        System.out.println("Please enter the ID you want to assign to your friend:");
        int id = input.nextInt();
        Date date = new Date();

        Matcheduser newUser = new Matcheduser(id, name, date);
        create(newUser);
    }

    @FXML
    void readAction(ActionEvent event) {

        //Code modified from Demo code, I don't use readAll here since this one include the Date, which I feel like readAll shouldn't use to keep the console less cluttered
        Query query = manager.createNamedQuery("Matcheduser.findAll");
        List<Matcheduser> matchedUsers = query.getResultList();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        for (Matcheduser m : matchedUsers) {
            System.out.println(m.getId() + " Name: " + m.getName() + ", Matched Since: " + df.format(m.getMatchsince()));
        }
    }

    @FXML
    void updateAction(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        //Code modified from Demo code (reused code from Read to allow the user to choose who they want to edit
        readAll();
        System.out.println("\nEnter the ID of the friend you want to edit:");
        int id = input.nextInt();

        System.out.println("Enter the new name of this friend:");
        input.nextLine(); //Not sure why I have to do this, but stackoverflow said it was a fix and it worked (https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo)
        String name = input.nextLine(); //For some reason without the first input.nextLine(), the linebreak character is used as the new name, instead of whatever the user inputs

        Matcheduser matchedUser = new Matcheduser(id, name, null);
        update(matchedUser);
    }

    @FXML
    void deleteAction(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        readAll();
        System.out.println("\nEnter the ID of the friend you want to delete:");
        int id = input.nextInt();

        Matcheduser matchedUser = new Matcheduser(id);
        delete(matchedUser);
    }

    @FXML
    void findByDefinedName(ActionEvent event) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter a letter or set of letters to search names by (do not use spaces):");
        String search = input.next();
        
        Query query = manager.createNamedQuery("Matcheduser.findNamesContaining");
        query.setParameter("name", "%" + search + "%");
        
        List<Matcheduser> foundNames = query.getResultList();
        for(Matcheduser user : foundNames){
            System.out.println("ID: " + user.getId() + " Named: " + user.getName() + ", Matched Since: " + df.format(user.getMatchsince()));
        }
    }

    @FXML
    void findByDate(ActionEvent event) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Scanner input = new Scanner(System.in);
        System.out.println("\nEnter a date to find which friends you've met before (YYYY-MM-DD format):");
        String search = input.next();
        
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(search);
        
        Query query = manager.createNamedQuery("Matcheduser.findMatchedBeforeDate");
        query.setParameter("matchsince", date);
        
        List<Matcheduser> foundNames = query.getResultList();
        for(Matcheduser user : foundNames){
            System.out.println("ID: " + user.getId() + " Named: " + user.getName() + ", Matched Since: " + df.format(user.getMatchsince()));
        }
    }

    EntityManager manager;

    @FXML
    void initialize() {

        manager = (EntityManager) Persistence.createEntityManagerFactory("LiamKirklandFMXLPU").createEntityManager();
    }

    //Using code from the IntroJavaFX Demo
    public void create(Matcheduser matchedUser) {
        try {
            manager.getTransaction().begin();

            if (matchedUser.getId() != null) {
                manager.persist(matchedUser);
                manager.getTransaction().commit();

                System.out.println(matchedUser.toString() + " successfully created.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Using code from Demo project
    public void update(Matcheduser matchedUser) {
        try {
            Matcheduser existingUser = manager.find(Matcheduser.class, matchedUser.getId());

            if (existingUser != null) {
                manager.getTransaction().begin();

                existingUser.setName(matchedUser.getName());

                manager.getTransaction().commit();
                System.out.println("Friend successfully updated!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Created this to help with code clarity.
    public void readAll() {
        Query query = manager.createNamedQuery("Matcheduser.findAll");
        List<Matcheduser> matchedUsers = query.getResultList();

        System.out.println("Friends:");
        for (Matcheduser m : matchedUsers) {
            System.out.println("ID: " + m.getId() + " Name: " + m.getName());
        }
    }

    //Using code from Demo project
    public void delete(Matcheduser matchedUser) {
        try {
            Matcheduser existingUser = manager.find(Matcheduser.class, matchedUser.getId());

            if (existingUser != null) {
                System.out.println(existingUser.getName() + " was successfully deleted.");
                manager.getTransaction().begin();
                manager.remove(existingUser);
                manager.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
