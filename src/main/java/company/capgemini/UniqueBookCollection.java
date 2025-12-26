package company.capgemini;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UniqueBookCollection {

    public static void main(String[] args) {

        Set<Book> bookSet = new HashSet<>();

        // Adding books
        bookSet.add(new Book("The Alchemist", "Paulo Coelho"));
        bookSet.add(new Book("1984", "George Orwell"));
        bookSet.add(new Book("The Alchemist", "Paulo Coelho")); // Duplicate (should not be added)

        // Print books
        System.out.println("Books in the collection:");
        for (Book book : bookSet) {
            System.out.println(book);
        }

    }
}

class Book {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Override equals() to compare title and author
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    // Override hashCode() to ensure uniqueness in HashSet
    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "Book{Title='" + title + "', Author='" + author + "'}";
    }
}
