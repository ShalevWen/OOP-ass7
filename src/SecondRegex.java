// 329238919 Shalev Wengrowsky

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The second type of regex.
 */
public class SecondRegex extends RegexFinder {
    /**
     * Instantiates a new Second regex.
     */
    public SecondRegex() {
        this.setPattern(Pattern.compile(
                "such <np>([^<]+)</np> as <np>"
                        + "([^<]+(?></np> , <np>[^<]+)*)</np>(?> , (?>and|or) <np>([^<]+)</np>)?"));
    }

    @Override
    public Map<String, Hypernym> find(String line) {
        Matcher matcher = this.getPattern().matcher(line);
        Map<String, Hypernym> results = new HashMap<>();
        while (matcher.find()) {
            Hypernym hypernym = new Hypernym(matcher.group(1));
            String[] hyponyms = matcher.group(2).split("</np> , <np>");
            for (String hyponym : hyponyms) {
                hypernym.addHyponym(hyponym, 1);
            }
            if (matcher.group(3) != null) {
                hypernym.addHyponym(matcher.group(3), 1);
            }
            results.put(matcher.group(1), hypernym);
        }
        return results;
    }
}
