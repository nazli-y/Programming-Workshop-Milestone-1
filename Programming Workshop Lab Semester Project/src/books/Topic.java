package books;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Topic {
    private String topicName;
    private ArrayList<Article> articles;

    //constructor
    public Topic(String topicName, ArrayList<Article> articles){
        this.topicName = topicName;
        this.articles = articles;
    }

    //goes through each of the articles present using the information gained from the Library.java class
    public void processArticles() throws FileNotFoundException{
        System.out.println();
        System.out.println("Processing topic: " + topicName);
        System.out.println("===============================");
        String articleWithRichestVocab = null;
        int maxUniqueWords = 0;
        int articleNumber = 1;

        //Iterate through the articles using the iterator
        Iterator<Article> iterator = articles.iterator();
        while(iterator.hasNext()){
            Article article = iterator.next();
            String articleLabel = "Article" + articleNumber++;
            String originalText = article.readOriginalText();
            String[] articleText = article.readCleanText(originalText);
            ArrayList<String> cleanedArticle = article.removeStopWords(articleText);
            article.calculateStatistics(cleanedArticle, originalText);

            int uniqueWordCount = article.calculateWordFrequency(cleanedArticle);

            String sentiment = article.determineSentiment(cleanedArticle);

            if(uniqueWordCount>maxUniqueWords){
                maxUniqueWords = uniqueWordCount;
                articleWithRichestVocab = articleLabel;
            }
            //prints the heuristics of each article
            System.out.println("=============================");
            System.out.println("Vocabulary Heuristics for " + articleLabel+ ":");
            System.out.println("Unique word count: " + uniqueWordCount);
            System.out.println("Sentiment: " + sentiment);
            System.out.println("=============================");
        }

        System.out.println("=============================");
        System.out.println("Summary for " +topicName+ ":");
        System.out.println("Article with Richest Vocabulary: "+ articleWithRichestVocab);
        System.out.println("Max unique words: " + maxUniqueWords);
        System.out.println("=============================");
    }
 }
