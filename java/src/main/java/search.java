import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import javax.print.DocFlavor;
import java.awt.image.BufferedImageFilter;
import java.io.*;
import java.util.*;

public class search {
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new FileReader("C:/Users/zrc/learngit/java-study/put.txt"));
        List<String> list=new ArrayList<String>();
        String readline= null;
        while ((readline=br.readLine())!=null){
            String[] wordArry1= readline.split("[^a-zA-Z]");
            for (String word: wordArry1){
                if ( word.length()!=0){
                    list.add(word);
                }
            }
    }

        br.close();
        Map<String,Integer> words=new TreeMap<String, Integer>();
        for (String lis :list){
            if (words.get(lis)!=null){
                words.put(lis,words.get(lis)+1);
            }
            else{
                words.put(lis,1);
            }
        }
        File f=new File("C:/Users/zrc/learngit/java-study/out.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
        SortMap(words);



}
    public static void SortMap(Map<String,Integer> oldmap){
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for ( int i = 0;i<=list .size();i++){
            System.out.println(list .get(i).getKey()+":"+list.get(i).getValue());

        }
    }


}