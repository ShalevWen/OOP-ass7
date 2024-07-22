// 329238919 Shalev Wengrowsky

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The main class.
 */
public class DiscoverHypernym {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String dbPath = "./hypernym_db.txt";
        if (args.length == 3) {
            dbPath = args[2];
        }
        File database = new File(dbPath);
        if (!database.exists()) {
            CreateHypernymDatabase.createHypernymDatabase(args[0], dbPath, true);
        }
        BufferedReader lines = null;
        try {
            lines = new BufferedReader(new java.io.FileReader(database.getAbsoluteFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Integer> hypernyms = new HashMap<>();
        for (String line : new ArrayList<>(lines.lines().toList())) {
            String hyponyms = line.split(": ")[1];
            String[] hyponymsArray = hyponyms.split(", ");
            for (String hyponym : hyponymsArray) {
                if (hyponym.split(" [(]")[0].equals(args[1])) {
                    int num = Integer.parseInt(hyponym.split(" [(]")[1].replace(")", ""));
                    hypernyms.put(line.split(":")[0], num);
                }
            }
        }
        hypernyms.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .forEach((hypernym) -> System.out.println(hypernym.getKey() + ": (" + hypernym.getValue() + ")"));
    }
}
