package bchain.data;

/**
 * Object that may be transferred via sockets
 */
public interface Transportable {
    /**
     * Convert message for transporting
     */
    String toTransport();
}
