package com.leyou.common.vo;

import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Getter
@Setter
public class LyExceptionResult {

    private String msg;
    private String datetime;

    private String formatDate(){
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fm.format(new Date());
    }
    public LyExceptionResult() {
        this.datetime = this.formatDate();
    }
    public LyExceptionResult(String msg){
        this.msg = msg;
        this.datetime = this.formatDate();
    }
    public LyExceptionResult(String msg, String datetime) {
        this.msg = msg;
        this.datetime = datetime;
    }

    //时间处理
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(dt.format(now));

        System.out.println(System.currentTimeMillis());


        //获取的是没有时区影响的时间

        // 构建一个时间
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 打印当前时间格式化结果
        System.out.println(fm.format(new Date()));
        System.out.println(fm.format(new Date(System.currentTimeMillis())));
//        System.out.println(fm.parse(time));
        //字符串转时间
        String tt = "2019-12-20 22:45:34";
        try {
            Date parse = fm.parse(tt);
            //获取时间戳到毫秒
            System.out.println(parse.getTime());
//            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
