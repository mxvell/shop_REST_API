package prachykAndMoroka.market.utill;

public class UserNotFoundException extends Exception {
   public UserNotFoundException(long userId){
       super("User with ID " + userId + " not found.");
   }
}