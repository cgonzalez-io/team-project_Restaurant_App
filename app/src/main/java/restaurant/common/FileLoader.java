package restaurant.common;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileLoader {

    private String filename;
    private String filepath;
    private String delimiter;

    public FileLoader(String filename, String filepath, String delimiter) {
        this.filename = filename;
        this.filepath = filepath;
        this.delimiter = delimiter;
    }

    /**
     * Loads the data components.
     * @param filename filename
     * @param loadedData loadedData
     */
    public void loadDataComponents(String filename, ArrayList loadedData) {
        try (BufferedReader reader = getBufferedReader(filename)) {
            String line;
            reader.readLine(); // Skip the first line (comment line)
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] parts = line.split("\t");
                    int v = Integer.parseInt(parts[0].trim());
                    int w = Integer.parseInt(parts[1].trim());
                    loadedData.add(v, w);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a buffered reader for a file.
     * @param filename filename
     * @return buffered reader
     * @throws FileNotFoundException exception
     * @throws UnsupportedEncodingException exception
     */
    private BufferedReader getBufferedReader(String filename)
        throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
    }
}
