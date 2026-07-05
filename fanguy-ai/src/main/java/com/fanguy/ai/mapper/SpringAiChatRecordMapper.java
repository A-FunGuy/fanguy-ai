package com.fanguy.ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fanguy.ai.entity.po.SpringAiChatRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpringAiChatRecordMapper extends
        BaseMapper<SpringAiChatRecord> {

    @Select("SELECT id FROM spring_ai_chat_record WHERE type = #{type} and " +
            "user_id = #{userId} ORDER BY create_time DESC")
    List<String> findConversationIds(@Param("type") String type,
    @Param("userId") Long userId);
}
