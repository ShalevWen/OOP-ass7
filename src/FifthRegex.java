// 329238919 Shalev Wengrowsky

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The fifth type of regex.
 */
public class FifthRegex extends RegexFinder {
    /**
     * Instantiates a new Fifth regex.
     */
    public FifthRegex() {
        this.setPattern(Pattern.compile(
                "<np>([^<]+)</np>(?> ,)? which is (?>(?>an example|a kind|a class) of)? <np>([^<]+)</np>"));
    }

    @Override
    public Map<String, Hypernym> find(String line) {
        Matcher matcher = this.getPattern().matcher(line);
        Map<String, Hypernym> results = new HashMap<>();
        while (matcher.find()) {
            Hypernym hypernym = new Hypernym(matcher.group(2));
            hypernym.addHyponym(matcher.group(1), 1);
            results.put(matcher.group(2), hypernym);
        }
        return results;
    }
}
