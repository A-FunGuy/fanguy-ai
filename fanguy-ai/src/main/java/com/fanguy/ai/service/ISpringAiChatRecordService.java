package com.fanguy.ai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fanguy.ai.entity.po.SpringAiChatRecord;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISpringAiChatRecordService extends
        IService<SpringAiChatRecord> {

    void saveRecord(String type, String conversionId);

    List<String> findConversationIds(String type);
}