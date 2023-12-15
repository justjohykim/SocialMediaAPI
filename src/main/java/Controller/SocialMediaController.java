package Controller;
import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     *public Javalin startAPI(){
        Javalin app = Javalin.create();
        app.post("/flights", this::postFlightHandler);
        app.put("/flights/{flight_id}", this::updateFlightHandler);
        app.get("/flights", this::getAllFlightsHandler);
        app.get("/flights/departing/{departure_city}/arriving/{arrival_city}",
                this::getAllFlightsDepartingFromCityArrivingToCityHandler);
        return app;
    }
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register",this::newAccountHandler);
        app.post("/login",this::loginHandler);
        app.post("/messages",this::newMessageHandler);
        app.get("/messages",this::getAllMessageHandler);
        app.get("/messages/{message_id}",this::getMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessagebyid);
        app.patch("/messages/{message_id}",this::updateMessageHandler);
        app.get("/accounts/{account_id}/messages",this::getMessagebyUserHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * 
     */
    private void getMessagebyUserHandler(Context context) throws JsonProcessingException{
        int account_id = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.messageofUser(account_id);
      
        context.json(messages);
    }




    private void updateMessageHandler(Context context) throws JsonProcessingException{
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        String m = message.getMessage_text();
        
        Message nullcheck = messageService.getMessagebyid(message_id);
        Message new_message = messageService.updateMessage(message_id, m);

        if (nullcheck == null || m == "" || m.length() > 255){
            context.status(400);
        }
        else{
           // Message message = messageService.updateMessage(message_id, m);
            
            context.json(mapper.writeValueAsString(new_message));
         }
     }






    private void deleteMessagebyid(Context context) throws JsonProcessingException{
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessagebyid(message_id);
        if(message == null){
            context.status(200);
        }
        else{
            messageService.deleteMessagebyid(message_id);
            context.json(message);
        }
    }




    private void getMessageHandler(Context context) throws JsonProcessingException{

        int message_id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessagebyid(message_id);
        if(message == null){
            context.status(200);
        }
        else{
            context.json(message);
        }
            

    }

    
    private void getAllMessageHandler(Context context) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }


    private void newMessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(),Message.class);
        Message newMessage = messageService.newMessage(message);
        if(newMessage != null){
            context.json(mapper.writeValueAsString(newMessage));
            
        }
        else{
            context.status(400);
        }
    }
    
    private void newAccountHandler(Context context)throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account newAccount = accountService.newAccount(account);
        if(newAccount == null){
            context.status(400);
        }
        else{
            context.json(mapper.writeValueAsString(newAccount));
        }
        
    }
    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(),Account.class);
        Account acc = accountService.login(account);
        if(acc != null){
            context.json(mapper.writeValueAsString(acc));
        }
        else{
            context.status(401);
        }
    }

}