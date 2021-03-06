package com.nowcoder.dao;

import com.nowcoder.model.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * author:Junqson
 * create:2018/3/30 21:12
 * des:
 */
@Repository
@Mapper
public interface NewsDao {
    String TABLE_NAME = "news";
    String INSERT_FIELDS = " title,link,image,like_count,comment_count,create_date,user_id ";
    String SELECT_FIELDS = " id,title,link,image,like_count,comment_count,create_date,user_id ";

    //使用注解配置
    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{title},#{link},#{image},#{likeCount},#{commentCount},#{createDate},#{userId})"})
    int addNews(News news);

    @Select("select " + SELECT_FIELDS + " from " + TABLE_NAME + " where id = #{id}")
    News getNews(int id);

    //只修改status，ticket，expired三个字段
    @Update({"update ", TABLE_NAME, "set title = #{title},link = #{link},image = #{image},like_count = #{likeCount},comment_count = #{commentCount} where id = #{id}"})
    void updateNews(News news);

    //更新点赞数量
    @Update({"update ", TABLE_NAME, "set like_count = #{likeCount} where id = #{newsId}"})
    void updateLikeCount(@Param("newsId") int newsId, @Param("likeCount") int likeCount);



    //使用xml配置
    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset,
                                       @Param("limit") int limit);
}

