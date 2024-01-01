import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IndexFiller {
    private InvertedIndex invertedIndex = new InvertedIndex();

    public InvertedIndex getInvertedIndex() {
        return invertedIndex;
    }

    public void fillInvertedIndex(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineWords = line.split("[^a-zA-Z0-9]+");
                for (String word : lineWords) {
                    invertedIndex.addWordAndFile(word, file.getPath());
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
