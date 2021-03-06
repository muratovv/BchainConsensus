package bl.data.set;

import bchain.data.RequestMessage;

import java.util.Objects;

/**
 * Data class for setting message to cluster
 */
public class SetVariableRequest extends RequestMessage {
    public String variable;
    public String value;

    @Override
    public int hashCode() {
        return Objects.hash(variable, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SetVariableRequest)) return false;
        SetVariableRequest request = (SetVariableRequest) o;
        return Objects.equals(variable, request.variable) &&
                Objects.equals(value, request.value);
    }
}
