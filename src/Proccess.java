import java.io.*;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Proccess {
    public ArrayList<ArrayList<String>> proccessFiles(Integer numFiles) throws IOException {
        var allStems = new ArrayList<ArrayList<String>>();
//        var dictionary = new Dictionary().getDictionary("/home/jacob/CS_453/project_1/Project 1/src/dictionary.txt");
        for(var i = 1; i < numFiles; i++) {
            var filePath = "/home/jacob/CS_453/project_1/Project 1/src/wiki_files/Doc ("+i+").txt";
            allStems.add(proccessFile(filePath));
        }
        return allStems;
    }

    private ArrayList<String> proccessFile(String filePath) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String fileContent = br.lines().collect(Collectors.joining(System.lineSeparator()));
        return processText(fileContent);
    }

    public ArrayList<String> processText(String text) {
        var tokenizedText = new Tokenizer().tokenize(text);
        var stoppedText = new StopRemover().removeStops(tokenizedText);
        var stemmer = new PorterStemmer();
        var stemmedWords = new ArrayList<String>();
        for(var word : stoppedText.split(" ")) {
            if(word.matches("[a-zA-Z]+")) {
                var stemmedWord = stemmer.stem(word.strip());
                stemmedWords.add(stemmedWord);
            }
        }
        return stemmedWords;
    }
}
