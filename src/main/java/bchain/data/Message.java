package bchain.data;

/**
 * Abstract data class of message in interconnection
 */
public class Message {
    /**
     * type of message, should use {@code this.class.getSimpleName()}
     */
    public String type;

    public Message() {
        this.type = this.getClass().getSimpleName();
    }
}
