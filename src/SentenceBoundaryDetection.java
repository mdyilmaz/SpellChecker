import java.io.*;
import java.util.List;
import zemberek.core.logging.Log;
import zemberek.tokenization.TurkishSentenceExtractor;


public class SentenceBoundaryDetection {


    private static void readSentences(String directory){
        // read and print sentences in the file
        try {
            File sentenceFiles = new File(directory + "sentences.txt");
            BufferedReader sentencesBuffer = new BufferedReader(new FileReader(sentenceFiles));
            String sentenceLine = "";
            while ((sentenceLine = sentencesBuffer.readLine()) != null) {
                System.out.println(sentenceLine);
            }
            sentencesBuffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void simpleSentenceBoundaryDetector(String directory, String input_path) {
        // initializations
        TurkishSentenceExtractor extractor = TurkishSentenceExtractor.DEFAULT;

        // get the name of the news files
        File folder = new File(input_path);
        File[] listOfFiles = folder.listFiles();

        String fileName = "";
        // traverse each file
        for (File file : listOfFiles) {
            if (file.isFile()) {
                fileName = file.getName();

                // read each news in the file
                try {
                    File sentenceFiles = new File(input_path + fileName);
                    BufferedReader sentencesBuffer = new BufferedReader(new FileReader(sentenceFiles));
                    String sentenceLine = "";
                    while ((sentenceLine = sentencesBuffer.readLine()) != null) {
                        List<String> sentences = extractor.fromParagraph(sentenceLine);

                        // get each sentences from the news
                        try(FileWriter fw = new FileWriter(directory + "sentences.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter out = new PrintWriter(bw))
                        {
                            for (String sentence : sentences) {
                                    // remove empty sentences
                                if(sentence.length() < 2)
                                    continue;

                                out.println(sentence);
                            }

                            out.close();
                            bw.close();
                            fw.close();
                        }
                        catch (IOException e) {
                            //exception handling left as an exercise for the reader
                        }

                    }
                    sentencesBuffer.close();
                    System.out.println(fileName + " is proccessed successfully.");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // main
    public static void main(String[] args) {
        String directory = "/Users/asus/Desktop/BIL571/SpellChecker/out/";
        String input_path = "/Users/asus/Desktop/BIL571/SpellChecker/in/news-500/";

        simpleSentenceBoundaryDetector(directory, input_path);
        // readSentences(directory);
    }
}
