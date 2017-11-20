package poezdrw.cucumber;

import by.poezdrw.core.business_object.TrainOrder;
import cucumber.api.Transformer;

/**
 * Class for transforming String to Object provided
 */
public class CucumberTrainOrderTransformer extends Transformer<TrainOrder> {


    @Override
    public TrainOrder transform(String value) {
        TrainOrder order = new TrainOrder();
        return order.setTrainNumber(value);
    }
}
