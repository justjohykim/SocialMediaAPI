package DAO;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




// int message_id, int posted_by String message_text long time_posted_epoch
public class MessageDAO {
    public Message getMessage(int id){
        Connection connection = ConnectionUtil.getConnection();
        
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message acc = new Message(rs.getInt("message_id"),rs.getInt("posted_by"), rs.getString("message_text"),
                        rs.getLong("time_posed_epoch"));
                return acc;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }
}
