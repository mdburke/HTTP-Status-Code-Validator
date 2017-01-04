package validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class UrlList {
    private List<String> urlList= new ArrayList<>();
    private String filePath;

    public UrlList(String filePath) {
        this.filePath = filePath;
        createList();
    }

    public List<String> getUrlList() {
        return urlList;
    }

    private void createList() {
        BufferedReader br = null;
        String baseString = null;
        String currentLine = null;

        try {
            br = new BufferedReader(new FileReader(filePath));

            if ((baseString = br.readLine()) != null) {
                while ((currentLine = br.readLine()) != null) {
                    urlList.add(baseString + currentLine);
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

//    private void
}
