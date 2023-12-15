package Service;


import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    public Message newMessage(Message n_message){
        return messageDAO.newMessage(n_message);
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessagebyid(int id){
        return messageDAO.getMessagebyid(id);
    }

    public Message deleteMessagebyid(int id){
        Message message = messageDAO.getMessagebyid(id);
        if(message == null){
            return null;
        }
        return messageDAO.deleteMessagebyid(id);
    }


    public Message updateMessage(int id, String text){
        Message message = messageDAO.getMessagebyid(id);
        if(message == null){
            return null;
        }
        messageDAO.updateMessage(id, text);
        return messageDAO.getMessagebyid(id);
        
    }

    public List<Message> messageofUser(int id){
        return messageDAO.messageofUser(id);
    }

}
