import java.io.*;
import java.util.*;

public class PasswordManager {
    private static final String FILE = "passwords.dat";

    public static void save(String website, String username, String password) throws Exception {
        String encrypted = AESUtil.encrypt(website + "," + username + "," + password);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(encrypted);
            bw.newLine();
        }
    }

    public static List<String[]> readAll() throws Exception {
        List<String[]> entries = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return entries;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String decrypted = AESUtil.decrypt(line);
                entries.add(decrypted.split(",", 3));
            }
        }
        return entries;
    }
}
