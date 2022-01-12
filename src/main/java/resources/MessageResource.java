package resources;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Message;
import resources.beans.MessageFilterBean;
import service.MessageService;

import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@BeanParam MessageFilterBean bean) {
        if (bean.getYear() > 0) {
            return messageService.getAllMessagesForYear(bean.getYear());
        }
        if (bean.getStart() >= 0 && bean.getSize() > 0) {
            return messageService.getAllMessagesPaginated(bean.getStart(), bean.getSize());
        }
        return messageService.getAllMessages();
    }

    @POST
    public Response addMessage(Message message) {
        var newMessage = messageService.addMessage(message);
        return Response.status(Response.Status.CREATED)
                .entity(newMessage)
                .build();
//        return messageService.addMessage(message);
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
        message.setId(messageId);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
        messageService.removeMessage(messageId);
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
        return messageService.getMessage(messageId);
    }


}
