package com.kpi.project.first.service.dao;

import com.kpi.project.first.service.entity.Message;
import com.kpi.project.first.service.service.vm.ChatCheckEntity;
import com.kpi.project.first.service.service.vm.ChatIdsVM;

import java.util.List;

public interface ChatDao {

    //Chats

    ChatIdsVM createChatsByEventId(int eventId);

    void deleteChatsByEventId(int eventId);

    ChatIdsVM findChatsIdsByEventId(int eventId);

    //Messages

    Message insertMessage(Message message);

    List<Message> findMessagesByChatId(int chatId);

    //Permissions

    ChatCheckEntity canJoinChat(int userId, int chatId);
}
