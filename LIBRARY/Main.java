import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Welcome to the Library Management System!");

        while (!exit) {
            System.out.println("\nAre you an Admin or a User? (admin/user/exit)");
            String role = scanner.nextLine();

            switch (role.toLowerCase()) {
                case "admin":
                    adminMenu();
                    break;
                case "user":
                    userMenu();
                    break;
                case "exit":
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid role. Try again.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Add Member");
            System.out.println("4. Remove Member");
            System.out.println("5. View Books");
            System.out.println("6. Logout");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 2:
                    System.out.print("Enter Book ID to remove: ");
                    int removeId = Integer.parseInt(scanner.nextLine());
                    library.removeBook(removeId);
                    break;
                case 3:
                    System.out.print("Enter Member ID: ");
                    int memberId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Member Email: ");
                    String email = scanner.nextLine();
                    library.addMember(new Member(memberId, name, email));
                    break;
                case 4:
                    System.out.print("Enter Member ID to remove: ");
                    int removeMemberId = Integer.parseInt(scanner.nextLine());
                    library.removeMember(removeMemberId);
                    break;
                case 5:
                    library.viewBooks();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    library.viewBooks();
                    break;
                case 2:
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    library.searchBook(keyword);
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Your Member ID: ");
                    int memberId = Integer.parseInt(scanner.nextLine());
                    library.issueBook(bookId, memberId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnBookId = Integer.parseInt(scanner.nextLine());
                    library.returnBook(returnBookId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
