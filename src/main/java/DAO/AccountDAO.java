package DAO;
import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountDAO {
    public Account newAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        if(account.getPassword().length() < 4 || account.getUsername() == ""){
            return null;
        }
        else{
            try {
                //Write SQL logic here
                String sql = "INSERT INTO account(username, password) VALUES(?,?);";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
                preparedStatement.executeUpdate();
                ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                if(pkeyResultSet.next()){
                    int generated_account_id = (int) pkeyResultSet.getLong(1);
                    return new Account(generated_account_id,account.getUsername(),account.getPassword());
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;

    }
}

    public Account login(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account acc = new Account(rs.getInt("account_id"),
                        rs.getString("username"), rs.getString("password"));
                return acc;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
    }
    return null;
}







}