package formatter;

import formatter.dao.UserDAO;
import formatter.dao.impl.UserDAOImpl;
import formatter.user.User;

import java.util.Scanner;
import java.util.UUID;

public class UserActivity {

    UserDAO userDAO = new UserDAOImpl();

    public UserActivity() {
        Scanner scanner = new Scanner(System.in);
        String answer1 = askRegistration();

        if (answer1.equalsIgnoreCase("R")) {
            System.out.println("Please enter your login");
            String login = scanner.nextLine();
            if (userDAO.checkLogin(login) != null) {
                System.out.println("Please enter your password");
                checkPassword(login);
            }
            else System.out.println("This login does not exist! Please try again");
        }

        else if (answer1.equalsIgnoreCase("U")) {
            String answer2 = replyYesNo();

            if (answer2.equalsIgnoreCase("Y")) {
                System.out.println("Please create login and press Enter");
                String login = createLogin();
                String confirmedPassword = createPassword();
                String salt = UUID.randomUUID().toString();
                User user = new User(login, salt, confirmedPassword);
                userDAO.createUser(user);
                }

            else if (answer2.equalsIgnoreCase("N")) {
                System.out.println("See you next time!");
            }
        }
    }

    public String askRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter R if you are registered user, U if you are unregistered user");
        String answer1 = scanner.nextLine();
        while (!answer1.equalsIgnoreCase("R") & !answer1.equalsIgnoreCase("U")) {
            System.out.println("You entered a wrong symbol. Please enter R if you are registered user, U if you are unregistered user");
            answer1 = scanner.nextLine();
        }
        return answer1;
    }

    public String replyYesNo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to register? Enter Y - yes or N - no");
        String answer2 = scanner.nextLine();
        while (!answer2.equalsIgnoreCase("Y") & !answer2.equalsIgnoreCase("N")) {
            System.out.println("You entered a wrong symbol. Please choose Y for yes, N for no to continue");
            answer2 = scanner.nextLine();
        }
        return answer2;
    }

    public String createPassword() {
        Scanner scanner = new Scanner(System.in);

        String password = "1";
        String confirmedPassword = "2";

        while (!confirmedPassword.equals(password)) {
            System.out.println("Please enter your password");
            password = scanner.nextLine();
            System.out.println("Confirm your password");
            confirmedPassword = scanner.nextLine();
        }
        return confirmedPassword;
    }

    public void checkPassword(String login) {
        Scanner scanner = new Scanner(System.in);
        User user = userDAO.getSaltByLogin(login);
        String password = Integer.toHexString((user.getSalt().concat(scanner.nextLine())).hashCode());
        User userDB = userDAO.getHashBySalt(user.getSalt());
        String hashDB = userDB.getHash();

        while (!password.equals(hashDB)) {
            System.out.println("↯↯ Wrong password! Please try again");
            password = Integer.toHexString((user.getSalt().concat(scanner.nextLine())).hashCode());
        }
        System.out.println("Success entry!");
    }

    public String createLogin() {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Please create login and press Enter");
        String login = scanner.nextLine();
        while (userDAO.checkLogin(login) != null) {
            System.out.println("Please choose other login");
            login = scanner.nextLine();
        }
        return login;
    }
}
