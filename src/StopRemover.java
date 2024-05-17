public class StopRemover {
    public String removeStops(String text) {
        var stops = new StopWords();
        var cleanedText = new StringBuilder();
        for(var word : text.split(" ")) {
            if(!stops.contains(word)) {
                cleanedText.append(word).append(" ");
            }
        }
        return cleanedText.toString();
    }
}
