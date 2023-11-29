package lab.push.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lab.push.context.PushMessageContext;
import lab.push.dto.Message;
import lab.push.observer.domain.NewMessage;
import lab.push.observer.event.NewMessageEvent;
import lombok.extern.java.Log;

import java.util.logging.Level;

/**
 * Observer implementation for transferring character between users. It will notify receiving user.
 */
@ApplicationScoped
@Log
public class NewMessageObserver {

    /**
     * Context for sending push messages.
     */
    private final PushMessageContext pushMessageContext;

    /**
     * @param pushMessageContext context for sending push messages
     */
    @Inject
    public NewMessageObserver(PushMessageContext pushMessageContext) {
        this.pushMessageContext = pushMessageContext;
    }

    /**
     * Observer method for transferring character. Called automatically when {@link NewMessageEvent} is fired.
     *
     * @param msg transfer description
     */
    public void processCharacterTransfer(@Observes @NewMessageEvent NewMessage msg) {
        if(msg.getReceiver() == null){
            pushMessageContext.notifyAll(Message.builder()
                .from(msg.getSender())
                .content(msg.getMessage())
                .build());
        }
        else{
            pushMessageContext.notifyUser(Message.builder()
                .from(msg.getSender())
                .content(msg.getMessage())
                .build(), msg.getReceiver());
        }

    }

}
