package Service;


import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;
    
    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
    public Account newAccount(Account account){
        return accountDAO.newAccount(account);
    }

    public Account login(Account account){
        return accountDAO.login(account);
    }
}
