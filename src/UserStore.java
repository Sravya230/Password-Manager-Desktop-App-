import java.util.HashMap;
import java.util.Map;

public class UserStore {
    private static final Map<String, String> users = new HashMap<>();

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, password);
        return true;
    }

    public static boolean login(String username, String password) {
        return password.equals(users.get(username));
    }
}
