import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    public List<String> getDictionary(String dictFile) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dictFile)));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }
}
