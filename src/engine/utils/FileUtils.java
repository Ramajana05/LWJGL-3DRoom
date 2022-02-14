package engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for helper-funtions. (in combination with the Shader class)
 *
 * @author: Ramajana Skopljak
 * @version: 1.0
 */

public class FileUtils {

    /**
     * loads the path needed.
     * @param path
     * @return
     * @throws IOException
     */

    public static String loadAsString(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(path)))) {
            String line = "";
            while((line = reader.readLine()) != null){
                result.append(line).append("\n");
            }
        } catch(IOException e){
            System.err.println("Couldnt find the file at " + path);
        }
        return result.toString();
    }
}
