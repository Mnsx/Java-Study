package base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/22 20:13
 * @Description:
 */
public class AtomicStampedDemo {
    public static void main(String[] args) {
        Book javaBook = new Book(1, "java");

        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);

        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book mysqlBook = new Book(2, "mysql");

        boolean flag = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(flag + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        flag = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(flag + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}

@Data
@AllArgsConstructor
class Book {
    private int id;
    private String bookName;
}
