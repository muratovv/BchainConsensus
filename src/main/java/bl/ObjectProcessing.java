package bl;

import bchain.data.ReplyMessage;

/**
 * Interface for matching input object on classes
 */
public interface ObjectProcessing {
    void match(Object object);

    /**
     * Provide answer for last matching
     */
    ReplyMessage getAnswer();
}
