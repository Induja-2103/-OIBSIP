import java.util.*;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private Map<Integer, Integer> issuedBooks; // bookId -> memberId

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        issuedBooks = new HashMap<>();
    }

    // Admin functions
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void removeBook(int bookId) {
        books.removeIf(book -> book.getId() == bookId);
        System.out.println("Book removed successfully!");
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member added successfully!");
    }

    public void removeMember(int memberId) {
        members.removeIf(member -> member.getId() == memberId);
        System.out.println("Member removed successfully!");
    }

    // User functions
    public void viewBooks() {
        for (Book book : books) {
            System.out.println(book.getId() + ". " + book.getTitle() + " by " + book.getAuthor() + (book.isIssued() ? " (Issued)" : ""));
        }
    }

    public void searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book.getId() + ". " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void issueBook(int bookId, int memberId) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.isIssued()) {
                book.setIssued(true);
                issuedBooks.put(bookId, memberId);
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not available!");
    }

    public void returnBook(int bookId) {
        if (issuedBooks.containsKey(bookId)) {
            for (Book book : books) {
                if (book.getId() == bookId) {
                    book.setIssued(false);
                    break;
                }
            }
            issuedBooks.remove(bookId);
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("This book was not issued!");
        }
    }
}
