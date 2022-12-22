package Aplication;

public class Password {

    public static boolean passwordIsValid(String passwordX)
    {
        boolean valid = true;
        if(passwordX.length() < 8)
        {
            valid = false;
            System.out.println("Parola trebuie sa aibe minim 8 caractere");
        }

        String upperCase = "(.*[A-Z].*)";
        if(!passwordX.matches(upperCase))
        {
            valid = false;
            System.out.println("Parola trebuie sa aibe minim o litera mare");
        }

        String numbers = "(.*[0-9].*)";
        if( !passwordX.matches(numbers))
        {
            System.out.println("Parola trebuie sa aibe minim o cifra");
            valid = false;
        }
        return valid;

    }
}
