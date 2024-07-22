// 329238919 Shalev Wengrowsky

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The fourth type of regex.
 */
public class FourthRegex extends RegexFinder {
    /**
     * Instantiates a new Fourth regex.
     */
    public FourthRegex() {
        this.setPattern(Pattern.compile(
                "<np>([^<]+)</np>(?> ,)? especially <np>"
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
