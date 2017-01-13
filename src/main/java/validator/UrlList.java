package validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/* Parses test data and turns it into an ArrayList for consumption with tests. */
public class UrlList {
    private List<String> urlList= new ArrayList<>();
    private String filePath;

    public UrlList(String filePath) {
        this.filePath = filePath;
        createList();
    }

    public UrlList() {
        this(RunValidator.testDataPath);
    }

    /* Getter */
    public List<String> getUrlList() {
        return urlList;
    }

    /* Reads the passed in csv file and turns it into an ArrayList<String> */
    private void createList() {
        BufferedReader br = null;
        String baseString;
        String currentLine;

        try {
            br = new BufferedReader(new FileReader(filePath));

            if ((baseString = br.readLine()) != null) {
                while ((currentLine = br.readLine()) != null) {
                    if (!currentLine.startsWith("#")) {
                        urlList.add(baseString + currentLine);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
