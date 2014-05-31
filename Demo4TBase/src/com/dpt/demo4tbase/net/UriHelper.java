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

	
}
