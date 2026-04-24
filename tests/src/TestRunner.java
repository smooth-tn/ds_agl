import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        List<Class<?>> testClasses = List.of(
                ConnectionTest.class,
                ClientServiceTest.class,
                BookServiceTest.class,
                BorrowServiceTest.class
        );
        int total = 0, passed = 0;
        for (Class<?> clazz : testClasses) {
            System.out.println("\n── " + clazz.getSimpleName() + " ──");
            Object instance = clazz.getDeclaredConstructor().newInstance();
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().startsWith("test")) {
                    total++;
                    try {
                        m.invoke(instance);
                        System.out.println("✓ " + m.getName());
                        passed++;
                    } catch (Exception e) {
                        System.out.println("✗ " + m.getName() + " → " + e.getCause().getMessage());
                    }
                }
            }
        }
        System.out.printf("\n📊 Résultats : %d/%d tests réussis.\n", passed, total);
    }
}