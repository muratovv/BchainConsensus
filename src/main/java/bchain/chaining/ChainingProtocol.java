package bchain.chaining;

import bchain.data.AckMessage;
import bchain.data.ChainMessage;
import bchain.data.Client;
import bchain.data.RequestMessage;

/**
 * Interface of chaining protocol
 */
public interface ChainingProtocol {

    /**
     * Invoked on head node
     *
     * @param request - intent from user
     * @param client  - information about client connection
     */
    void onRequest(RequestMessage request, Client client);

    /**
     * Invoked on chain request
     *
     * @param chain - contains information about request and previous node
     */
    void onChain(ChainMessage chain);

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
