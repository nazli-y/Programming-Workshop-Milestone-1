package books;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;

public class Main {
    public static void main(String[]args) throws FileNotFoundException{
        //stores the path to the library containing the topics and articles for each topic
        String libraryPath = "C:\\Users\\Nazli\\IdeaProjects\\Programming Workshop Lab Semester Project\\Library";

        //creates a new library given the path
        Library library = new Library(libraryPath);
        //creates an arraylist of the topics
        ArrayList<Topic> topics = library.getTopics();

        Scanner reader = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.println("===================================");
            System.out.println("Choose a number to retrieve more information on");
            System.out.println("1. Topic 1");
            System.out.println("2. Topic 2");
            System.out.println("3. Topic 3");
            System.out.println("4. Add a new topic");
            System.out.println("5. Add a new file to topic of choice");
            System.out.println("6. Process topic of choice");
            System.out.println("7. Exit");
            System.out.println("===================================");

            int input = reader.nextInt();
            reader.nextLine();

            switch(input){
                case 1:
                    topics.get(input-1).processArticles();
                    break;

                case 2:
                    topics.get(input-1).processArticles();
                    break;

                case 3:
                    topics.get(input-1).processArticles();
                    break;

                case 4:
                    System.out.println("Enter the name of the new topic:");
                    String newTopicName = reader.nextLine();
                    try{
                        //Add the new topic and articles
                        library.addNewTopic(newTopicName);
                        System.out.println("Topic added successfully.");
                    }catch(IOException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Enter the source path of the article:");
                    String sourcePath = reader.nextLine();
                    System.out.println("Enter the name of the target topic:");
                    String topicName = reader.nextLine();

                    try{
                        //Call the moveArticleToTopic method from the Library class
                        library.moveArticleToTopic(topicName, sourcePath);
                        System.out.println("Article moved successfully to the topic: "+ topicName);
                    }catch(IOException e){
                            System.err.println("An error occured while moving the article: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Topics currently in the library are:");
                    for(Topic topic : library.getTopics()){
                        System.out.println(topic.getName());
                    }
                    System.out.println("Pick a topic from the list to process:");
                    String topicChoice = reader.nextLine();

                    Topic selectedTopic = null;
                    for(Topic topic : topics){
                        if (topic.getName().equalsIgnoreCase(topicChoice)){
                            selectedTopic = topic;
                            break;
                        }
                    }

                    if(selectedTopic == null){
                        System.out.println("Invalid topic name. Please try again.");
                    }else{
                        selectedTopic.processArticles();
                        System.out.println("Topic " + selectedTopic.getName() + "processed successfully.");
                    }
                    break;

                case 7:
                    System.out.println("You have exited the program.");
                    exit=true;
                    break;

                default:
                    System.out.println("Try entering a number choice again");
            }
        }
    }
}
