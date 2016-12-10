package com.news.db;

import com.news.App;
import com.news.R;
import com.news.common.ApiConstants;
import com.news.common.Constants;
import com.news.db.gen.NewsChannelDao;
import com.news.utils.MyUtils;

import org.greenrobot.greendao.query.Query;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class NewsChannelManager {
    /**
     * 首次打开程序初始化db
     */
    public static void initDb(){
        if (!MyUtils.getBoolean(Constants.INIT_DB,false)){
            NewsChannelDao dao = App.getNewsChannelDao();
            List<String> channelName = Arrays.asList(App.getAppContext().getResources()
                    .getStringArray(R.array.news_channel_name));
            List<String> channelId = Arrays.asList(App.getAppContext().getResources()
                    .getStringArray(R.array.news_channel_id));
            for (int i=0;i<channelName.size();i++){
                NewsChannel newsChannel=new NewsChannel
                        (channelName.get(i),channelId.get(i), ApiConstants.getType(channelId.get(i)),i<=5,i,i==0);
                dao.insert(newsChannel);
            }
        }
    }
    public static List<NewsChannel> loadNewsChannelsMine() {
        Query<NewsChannel> newsChannelTableQuery = App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelSelect.eq(true))
                .orderAsc(NewsChannelDao.Properties.NewsChannelIndex).build();
        return newsChannelTableQuery.list();
    }

    public static List<NewsChannel> loadNewsChannelsMore() {
        Query<NewsChannel> newsChannelTableQuery = App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelSelect.eq(false))
                .orderAsc(NewsChannelDao.Properties.NewsChannelIndex).build();
        return newsChannelTableQuery.list();
    }

    public static NewsChannel loadNewsChannel(int position) {
        return App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelIndex.eq(position)).build().unique();
    }

    public static void update(NewsChannel newsChannelTable) {
        App.getNewsChannelDao().update(newsChannelTable);
    }

    public static List<NewsChannel> loadNewsChannelsWithin(int from, int to) {
        Query<NewsChannel> newsChannelTableQuery = App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelIndex
                        .between(from, to)).build();
        return newsChannelTableQuery.list();
    }

    public static List<NewsChannel> loadNewsChannelsIndexGt(int channelIndex) {
        Query<NewsChannel> newsChannelTableQuery = App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelIndex
                        .gt(channelIndex)).build();
        return newsChannelTableQuery.list();
    }

    public static List<NewsChannel> loadNewsChannelsIndexLtAndIsUnselect(int channelIndex) {
        Query<NewsChannel> newsChannelTableQuery = App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelIndex.lt(channelIndex),
                        NewsChannelDao.Properties.NewsChannelSelect.eq(false)).build();
        return newsChannelTableQuery.list();
    }

    public static int getAllSize() {
        return App.getNewsChannelDao().loadAll().size();
    }

    public static int getNewsChannelSelectSize() {
        return (int) App.getNewsChannelDao().queryBuilder()
                .where(NewsChannelDao.Properties.NewsChannelSelect.eq(true))
                .buildCount().count();
    }
}
