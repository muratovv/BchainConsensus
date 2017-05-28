package bchain.chaining;

import bchain.data.RequestMessage;

/**
 * @author @muratovv
 * @date 28.05.17
 */
public interface LeaderRedirectStrategy {

    void onLeaderRequest(RequestMessage message);

    void onOtherNodeRequest(RequestMessage message);

}
