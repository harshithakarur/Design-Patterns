import test.Facade;

import java.util.Scanner;

public class TestDemo {
    static Scanner sc= new Scanner(System.in); //System.in is a standard input stream
    static Facade facade = new Facade();

    public static void main(String[] args) throws Exception {
        System.out.println("Enter User name:");
        String userName = sc.nextLine();
//        System.out.println(userName);
        System.out.println("Enter User password:");
        String password = sc.nextLine();
//        System.out.println(password);
        facade.login(userName, password);

    }
}
