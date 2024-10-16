package books;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Article {
    private String articlePath;
    private ArrayList<String> stopWords;

    //constructor
    public Article(String articlePath, ArrayList<String> stopWords) {
        this.articlePath = articlePath;
        this.stopWords = stopWords;
    }
    
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


    public String[] readCleanText(String originalText){
        String cleanData = originalText.replaceAll("'","").replaceAll("[^a-zA-Z']", " ");
        String[] words = cleanData.toLowerCase().trim().split("\\s+");
        return words;
    }


    public ArrayList<String> removeStopWords(String[] words){
        ArrayList<String> cleanWords = new ArrayList<>();
        for(String word: words){
            if(!stopWords.contains(word)){
                cleanWords.add(word);
            }
        }
        return cleanWords;
    }


    public void calculateStatistics(ArrayList<String> cleanWords, String originalText){
        System.out.println();
        System.out.println("Basic Statistics for: " + articlePath);
        int totalWords = cleanWords.size();
        System.out.println("Total words (without stop words): " + totalWords);
        int sentenceCount = originalText.split("[.!?]").length;
        System.out.println("Total sentences: " +sentenceCount);
    }


    public void calculateWordFrequency(ArrayList<String> cleanWords){
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<Integer> frequencies = new ArrayList<>();

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

        //ranking in descending order using bubble sort
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

        //print word frequencies
        System.out.println();
        System.out.println("Word Frequencies for: " + articlePath);
        for(int i = 0; i < uniqueWords.size(); i++){
            System.out.println(uniqueWords.get(i)+ ": " + frequencies.get(i));
        }
    }
}








