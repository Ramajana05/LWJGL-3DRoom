package engine.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class FileUtils {


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