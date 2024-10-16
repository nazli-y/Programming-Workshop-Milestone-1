package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Topic {
    private String topicName;
    private ArrayList<Article> articles;

    public Topic(String topicName, ArrayList<Article> articles){
        this.topicName = topicName;
        this.articles = articles;
    }

    public void processArticles() throws FileNotFoundException{
        System.out.println();
        System.out.println("Processing topic: " + topicName);
        System.out.println("===============================");

        Iterator<Article> iterator = articles.iterator();
        while(iterator.hasNext()){
            Article article = iterator.next();
            String originalText = article.readOriginalText();
            String[] articleText = article.readCleanText(originalText);
            ArrayList<String> cleanedArticle = article.removeStopWords(articleText);
            article.calculateStatistics(cleanedArticle, originalText);
            article.calculateWordFrequency(cleanedArticle);
        }
        System.out.println("=============================");
    }
 }
