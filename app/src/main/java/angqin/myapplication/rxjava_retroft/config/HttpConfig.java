package angqin.myapplication.rxjava_retroft.config;

/**
 * 作者：${lixuebin} on 2018/1/24 16:40
 * 邮箱：2072301410@qq.com
 * tip:
 */
public class HttpConfig {
    public static final String BASEURL = "https://api.douban.com/v2/movie/";
    //豆瓣电影的Top250做测试连接
    public static final String TestURL = BASEURL +"top250?start=0&count=10";
}
