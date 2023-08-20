package ExpressionEval;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableExtractor {

    public static Set<String> extractVariableIds(String expression) {
        Set<String> variableIds = new HashSet<>();

        Pattern pattern = Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String match = matcher.group();
            variableIds.add(match);
        }

        return variableIds;
    }
}