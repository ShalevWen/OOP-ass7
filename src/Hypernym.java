// 329238919 Shalev Wengrowsky

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Hypernym.
 */
public class Hypernym {
    private String name;
    private Map<String, Integer> hyponyms;

    /**
     * Instantiates a new Hypernym.
     *
     * @param name the name
     */
    public Hypernym(String name) {
        this.name = name;
        this.hyponyms = new HashMap<>();
    }

    /**
     * Gets hyponyms.
     *
     * @return the hyponyms
     */
    public Map<String, Integer> getHyponyms() {
        return hyponyms;
    }

    @Override
    public String toString() {
        List<Map.Entry<String, Integer>> hyponyms = this.hyponyms.entrySet().stream()
                .sorted((e1, e2) -> {
                    if (e1.getValue() > e2.getValue()) {
                        return -1;
                    } else if (e1.getValue() < e2.getValue()) {
                        return 1;
                    } else {
                        return e1.getKey().compareTo(e2.getKey());
                    }
                }).toList();
        return this.name + hyponyms.toString()
                .replace("[", ": ")
                .replace("]", ")")
                .replace("=", " (")
                .replace(",", "),");
    }

    /**
     * Add hyponym.
     *
     * @param hyponym the hyponym
     * @param count   the count
     */
    public void addHyponym(String hyponym, int count) {
        if (this.hyponyms.containsKey(hyponym)) {
            this.hyponyms.put(hyponym, this.hyponyms.get(hyponym) + count);
        } else {
            this.hyponyms.put(hyponym, count);
        }
    }

    /**
     * Add all the hyponyms.
     *
     * @param hyponyms the hyponyms to add
     */
    public void addHyponyms(Map<String, Integer> hyponyms) {
        for (Map.Entry<String, Integer> hyponym : hyponyms.entrySet()) {
            this.addHyponym(hyponym.getKey(), hyponym.getValue());
        }
    }
}
