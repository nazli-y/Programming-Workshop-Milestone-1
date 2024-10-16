package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        String libraryPath = "C:\\Users\\Nazli\\IdeaProjects\\Programming Workshop Lab Semester Project\\Library";

        Library library = new Library(libraryPath);
        ArrayList<Topic> topics = library.getTopics();

        for(Topic topic: topics){
            topic.processArticles();
        }
    }
}
