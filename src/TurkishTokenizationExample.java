

import com.google.common.base.Joiner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.antlr.v4.runtime.Token;
import zemberek.core.logging.Log;
import zemberek.tokenization.TurkishTokenizer;

public class TurkishTokenizationExample {

  static TurkishTokenizer tokenizer = TurkishTokenizer.DEFAULT;

  public static void tokenIterator(String input) {
    Log.info("Low level tokenization iterator using Ant-lr Lexer.");
    Log.info("Input = " + input);
    Iterator<Token> tokenIterator = tokenizer.getTokenIterator(input);
    while (tokenIterator.hasNext()) {
      Token token = tokenIterator.next();
      Log.info("Token= " + token.getText() + " Type=" + token.getType());
    }
  }

  public static void simpleTokenization(String input) {
    Log.info("Simple tokenization returns a list of token strings.");
    TurkishTokenizer tokenizer = TurkishTokenizer.DEFAULT;
    Log.info("Input = " + input);
    Log.info("Tokenization list = " +
        Joiner.on("|").join(tokenizer.tokenizeToStrings(input)));
  }

  public static void main(String[] args) {
    try {
      File f = new File("sentences.txt");
      BufferedReader b = new BufferedReader(new FileReader(f));
      String readLine = "";
      System.out.println("Reading file using Buffered Reader");
      while ((readLine = b.readLine()) != null) {
        tokenIterator(readLine);
        Log.info();
        simpleTokenization(readLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
