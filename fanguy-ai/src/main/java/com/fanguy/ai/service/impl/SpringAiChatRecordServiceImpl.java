package com.fanguy.ai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fanguy.ai.entity.po.SpringAiChatRecord;
import com.fanguy.ai.mapper.SpringAiChatRecordMapper;
import com.fanguy.ai.service.ISpringAiChatRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpringAiChatRecordServiceImpl extends
        ServiceImpl<SpringAiChatRecordMapper, SpringAiChatRecord> implements
        ISpringAiChatRecordService {

    @Override
    public void saveRecord(String type, String conversionId) {
        // 1.判断记录是否已存在
        Long count = this.lambdaQuery()
                .eq(SpringAiChatRecord::getId, conversionId)
                .count();
        if (count != null && count > 0) {
            // 记录已存在
            return;
        }

        // 2.保存记录
        SpringAiChatRecord record = new SpringAiChatRecord();
        record.setType(type);
        record.setId(conversionId);
        //TODO userId暂时写死，后续从session中获取
        record.setUserId(1L);
        //TODO title暂时写死，后续根据type获取
        record.setTitle(conversionId);
        record.setCreateTime(LocalDateTime.now());
        save(record);
    }


    @Override
    public List<String> findConversationIds(String type) {
        //TODO userId暂时写死，后续从session中获取
        return this.getBaseMapper().findConversationIds(type, 1L);
    }
}
