package org.zrc.wordcount;

import java.io.*;
import java.util.*;

/**
 *
 * WordCount 给定一篇英文文档（input.txt），统计其中单词词频，将结果输入到文件中（output.txt)
 *
 */

public class WordCount {
    public static final String InputFileName = "input.txt";
    public static final String OutputFileName = "output.txt";

    // 获取资源文件目录
    private static InputStream getInputStream(final String path) {
        return Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(path);
    }

    // 读取input文档，将其中的英文单词保存到List中
    public static List<String> readWordsFile(String inputFileName) {
        List<String> wordList = new ArrayList<String>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    getInputStream(inputFileName)));
            String readline = "";
            while ((readline = reader.readLine()) != null) {
                String[] wordArray = readline.split("[^a-zA-Z]");
                for (String word : wordArray) {
                    if (word.length() != 0) {
                        wordList.add(word.toLowerCase());
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return wordList;
    }

    // 处理List中的英文单词，统计词频，结果保存到HashMap中
    public static Map<String, Integer> processWordMap(List<String> wordList){
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        for(String word: wordList){
            if(wordMap.containsKey(word)){
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        return wordMap;
    }

    // 将HashMap中的单词按词频降序排列，结果输出到output文本文件
    public static void writeWordsFile(Map<String, Integer> wordMap, String outputFileName) {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));

            List<Map.Entry<String, Integer>> wordMapList =
                    new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());

            Collections.sort(wordMapList, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {

                    // 请使用内置比较函数, 否则可能会报错, 违反使用约定
                    // 具体要满足交换律, 即返回值compare(x, y)与compare(y, x)应一致
                    return obj2.getValue().compareTo(obj1.getValue()); // 比较map值
                }
            });

            for (Map.Entry<String, Integer> entry : wordMapList) {
                StringBuffer sb = new StringBuffer();
                sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\r\n");
                writer.write(sb.toString());
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> wordList = readWordsFile(InputFileName);
        Map<String, Integer> wordMap = processWordMap(wordList);
        writeWordsFile(wordMap, OutputFileName);
    }
}
