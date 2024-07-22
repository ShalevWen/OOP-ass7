// 329238919 Shalev Wengrowsky

import java.util.Map;
import java.util.regex.Pattern;

/**
 * The type Regex finder.
 */
public abstract class RegexFinder {
    /**
     * The Pattern.
     */
    private Pattern pattern;

    /**
     * Gets pattern.
     *
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * Sets pattern.
     *
     * @param pattern the pattern
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Get a string and return all hypernymy relations in it.
     *
     * @param line the string
     * @return a list with all the hypernymy relations
     */
    public abstract Map<String, Hypernym> find(String line);
}
