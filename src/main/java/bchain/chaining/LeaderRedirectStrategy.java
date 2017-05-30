package bchain.chaining;

import bchain.data.RequestMessage;

/**
 * Strategy for redirecting {@link RequestMessage} objects to leader
 */
public interface LeaderRedirectStrategy {

    void onLeaderRequest(RequestMessage request);

    void onOtherNodeRequest(RequestMessage request);

}
