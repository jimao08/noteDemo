package com.demo.FetchDemo;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linkang on 12/2/16.
 */
public class Fetch1 {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://www.zhihu.com/question/29759505");


            ExecutorService executorService = Executors.newCachedThreadPool();
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));

            File file = new File("tmp/");
            file.mkdir();

            String str;
            ArrayList<String> collectList = new ArrayList<>();
            while ((str = bufferedReader.readLine()) != null) {

                if (str.indexOf("jpg") > -1 || str.indexOf("jpeg") > -1 || str.indexOf("png") > -1) {
                    Pattern pattern = Pattern.compile("http.*?(jpg|png|jpeg|gif)");
                    Matcher matcher = pattern.matcher(str);
                    while (matcher.find()) {
                        String imgUrl = matcher.group();
                        executorService.execute(() -> {
                            try {
                                URL url1 = new URL(imgUrl);
                                InputStream inputStream1 = url1.openStream();

                                String filePath = "tmp/" + StringUtils.right(imgUrl, imgUrl.length() - 1 - imgUrl.lastIndexOf("/"));
                                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

                                int tmp = 0;
                                byte[] buffer = new byte[64];
                                while ((tmp = inputStream1.read(buffer)) != -1) {
                                    fileOutputStream.write(buffer, 0, tmp);
                                }

                                fileOutputStream.close();
                            } catch (Exception e) {
                                System.out.println("error:" + imgUrl);
                                e.printStackTrace();
                            }
                        });
                    }
                }
            }


//            FileUtils.writeLines(new File("tt1.txt"), collectList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
