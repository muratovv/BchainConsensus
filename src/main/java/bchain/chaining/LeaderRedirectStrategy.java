package bchain.chaining;

import bchain.data.Client;
import bchain.data.RequestMessage;

/**
 * Strategy for redirecting {@link RequestMessage} objects to leader
 */
public interface LeaderRedirectStrategy {

    void onLeaderRequest(RequestMessage request, Client client);

    void onOtherNodeRequest(RequestMessage request, Client client);

}
