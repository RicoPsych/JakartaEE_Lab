package lab.push;

import java.io.Serializable;
import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Event;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mail.Message;
import jakarta.mail.MessageAware;
import jakarta.security.enterprise.SecurityContext;
import lab.album.entities.Album.Genre;
import lab.album.model.AlbumCreateModel;
import lab.push.context.PushMessageContext;
import lab.push.observer.domain.NewMessage;
import lab.push.observer.event.NewMessageEvent;
import lab.user.model.UsersModel;
import lab.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ViewScoped
@Named
@NoArgsConstructor(force = true)
public class MessageServiceView implements Serializable{
    private final PushMessageContext context;
    private final SecurityContext securityContext;
    private final Event<NewMessage> event;

    @Inject
    public MessageServiceView(PushMessageContext context,SecurityContext securityContext, @NewMessageEvent Event<NewMessage> newProfessionEvent) {
        this.context = context;
        this.securityContext = securityContext;
        this.event = newProfessionEvent;
    }

    
    @Getter
    private MessageModel msg;

    public void init() {
        if (msg == null)
        this.msg = MessageModel.builder()
        //.msg("").user("")
        .build();
    }



    public void SendMessage(){
        event.fire(NewMessage.builder()
            .sender(this.securityContext.getCallerPrincipal().getName())
            .receiver(null)
            .message(this.msg.getMsg())
                .build());
        // context.notifyAll(lab.push.dto.Message.builder()
        //     .from(this.securityContext.getCallerPrincipal().getName())
        //     .content(this.msg.getMsg())
        // .build());
    }

    public void SendMessageUser(){
        event.fire(NewMessage.builder()
            .sender(this.securityContext.getCallerPrincipal().getName())
            .receiver(msg.getUser())
            .message(this.msg.getMsg())
                .build());

        // context.notifyUser(lab.push.dto.Message.builder()
        //     .from(this.securityContext.getCallerPrincipal()
        //     .getName())
        //     .content(this.msg.getMsg())
        // .build(),this.msg.getUser());
    }
}
