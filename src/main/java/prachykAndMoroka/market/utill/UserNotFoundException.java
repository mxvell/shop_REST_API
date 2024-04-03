package prachykAndMoroka.market.utill;

public class UserNotFoundException extends Exception {
     public String getMessage(){
          return "This user doesn't exist";
     }
}