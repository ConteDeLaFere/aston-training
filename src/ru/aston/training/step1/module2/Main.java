import ru.aston.training.step1.module2.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;



void main() {
    final String filepath = "students.json";
    StudentJsonFIO studentJsonFIO = new StudentJsonFIO(filepath);
    List<Student> students = studentJsonFIO.loadStudents();

    if (students != null && !students.isEmpty()) {
        System.out.println("=== 1. Список всех студентов ===");
        students.forEach(System.out::println);

        System.out.println("\n=== 2. Списки книг каждого студента ===");
        students.forEach(student -> System.out.println(student.name() + ": " + student.books()));

        System.out.println("\n=== 3. Все книги, отсортированные по количеству страниц ===");
        students.stream()
                .flatMap(student -> student.books().stream())
                .sorted(Comparator.comparingInt(Book::pageCount))
                .forEach(System.out::println);

        System.out.println("\n=== 4. Только уникальные книги (без повторов) ===");
        students.stream()
                .flatMap(student -> student.books().stream())
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n=== 5. Книги, выпущенные после 2000 года ===");
        students.stream()
                .flatMap(student -> student.books().stream())
                .filter(book -> book.year() > 2000)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n=== 6. Первые 3 книги из общего потока ===");
        students.stream()
                .flatMap(student -> student.books().stream())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n=== 7. Все годы выпуска книг ===");
        students.stream()
                .flatMap(student -> student.books().stream())
                .map(Book::year)
                .distinct()
                .forEach(year -> System.out.print(year + " "));


        System.out.println("\n\n=== 8. При помощи методов короткого замыкания вернуть Optional от года ===");
        Optional<Book> bookOptional = students.stream()
                .flatMap(student -> student.books().stream())
                .findAny();

        bookOptional.ifPresent(System.out::println);

        System.out.println("\n=== 9. При помощи методов получения значения из Optional вывести в консоль год выпуска найденной книги, либо запись о том, что такая книга отсутствует ===");
        bookOptional = students.stream()
                .flatMap(student -> student.books().stream())
                .filter(book -> book.year() == 2000)
                .findAny();

        bookOptional.ifPresentOrElse(
                book -> System.out.printf("Год выпуска книги '%s' — %d%n", book.title(), book.year()),
                () -> System.out.println("Книга не найдена")
        );
    }
}
