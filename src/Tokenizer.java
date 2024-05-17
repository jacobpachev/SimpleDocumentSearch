import java.util.List;

public class Tokenizer {
    public String tokenize(String text) {
        text = text.replaceAll("\n", " ").replaceAll("[^a-zA-Z\\s]", "").replaceAll("-", " ").toLowerCase();
        var finalText = new StringBuilder();
        for(var word : text.split(" ")) {
            finalText.append(word).append(" ");
        }
        return finalText.toString();
    }
}
