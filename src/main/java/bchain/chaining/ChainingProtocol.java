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
     * @param request - intent from user
     */
    void onRequest(RequestMessage request);

    /**
     * Invoked on chain request
     *
     * @param chain - contains information about request and previous node
     */
    void onChain(ChainMessage chain);

    /**
     * Invoked on response(commit) to cluster
     *
     * @param ack - contains information about commit
     */
    void onAck(AckMessage ack);


    /**
     * Invoked when {@link AckMessage} from node not received
     */
    void onFailure();
}
