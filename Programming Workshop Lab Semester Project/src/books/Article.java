package books;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Article {
    private String articlePath;
    private ArrayList<String> stopWords;
    private ArrayList<String> positiveWords;
    private ArrayList<String> negativeWords;

    //constructor
    public Article(String articlePath, ArrayList<String> stopWords, ArrayList<String> positiveWords, ArrayList<String> negativeWords) {
        this.articlePath = articlePath;
        this.stopWords = stopWords;
        this.positiveWords = positiveWords;
        this.negativeWords = negativeWords;
    }

    //reads the original text file with all words and punctuation
    public String readOriginalText() throws FileNotFoundException{
        File file = new File(articlePath);
        Scanner sc = new Scanner(file);
        StringBuilder originalText = new StringBuilder();

        while(sc.hasNextLine()){
            originalText.append(sc.nextLine()).append(" ");//keeps original punctuation
        }
        sc.close();
        return originalText.toString();
    }

    //reads the text file into an array after removing all punctuation, symbols, and numbers
    public String[] readCleanText(String originalText){
        String cleanData = originalText.replaceAll("'","").replaceAll("[^a-zA-Z']", " ");
        String[] words = cleanData.toLowerCase().trim().split("\\s+");
        return words;
    }

    //removes words in stopwords text file from the cleaned article array
    public ArrayList<String> removeStopWords(String[] words){
        ArrayList<String> cleanWords = new ArrayList<>();
        for(String word: words){
            if(!stopWords.contains(word)){
                cleanWords.add(word);
            }
        }
        return cleanWords;
    }

    //calculates basic statistics like word count and sentence count in the array of cleaned words
    public void calculateStatistics(ArrayList<String> cleanWords, String originalText){
        System.out.println();
        System.out.println("Basic Statistics for: " + articlePath);
        int totalWords = cleanWords.size();
        System.out.println("Total words (without stop words): " + totalWords);
        int sentenceCount = originalText.split("[.!?]").length;
        System.out.println("Total sentences: " +sentenceCount);
    }

    //determines the number of positive & negative words and compares articles based off their sentiment
    public String determineSentiment(ArrayList<String> cleanedWords){
        int positiveCount = 0;
        int negativeCount = 0;

        for(String word: cleanedWords){
            if(positiveWords.contains(word)){
                positiveCount++;
            }else if(negativeWords.contains(word)){
                negativeCount++;
            }
        }
        System.out.println("Positive words count: " +positiveCount);
        System.out.println("Negative words count: " +negativeCount);


        if(positiveCount>negativeCount){
            return "Positive";
        }else if(negativeCount>positiveCount){
            return "Negative";
        }else{
            return "Neutral";
        }
    }

    //calculates the frequency that a word is present in the cleaned article
    public int calculateWordFrequency(ArrayList<String> cleanWords){
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<Integer> frequencies = new ArrayList<>();
        ArrayList<String> mostRepeatedWords = new ArrayList<>();

        Iterator<String> iterator = cleanWords.iterator();
        while(iterator.hasNext()){
            String word = iterator.next();
            int index = uniqueWords.indexOf(word);
            if(index == -1) {
                //if the word is new add to uniqueWords list
                uniqueWords.add(word);
                frequencies.add(1);// initialized frequency to 1
            }else{
                //if word is found increment its frequency
                frequencies.set(index, frequencies.get(index) + 1);
            }
        }

        //ranks the frequencies in descending order using bubble sort
        for(int i = 0; i < frequencies.size()-1; i++){
            for(int j = i+1; j < frequencies.size(); j++){
                if(frequencies.get(i) < frequencies.get(j)){
                    int tempFrequency = frequencies.get(i);
                    frequencies.set(i, frequencies.get(j));
                    frequencies.set(j, tempFrequency);

                    String tempWord = uniqueWords.get(i);
                    uniqueWords.set(i, uniqueWords.get(j));
                    uniqueWords.set(j, tempWord);
                }
            }
        }

        //gives list of repeated words
        int maxFrequency = 0;
        //String mostRepeatedWord = "";
        for(int i= 0; i < frequencies.size();i++){
            if(frequencies.get(i) > maxFrequency){
                maxFrequency = frequencies.get(i);
                mostRepeatedWords.clear();
                mostRepeatedWords.add(uniqueWords.get(i));
            } else if (frequencies.get(i)==maxFrequency) {
                mostRepeatedWords.add(uniqueWords.get(i));

            }
        }

        //print word frequencies
        System.out.println();
        System.out.println("Word Frequencies for: " + articlePath);
        for(int i = 0; i < uniqueWords.size(); i++){
            System.out.println(uniqueWords.get(i)+ ": " + frequencies.get(i));
        }

        //print most repeated words
        System.out.println("Most repeated word(s):" + mostRepeatedWords +"("+maxFrequency+")");

        //returns the number of unique words
        return uniqueWords.size();

    }

}








