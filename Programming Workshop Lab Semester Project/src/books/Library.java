package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Library { //initializes libraryPath
    private String libraryPath;

    //constructor
    public Library(String libraryPath){
        this.libraryPath = libraryPath;
    }
    // retrieves the three topics 
    public ArrayList<Topic> getTopics() throws FileNotFoundException{
        ArrayList<Topic> topics = new ArrayList<>();
        ArrayList<String> stopWords = getStopWords(); //calls the method getStopWords() to retrieve the stopwords and store them in an array
        File libraryDirectory = new File(libraryPath);
        File[] topicDirectories = libraryDirectory.listFiles(File::isDirectory);

        if(topicDirectories != null){ 
            for(File topicDirectory : topicDirectories){
                String topicName = topicDirectory.getName();
                ArrayList<Article> articles = new ArrayList<>();

                File[] articleFiles = topicDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
                if(articleFiles != null){
                    for(File articleFile : articleFiles){
                        articles.add(new Article(articleFile.getPath(),stopWords));
                    }
                }
                topics.add(new Topic(topicName, articles));

            }
        }
        return topics;
    }
    //retrieves the stop words and creates an ArrayList with the words
    public ArrayList<String> getStopWords() throws FileNotFoundException{
        File file = new File(libraryPath + "\\stopwords.txt");  //uses the absolute libray path to access the stopwords.txt path
        Scanner sc = new Scanner(file);
        ArrayList<String> stopWords = new ArrayList<>();
        while (sc.hasNextLine()){
            stopWords.add(sc.nextLine());
        }
        sc.close();
        return stopWords;
    }
}
