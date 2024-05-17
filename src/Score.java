import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score {
    public ArrayList<Integer> rank(List<String> query, Integer numDocs, ArrayList<HashMap<String, Integer>> docToStems) {
        HashMap<Integer, Double> scores = new HashMap<>(); //Document id to score
        for(var i = 1; i < numDocs+1; i++) {
            var score = scoreDocument(query, i-1, docToStems);
            scores.put(i, score);
        }
        var topTen = new ArrayList<Integer>();
        for(var i = 0; i < 10; i++) {
            topTen.add(-1);
        }
        for(var i = 1; i < numDocs+1; i++) {
            var rank = 0;
            for(var documentId : topTen) {
                if(documentId != -1) { //Initial default value
                    if (scores.get(documentId) >= scores.get(i)) {
                        rank += 1;
                    }
                }
            }
            if(rank < 10) {
                topTen.set(rank, i);
            }
        }
        for(var docId : topTen) {
            System.out.println("Doc id: " + docId + ", score: " + scores.get(docId));
        }
        return topTen;
    }

    private Double scoreDocument(List<String> query, Integer docID, ArrayList<HashMap<String, Integer>> docToStems) {
        var score = 0.0;
        for(var word : query) {
            var tf = termFreq(word, docID, docToStems);
            var idf = idf(word, docToStems);
            score += tf*idf;
        }
        return score;
    }

    public Double termFreq(String curWord, Integer docID, ArrayList<HashMap<String, Integer>> docToStems) {
        var p = new Proccess();
        var stemmed = p.processText(curWord);
        if(!stemmed.isEmpty()) {
            curWord = stemmed.get(0);
        }
        else {
            return 0.0;
        }
        var wordFreq = docToStems.get(docID);
        var maxFreq = 0;
        for(var word : wordFreq.keySet()) {
            var curFreq = wordFreq.get(word);
            if(curFreq > maxFreq) {
                maxFreq = curFreq;
            }
        }
        if(wordFreq.get(curWord) == null) {
            return 0.0;
        }
        return (double) wordFreq.get(curWord)/ (double) maxFreq;
    }

    public Double idf(String word, ArrayList<HashMap<String, Integer>> docToStems) {
        var p = new Proccess();
        var stemmed = p.processText(word);
        if(!stemmed.isEmpty()) {
            word = stemmed.get(0);
        }
        else {
            return 0.0;
        }
        var numDocs = docToStems.size();
        var numDocsWithWord = 0;
        for(var doc : docToStems) {
            if(doc.containsKey(word)) {
                numDocsWithWord++;
            }
        }
        var q = numDocs/numDocsWithWord;
        return Math.log(q)/Math.log(2);
    }
}
