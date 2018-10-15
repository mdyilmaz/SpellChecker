

import java.io.*;
import java.util.List;
import zemberek.core.logging.Log;
import zemberek.tokenization.TurkishSentenceExtractor;

public class SentenceBoundaryDetection {

  public static void simpleSentenceBoundaryDetector() {
    String input =
        "İşte Recep Tayyip Erdoğan'ın açıklamalarından satır başları:\"Hüseyin Avni Aker'i millet bahçesi yapacağız. Anneler babalar yavrular o bahçede rahatlıkla eğlenecekler. Ve niçin, yani Amerika'da İngiltere'de bu denli büyük bahçeler var da bizde olmasın. Hep beton yığınları mı olacak? Şimdi inşallah proje hazır, süratle bunları hazırlayacağız. Şehir hastanesinin adımını atıyoruz. Çalışmalarımız sürüyor. Sağlıkta, eğitimde bugüne kadar attığımız devam adımlarla bundan sonraki yolculuğumuz da devam edecek. İnşallah bölünmüş yollarla beraber çok daha ideal olan yollarımızı birbirine bağlayarak bu yolculuğumuzu devam ettireceğiz. Adalet, emniyet, enerji, tarım bütün bunlar bizim bu atacağımız adımın önemli ayakları. \"NEYMİŞ BİZİ DÖVİZE MAHKUM EDECEKLERMİŞ...\"Şimdi birileri ortalığı karıştırıyor. Neymiş? Bizi dövize mahkum edeceklermiş. Kur, faiz... E neymiş? \"Çarşamba günü saat 18:00'e kadar papazı bırakın. Bırakmadığınız takdirde yaptırımlar başlayacakmış\" İçişleri Bakanımızı, Adalet Bakanımızı yaptırımlara mahkum edeceklermiş. Ne oldu? Biz de kalktık anında Amerika'nın İçişleri ve Adalet Bakanına aynı yaptırımı kelimesi kelimesine verdik. \"KENDİLERİNİ HUKUKA DAVET EDİYORUZ\"Şimdi yeni bir şey daha çıkardılar. Demir çelik de bize yaptırım. Amerika bak, Dünya Ticaret Örgütü var. Bu örgütün kuralların içerisinde senin kuralsızlığın yok. Biz uluslararası hukuka göre hareket ederiz. Hukuk tanımama gibi bir anlayış Türkiye'de yoktur. Dolayısıyla biz kendilerini hukuka davet ediyoruz. \"SUÇ İŞLEYEN BEDELİNİ ÖDEYECEK\"Biz sizinle NATO'da beraber değil miyiz? Beraber hareket etmiyor muyuz? Stratejik ortak değil miyiz? Ne oldu şimdi size? Bu ortaklığı niye bozuyorsunuz? Suç onlarda. Senin adamın suçluysa, terör örgütleriyle ilişkisi varsa bu ülkenin de bir yargı sistemi var. Gereği neyse bunu yapar. Halkbankası genel müdür muavinini hiç suçu yokken tutuklayacaksın, siz kalkıp Halkbankamıza hiçbir ilgisi alakası yokken yaptırım uygulayacaksın. Eee? Türkiye'den de kalkıp farklı şeyler isteyeceksin. Biz burada bir mütekabiliyet anlayışıyla hareket etmiyoruz. Sen öyle yaptın da biz öyle yapıyoruz diye değil. Suç işleyen bedelini ödeyecek, olay budur. Biz bugüne kadar Amerika ile hiçbir zaman kötü olmanın gayreti içerisine girmedik. Ama eğer böyle gidecekse Arapların bir sözü var \"men dakka dukka\" Biz de onu yaparız.\"\n";
    Log.info("Paragraph = " + input);
    TurkishSentenceExtractor extractor = TurkishSentenceExtractor.DEFAULT;
    List<String> sentences = extractor.fromParagraph(input);
    Log.info("Sentences:");

    try(FileWriter fw = new FileWriter("sentences.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      for (String sentence : sentences) {
        Log.info(sentence);
        out.println(sentence);
      }
    } catch (IOException e) {
      //exception handling left as an exercise for the reader
    }

    try {
      File f = new File("sentences.txt");
      BufferedReader b = new BufferedReader(new FileReader(f));
      String readLine = "";
      System.out.println("Reading file using Buffered Reader");
      while ((readLine = b.readLine()) != null) {
        System.out.println(readLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    simpleSentenceBoundaryDetector();
  }
}
