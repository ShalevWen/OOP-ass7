// 329238919 Shalev Wengrowsky

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main class.
 */
public class CreateHypernymDatabase {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String corpusPath = args[0];
        String outPath = args[1];
        CreateHypernymDatabase.createHypernymDatabase(corpusPath, outPath, false);
    }

    /**
     * Create hypernym database.
     *
     * @param corpusPath the corpus path
     * @param outPath    the database path
     * @param full       should the database include hypernyms with less than 3 hyponyms
     */
    public static void createHypernymDatabase(String corpusPath, String outPath, boolean full) {
        RegexFinder[] finders =
                {new FirstRegex(), new SecondRegex(), new ThirdRegex(), new FourthRegex(), new FifthRegex()};
        File corpus = new File(corpusPath);
        File[] files = corpus.listFiles();
        if (files == null) {
            System.out.println("No files in " + corpusPath);
            return;
        }
        Map<String, Hypernym> hypernyms = new HashMap<>();
        for (File file : files) {
            BufferedReader lines = null;
            try {
                lines = new BufferedReader(new java.io.FileReader(file.getAbsoluteFile()));
                String line = lines.readLine();
                while (line != null) {
                    line = line.toLowerCase();
                    for (RegexFinder finder : finders) {
                        Map<String, Hypernym> newHypernyms = finder.find(line);
                        for (Map.Entry<String, Hypernym> newHypernym : newHypernyms.entrySet()) {
                            if (hypernyms.containsKey(newHypernym.getKey())) {
                                hypernyms.get(newHypernym.getKey()).addHyponyms(newHypernym.getValue().getHyponyms());
                            } else {
                                hypernyms.put(newHypernym.getKey(), newHypernym.getValue());
                            }
                        }
                    }
                    line = lines.readLine();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Error in file " + file.getName());
            } finally {
                try {
                    if (lines != null) {
                        lines.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        List<Map.Entry<String, Hypernym>> hypernymList =
                hypernyms.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList();
        try {
            PrintWriter out = new PrintWriter(outPath);
            for (Map.Entry<String, Hypernym> hypernym : hypernymList) {
                if (hypernym.getValue().getHyponyms().size() < 3 && !full) {
                    continue;
                }
                out.println(hypernym.getValue().toString());
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
