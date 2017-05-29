package bl;

import core.TypeMatherBuilder;
import core.api.Matcher;

/**
 * Class making processing on input business logic data
 */
public class DataProcessor {
    private Matcher matcher = new TypeMatherBuilder()
            .register(SetVariableRequest.class, this::onSetVariableRequest)
            .build();

    public void match(Object object) {
        matcher.match(object);
    }

    private void onSetVariableRequest(SetVariableRequest request) {

    }

    private void onGetVariableRequest(GetVariableRequest request) {

    }

    private void onGetReply(GetReply reply) {

    }

    private void onSetReply(SetReply reply) {

    }
}
