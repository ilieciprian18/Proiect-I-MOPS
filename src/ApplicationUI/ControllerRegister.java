package ApplicationUI;

import Aplication.SQLConnect;
import Person.DateOfBirth;
import Person.Gender;
import UserStuff.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerRegister {

    @FXML
    private TextField username;

    @FXML
    private Label wrongUsername;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    @FXML
    private Label wrongName;

    @FXML
    private TextField name;

    @FXML
    private Label wrongPrenume;

    @FXML
    private TextField prenume;

    @FXML
    private Label wrongEmail;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label wrongPhoneNumber;

    @FXML
    private ChoiceBox<String> chooseSex;

    @FXML
    private Label wrongSelected;

    private String[] select = {"male", "female"};

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private Label wrongDate;



    public void ControllerRegister(){




    }

    public void initialize(){

        chooseSex.getItems().addAll(select);

    }



    public void toLogin(ActionEvent event){

        System.out.println("Merge");
    }

    public void signUp(ActionEvent event) throws SQLException {

        SQLConnect SQL = new SQLConnect();
        boolean okusername = false;
        boolean okPassword = false;
        boolean okName = false;
        boolean okPrenume = false;
        boolean okEmail = false;
        boolean okPhoneNumber = false;
        boolean okSelect = false;
        boolean okDate = false;
        String tempUsername = username.getText();

        if(tempUsername.length() >=1 && tempUsername.contains(" ") == false && SQL.searchUsername(tempUsername)==true )
        {
            System.out.println("da");
            wrongUsername.setVisible(false);
            okusername = true;
        }
        else {
            if( tempUsername.length() <1)
            {
                wrongUsername.setText("Required*");
            }

            if(tempUsername.contains(" ") == true)
            {
                wrongUsername.setText("No spaced allowed");
            }

            if(SQL.searchUsername(tempUsername) == false)
            {
                wrongUsername.setText("Username already taken!");
            }
        }

        if(okusername == false)
        {
            wrongUsername.setVisible(true);
        }

        String tempPassword = password.getText();
        okPassword = checkPassword(tempPassword);
        //System.out.println(okPassword);
        if(okPassword == false)
        {
            wrongPassword.setVisible(true);
        }
        else{
            wrongPassword.setVisible(false);

        }

        String tempName = name.getText();
        if(tempName.length() >= 1)
        {
            okName = true;
            wrongName.setVisible(false);
        }

        if(okName == false){
            wrongName.setVisible(true);
        }

        String tempPrenume = prenume.getText();
        if(tempPrenume.length() >=1)
        {
            okPrenume = true;
            wrongPrenume.setVisible(false);
        }

        if(okPrenume == false)
        {
            wrongPrenume.setVisible(true);
        }

        String tempEmail = email.getText();
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(tempEmail);
        System.out.println(matcher.matches());
        if(matcher.matches() == true)
        {
            wrongEmail.setVisible(false);
            okEmail = true;

        }

        if ( okEmail == false)
        {
            wrongEmail.setVisible(true);
            wrongEmail.setText("email not valid");
        }

        String tempPhoneNumber = phoneNumber.getText();
        pattern = Pattern.compile("^\\d{10}$");
        matcher = pattern.matcher(tempPhoneNumber);
        if(matcher.matches() == true)
        {
            wrongPhoneNumber.setVisible(false);
            okPhoneNumber = true;

        }

        if(okPhoneNumber == false)
        {
            wrongPhoneNumber.setVisible(true);
            wrongPhoneNumber.setText("not valid");
        }

        String x = "";
        String tempSex = chooseSex.getValue();
        System.out.println(tempSex);
        if(tempSex != null)
        {

            if(tempSex.equals("female"))
            {
                 x = "female";
            }
            else {
                 x = "male";
            }
            okSelect = true;
            wrongSelected.setVisible(false);
        }




        System.out.println(okSelect);

        if(okSelect == false)
        {
            wrongSelected.setVisible(true);
        }


        int tempDay =0;
        int tempMonth = 0;
        int tempYear = 0;

        if(dateOfBirth.getValue() != null){
            okDate = true;
            wrongDate.setVisible(false);

             tempDay = dateOfBirth.getValue().getDayOfMonth();
             tempMonth = dateOfBirth.getValue().getMonthValue();
             tempYear = dateOfBirth.getValue().getYear();
        }




        if(okDate == false)
        {
            wrongDate.setVisible(true);
        }

        if( okusername && okName && okPrenume && okPassword && okEmail && okPhoneNumber && okSelect && okDate)
        {
            //select


            if( x.equals("female"))
            {
                SQL.insertUser(new User(tempName,tempPrenume,tempEmail,tempPhoneNumber,Gender.FEMALE,
                        new DateOfBirth(tempDay, tempMonth,tempYear),tempUsername));
                SQL.insertUsername(tempUsername,tempPassword);
                SQL.auditLog("New user " + tempUsername + " registered!");
            }
            else
            {
                SQL.insertUser(new User(tempName,tempPrenume,tempEmail,tempPhoneNumber,Gender.MALE,
                        new DateOfBirth(tempDay, tempMonth,tempYear),tempUsername));
                SQL.insertUsername(tempUsername,tempPassword);
                SQL.auditLog("New user " + tempUsername + " registered!");
            }

            username.clear();
            password.clear();
            name.clear();
            prenume.clear();
            email.clear();
            phoneNumber.clear();
            chooseSex.valueProperty().set(null);
            dateOfBirth.valueProperty().set(null);

        }














    }

    public void goToLogin(ActionEvent event) throws IOException {

        MainAppUI m = new MainAppUI();
        m.changeScene("ApplicationUI.fxml");
    }

    private boolean checkPassword(String password){

        boolean isValid = true;
        String error = "Minim: ";
        if(password.length() < 8)
        {
            isValid = false;
            error = error + "8 caractere";
        }

        String uppercase = "(.*[A-Z].*)";
        if(!password.matches(uppercase)){
            isValid = false;
            error = error + " o litera mare";
        }

        String numbers = "(.*[1-9].*)";
        if(!password.matches(numbers))
        {
            isValid = false;
            error = error + " o cifra";
        }

        System.out.println();
        wrongPassword.setText(error);
        return isValid;

    }
}
