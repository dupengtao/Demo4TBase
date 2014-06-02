package com.dpt.demo4tbase.net;

public class UriHelper {
	
    private static final String BASE_URL=" http://wcf.open.cnblogs.com/news/";
    
    /**
     * 分页获取新闻列表
     * http://wcf.open.cnblogs.com/news/recent/paged/{PAGEINDEX}/{PAGESIZE}
     * @return
     */
    public static String getRecentNews(int pageIndex,int pageSize) {
        StringBuilder builder = new StringBuilder(BASE_URL);
        builder.append("recent/paged/");
        builder.append(pageIndex);
        builder.append("/");
        builder.append(pageSize);
        return builder.toString();
    }

    public static String getImageUrl() {
        return "http://ipc.chotee.com/uploads/ishoulu/pic/2013/03/66151409197ba74f337521654.jpg";
    }
    public static String getImageUrl2() {
    	return "http://ipc.chotee.com/uploads/ishoulu/pic/2013/05/1385193aa98c431a807856676.jpg";
    }
	
}
