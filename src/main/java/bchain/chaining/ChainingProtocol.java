package bchain.chaining;

import bchain.data.AckMessage;
import bchain.data.ChainMessage;
import bchain.data.RequestMessage;

/**
 * Interface of chaining protocol
 */
public interface ChainingProtocol {

    /**
     * Invoked on head node
     *
     * @param message - request intent from user
     */
    void onRequest(RequestMessage message);

    /**
     * Invoked on chain request
     *
     * @param message - contains information about request and previous node
     */
    void onChain(ChainMessage message);

    /**
     * Invoked on response(commit) to cluster
     *
     * @param message - contains information about commit
     */
    void onAck(AckMessage message);


    /**
     * Invoked when answer from node not received
     */
    void onFailure();
}
