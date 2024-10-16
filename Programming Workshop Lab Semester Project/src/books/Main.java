package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        String libraryPath = "C:\\Users\\Nazli\\IdeaProjects\\Programming Workshop Lab Semester Project\\Library";//stores the path to the library containing the topics and articles for each topic

        Library library = new Library(libraryPath);//creates a new library given the path
        ArrayList<Topic> topics = library.getTopics();//creates an arraylist of the topics

        for(Topic topic: topics){//for each topic in the arraylist an the article is processed using the processArticles funtion in the topic class
            topic.processArticles();
        }
    }
}
