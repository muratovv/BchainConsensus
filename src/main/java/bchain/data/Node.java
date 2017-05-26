package bchain.data;

import java.util.Objects;

/**
 * Information about node in cluster
 */
public class Node {
    public String address;
    public String key;

    private Node(String address, String key) {
        this.address = address;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(address, node.address) &&
                Objects.equals(key, node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, key);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("address='").append(address).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Node node(String address, String key) {
        return new Node(address, key);
    }
}
