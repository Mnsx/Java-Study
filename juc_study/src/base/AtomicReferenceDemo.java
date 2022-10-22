package base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/22 19:47
 * @Description:
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("z3", 22);
        User l4 = new User("l4", 38);

        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t" + atomicReference.get().toString());
    }
}

@Data
@AllArgsConstructor
class User {
    String username;
    int age;
}
