package formatter;

import formatter.dao.UserDAO;
import formatter.dao.impl.UserDAOImpl;
import formatter.exception.WrongEntryException;
import formatter.user.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserActivity {

//    public static void main(String[] args) throws WrongEntryException {
//        new UserActivity();
//    }
    UserDAO userDAO = new UserDAOImpl();
    public UserActivity() throws WrongEntryException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter R if you are registered user, U if you are unregistered user");
        String answer1 = scanner.nextLine();
        if (answer1.equalsIgnoreCase("R")) {
            System.out.println("Please enter your login");
            String login = scanner.nextLine();
            if (userDAO.getAllLogins().contains(login)) {
                System.out.println("Please enter your password");
                String password = scanner.nextLine();
                // проверка правильности пароля
                User user = new User(login, password);
            }
            else {
                List<String> list = userDAO.getAllLogins();
                for(String str: list) {
                    System.out.println(str);
            }}

                System.out.println("This login doesn't exist!");

        } else if (answer1.equalsIgnoreCase("U")) {
            System.out.println("Would you like to register? Enter Y - yes or N - no");
            String answer2 = scanner.nextLine();
            if (answer2.equalsIgnoreCase("Y")) {

                System.out.println("Please create login and press Enter");
                String login = scanner.nextLine();
                // проверка в базе, существует ли такой логин

                System.out.println("Please enter your password");
                String password = scanner.nextLine();
                User user = new User(login, password);
                userDAO.createUser(user);

            } else if (answer2.equalsIgnoreCase("N")) {
                UUID uuid = UUID.randomUUID();
                String login = uuid.toString();
            }
        } else throw new WrongEntryException();
    }
}
