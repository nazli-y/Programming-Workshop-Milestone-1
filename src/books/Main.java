package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        //stores the path to the library containing the topics and articles for each topic
        String libraryPath = "C:\\Users\\Nazli\\IdeaProjects\\Programming Workshop Lab Semester Project\\Library";

        //creates a new library given the path
        Library library = new Library(libraryPath);
        //creates an arraylist of the topics
        ArrayList<Topic> topics = library.getTopics();

        //for each topic in the arraylist an article is processed using the processArticles funtion in the topic class
        for(Topic topic: topics){
            topic.processArticles();
        }
    }
}
