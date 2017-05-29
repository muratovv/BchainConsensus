package bl;

import bl.data.get.GetReply;
import bl.data.get.GetVariableRequest;
import bl.data.set.SetReply;
import bl.data.set.SetVariableRequest;
import core.TypeMatherBuilder;
import core.api.Matcher;

/**
 * Class making processing on input business logic data
 */
public class DataProcessor implements ObjectProcessing {
    private Matcher matcher = new TypeMatherBuilder()
            .register(SetVariableRequest.class, this::onSetVariableRequest)
            .register(GetVariableRequest.class, this::onGetVariableRequest)
            .register(SetReply.class, this::onSetReply)
            .register(GetReply.class, this::onGetReply)
            .build();

    @Override
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
