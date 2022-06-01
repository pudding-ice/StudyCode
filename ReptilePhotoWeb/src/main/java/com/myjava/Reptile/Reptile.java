package com.myjava.Reptile;

import com.myjava.domain.Photo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reptile {
    private String path;
    private String url;
    private Integer inputNum;
    private Integer uid;
    public Reptile(String url, String path,Integer inputNum,Integer uid) {
        this.path = path;
        this.url = url;
        this.inputNum = inputNum;
        this.uid = uid;
    }

    public ArrayList<Photo> getImgSrcFromHtml(String html,Integer i) throws IOException {
        ArrayList<Photo> imgSrcList = new ArrayList<>();
        String u = url +i+".htm";
        byte[] body = Jsoup.connect(u).execute().bodyAsBytes();
        InputStream in = new ByteArrayInputStream(body);
        Document document = Jsoup.parse(in, "GBK", html);
        Elements elements = document.select("div [class=list]").select("li");
        for (Element imgage_info:elements) {
            Photo photo = new Photo();
            Elements b = imgage_info.select("b");
            Elements imgs = imgage_info.select("img");
            String src = imgs.attr("src");
            photo.setP_url(src);
            String img_name = b.text();
            photo.setP_name(img_name);
            imgSrcList.add(photo);
        }
        return imgSrcList;
    }
    public String getHtml(Integer inputNum){
        String html="";
        String u = url +inputNum+".htm";
        try{
            html = Jsoup.connect(u).execute().body();
        } catch (IOException e) {
            System.out.println("获取失败");
            e.printStackTrace();
        }
        return html;
    }

    /**
     *
     * @Title: getBytesFromInputStream
     * @Description: InputStream流转换byte[]
     * @param @param  inputStream
     * @param @return byte[]
     * @return byte[] 返回类型
     * @throws
     */
    public static byte[] getBytesFromInputStream(InputStream inputStream) {
        byte[] bs = null;
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(); //
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            bs = arrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bs;
    }

    public void downLoadImg(List<Photo> list) {
        //根据网址  把图片下载到指定的文件夹
        // 1 URL IO流
        URL newUrl = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        byte[] bs= null;
        Iterator<Photo> it = list.iterator();
        while (it.hasNext()) {
            Photo photo = it.next();
            try {
                // 给我一个图片的地址/网址
                newUrl = new URL(photo.getP_url());
                //打开连接 强制转换成http的连接对象 http ftp socket
                httpURLConnection = (HttpURLConnection)newUrl.openConnection();
                //获取返回的数据(IO流)
                inputStream = httpURLConnection.getInputStream();// 获取返回流 输入流
                // 把输入流转换成字节 二进制
                // bs里面就是图片的二进制数据
                bs = getBytesFromInputStream(inputStream);
                // 写文件  文件的名字  路径+文件的名字
                String[] split = photo.getP_url().split("/");
                photo.setP_url(this.path+split[split.length-1]);
                String s = "C:/Users/Pudding/Desktop/ReptilePhotoWeb/web"+photo.getP_url();
                // 输出到文件里面
                fileOutputStream = new FileOutputStream(new File(s));
                //写入文件
                fileOutputStream.write(bs);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<Photo> run() throws IOException {
        ArrayList<Photo> total = new ArrayList<>();
        ArrayList<Photo> lists = null;
        for (Integer i = 1; i <= inputNum; i++) {
            String html = getHtml(i);
            lists = getImgSrcFromHtml(html,i);
            downLoadImg(lists);
            //给photo集合添加UID
            Iterator<Photo> it = lists.iterator();
            while (it.hasNext()) {
                Photo next = it.next();
                next.setU_id(this.uid);
            }
            total.addAll(lists);
        }
        return total ;
    }
}
