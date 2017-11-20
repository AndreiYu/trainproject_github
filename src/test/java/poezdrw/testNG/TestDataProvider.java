package poezdrw.testNG;

import by.poezdrw.core.business_object.BaseBusinessObject;
import by.poezdrw.core.business_object.TrainOrder;
import by.poezdrw.core.business_object.User;
import by.poezdrw.helper.DateManager;
import by.poezdrw.util.PropertiesLoader;
import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.csv.CsvConfiguration;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.schema.Schema;
import org.apache.metamodel.schema.Table;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import poezdrw.testNG.testbase.AbstractTest;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//========================DONE=====================//
public class TestDataProvider {

    //TODO: check if works for Linux
    private static File userCsvFile = new File("src/test/resources/data_for_tests_user.csv");
    private static File trainOrderCsvFile = new File("src/test/resources/data_for_tests_train-order.csv");

    public static final String FEEDBACK_TEXT = "Very well job";
    static final String NEWLASTNAME = "Petrov";

    static final String NUMBER_ADULT_PASSENGERS = "1";

    static final String INTERNET_BANKING_CHECK_TEXT = "Беларусбанк";
    static final String ERIP_CHECK_TEXT = "Ваш код платежа в системе «Расчёт»";
    static final String CARD_CHECK_TEXT = "Ввод данных банковской карты";


    //for CheckOrderPage
    static final String TRAIN_STRING_RU = "Поезд:";
    static final String FROM_STRING_RU = "Станция отправления:";
    static final String TO_STRING_RU = "Станция назначения:";
    static final String DATE_STRING_RU = "Отправление:";
    static final String CARRIAGE_STRING_RU = "Номер вагона:";
    static final String CARRIAGE_TYPE_STRING_RU = "Тип вагона, класс обслуживания:";
    static final String PLACE_STRING_RU = "Выбор мест:";


// =============== Getting valid BusinessObjects for tests ==================== //

    /**
     * Method for generating valid user for BookingOrder tests
     *
     * @return
     */
    static User getValidUserForBooking() {

        User user = (User) getValidBusinessObjectTestData("All", BusinessObjectType.USER)
                .stream()
                .filter(a -> ((User) a).getLogin() != null && !((User) a).getLogin().isEmpty())
                .filter(a -> ((User) a).getPassword() != null && !((User) a).getPassword().isEmpty())
                .filter(a -> ((User) a).getFirstName() != null && !((User) a).getFirstName().isEmpty())
                .filter(a -> ((User) a).getLastName() != null && !((User) a).getLastName().isEmpty())
                .filter(a -> ((User) a).getPassport() != null && !((User) a).getPassport().isEmpty())
                .findAny()
                .orElse(null);

        return user;
    }

    /**
     * Method for generating valid TrainOrder for BookingOrder tests
     *
     * @return
     */
    public static TrainOrder getValidOrderForBooking() {

        TrainOrder trainOrder = (TrainOrder) getValidBusinessObjectTestData("All", BusinessObjectType.TRAIN_ORDER)
                .stream()
                .filter(a -> ((TrainOrder) a).getDeparturePoint() != null && !((TrainOrder) a).getDeparturePoint().isEmpty())
                .filter(a -> ((TrainOrder) a).getDestinationPoint() != null && !((TrainOrder) a).getDestinationPoint().isEmpty())
                .filter(a -> ((TrainOrder) a).getStringDepartureDate() != null && !((TrainOrder) a).getStringDepartureDate().isEmpty())
                .findAny()
                .orElse(null);

        return trainOrder;
    }


//============ Dataproviders ==================================//

    @DataProvider(name = "ValidTrainOrders")
    public static Object[] gatherValidDataForTrainOrder(ITestContext testContext) {
        return getValidBusinessObjectTestData(testContext.getName(), BusinessObjectType.TRAIN_ORDER).toArray();
    }

//=========== Loader of data from .csv =========================//

    public static List<BaseBusinessObject> getValidBusinessObjectTestData(String testName, BusinessObjectType type) {

        CsvConfiguration conf = new CsvConfiguration();
        List<BaseBusinessObject> businessObjects = new ArrayList<>();

        if (type == BusinessObjectType.TRAIN_ORDER) {
            DataContext csvContext = DataContextFactory.createCsvDataContext(trainOrderCsvFile);
            Schema schema = csvContext.getDefaultSchema();
            Table[] tables = schema.getTables();
            Table table = tables[0]; // a representation of the csv file name including extension
            DataSet dataSet = csvContext.query()
                    .from(table)
                    .select("from", "to", "departure_date", "id_number", "carriage_number", "place_number")
                        .where("run").eq("y")
                        .and("is_valid").eq("y")
                        .and("test_name").eq(testName)
                        .or("test_name").eq("All")
                    .execute();
            List<Object[]> values = dataSet.toObjectArrays();
            values.forEach(a -> businessObjects.add(new TrainOrder(a)));
        }
        if (type == BusinessObjectType.USER) {
            DataContext csvContext = DataContextFactory.createCsvDataContext(userCsvFile);
            Schema schema = csvContext.getDefaultSchema();
            Table[] tables = schema.getTables();
            Table table = tables[0]; // a representation of the csv file name including extension
            DataSet dataSet = csvContext.query()
                    .from(table)
                    .select("login", "password", "last_name", "first_name", "id_number")
                        .where("run").eq("y")
                        .and("is_valid").eq("y")
                        .and("test_name").eq(testName)
                        .or("test_name").eq("All")
                    .execute();
            List<Object[]> values = dataSet.toObjectArrays();
            values.forEach(a -> businessObjects.add(new User(a)));
        }

        return businessObjects;
    }


//  ======================== Helper nested classes for test dataprovider purposes ================= //

    /**
     * To choose payment method on last page
     */
    enum PaymentType {

        IB, ERIP, BANK_CARD

    }

    enum BusinessObjectType {

        USER, TRAIN_ORDER

    }



}




