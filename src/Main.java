import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        var allStems = new Proccess().proccessFiles(323);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(allStems);
//        try (FileWriter writer = new FileWriter("all_stems.json")) {
//            writer.write(json);
//        }
        var gson = new Gson();
        try (FileReader reader = new FileReader("/home/jacob/CS_453/project_1/Project 1/all_stems.json")) {
            ArrayList<ArrayList<String>> allStems = gson.fromJson(reader, ArrayList.class);
            var docToStems = getHashMaps(allStems);
            var c = new Score();
            var queryStr = "action film producer";
            var query = Arrays.asList(queryStr.split(" "));
            System.out.println(c.rank(query, 321, docToStems));
            //System.out.println(c.termFreq("court", 1, docToStems));
        }
    }

    private static ArrayList<HashMap<String, Integer>> getHashMaps(ArrayList<ArrayList<String>> allStems) {
        var docToStems = new ArrayList<HashMap<String, Integer>>();
        for (var i = 0; i < allStems.size(); i++) {
            var wordsInDoc = allStems.get(i);
            var wordsFreq = new HashMap<String, Integer>();
            for(var word : wordsInDoc) {
                if(!wordsFreq.containsKey(word)) {
                    wordsFreq.put(word, 1);
                } else {
                    wordsFreq.put(word, wordsFreq.get(word)+1);
                }
            }
            docToStems.add(i, wordsFreq);
        }
        return docToStems;
    }
}
