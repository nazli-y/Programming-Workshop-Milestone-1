package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class Library { //initializes libraryPath
    private String libraryPath;

    //constructor
    public Library(String libraryPath){
        this.libraryPath = libraryPath;
    }

    // retrieves the three topics from the library folder
    public ArrayList<Topic> getTopics() throws FileNotFoundException{
        ArrayList<Topic> topics = new ArrayList<>();
        ArrayList<String> stopWords = getStopWords(); //calls the method getStopWords() to retrieve the stopwords and store them in an array
        ArrayList<String> positiveWords = getPositiveWords(); //calls the method getPositiveWords() to retrieve the positivewords and store them in an array
        ArrayList<String> negativeWords = getNegativeWords(); //calls the method getNegativeWords() to retrieve the negativewords and store them in an array

        File libraryDirectory = new File(libraryPath);
        File[] topicDirectories = libraryDirectory.listFiles(File::isDirectory);

        //checks through the topics in the library directory to see if they exist and if they do
        //they assign its name and put it into articleFiles
        if(topicDirectories != null){
            for(File topicDirectory : topicDirectories){
                String topicName = topicDirectory.getName();
                ArrayList<Article> articles = new ArrayList<>();

                //gets the files in each topic and adds it to articleFiles
                File[] articleFiles = topicDirectory.listFiles((dir, name) -> name.endsWith(".txt"));
                if(articleFiles != null){
                    for(File articleFile : articleFiles){
                        articles.add(new Article(articleFile.getPath(),stopWords,positiveWords,negativeWords));
                    }
                }
                topics.add(new Topic(topicName, articles));

            }
        }
        return topics;
    }

    //retrieves the stop words file and creates an ArrayList with the words
    public ArrayList<String> getStopWords() throws FileNotFoundException{
        File file = new File(libraryPath+"\\stopwords.txt"); //uses the absolute libray path to access the stopwords.txt path
        Scanner sc = new Scanner(file);
        ArrayList<String> stopWords = new ArrayList<>();
        while (sc.hasNextLine()){
            stopWords.add(sc.nextLine());
        }
        sc.close();
        return stopWords;
    }
    //retrieves the positive words file and creates an ArrayList with the words
    public ArrayList<String> getPositiveWords() throws FileNotFoundException{
        File file = new File(libraryPath+"\\positive-words.txt");//uses the absolute libray path to access the positive-words.txt path
        Scanner sc = new Scanner(file);
        ArrayList<String> positiveWords = new ArrayList<>();
        while (sc.hasNextLine()){
            positiveWords.add(sc.nextLine());
        }
        sc.close();
        return positiveWords;
    }

    //retrieves the negative words file and creates an ArrayList with the words
    public ArrayList<String> getNegativeWords() throws FileNotFoundException{
        File file = new File(libraryPath+"\\negative-words.txt");//uses the absolute libray path to access the negative-words.txt path
        Scanner sc = new Scanner(file);
        ArrayList<String> negativeWords = new ArrayList<>();
        while (sc.hasNextLine()){
            negativeWords.add(sc.nextLine());
        }
        sc.close();
        return negativeWords;
    }

    //adds a new folder to the librarypath of this project directory
    public void addNewTopic(String newTopicName) throws IOException {
        // Create the new topic directory
        File newTopicDir = new File(libraryPath + "/" + newTopicName);
        if (newTopicDir.exists()) {
            throw new IOException("The topic already exists.");
        }

        if (!newTopicDir.mkdirs()) {
            throw new IOException("Failed to create the new topic directory.");
        }
    }

    //moves a file input from user to a new folder location
    void moveArticleToTopic(String newTopicName, String articlePath) throws IOException {
        File articleFile = new File(articlePath);
        File newLocation = new File(libraryPath + "/" + newTopicName + "/" + articleFile.getName());


        // Ensure the article is moved to the correct directory
        if (!articleFile.exists()) {
            throw new IOException("Article file does not exist: " + articlePath);
        }


        if (!articleFile.renameTo(newLocation)) {
            throw new IOException("Failed to move article: " + articlePath);
        }
    }

}
