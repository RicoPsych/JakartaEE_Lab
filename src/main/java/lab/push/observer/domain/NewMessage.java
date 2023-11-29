package lab.push.observer.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Description of character transfer between users.
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class NewMessage {

    /**
     * Name of the character.
     */
    private String message;

    /**
     * Transferring user.
     */
    private String sender;

    /**
     * Receiving user.
     */
    private String receiver;

}
