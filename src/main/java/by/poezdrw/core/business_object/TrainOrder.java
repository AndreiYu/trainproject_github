package by.poezdrw.core.business_object;

import by.poezdrw.helper.DateManager;

import java.util.Calendar;

public class TrainOrder implements BaseBusinessObject{

    private String departurePoint;
    private String destinationPoint;
    private String trainNumber;
    private Calendar departureDate;
    private String carriageNumber;
    private String placeNumber;

    public TrainOrder() {
    }


    public TrainOrder(String departurePoint, String destinationPoint, Calendar departureDate) {
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.departureDate = departureDate;
    }

    public TrainOrder(String departurePoint, String destinationPoint, String trainNumber, Calendar departureDate) {
        this.departurePoint = departurePoint;
        this.destinationPoint = destinationPoint;
        this.trainNumber = trainNumber;
        this.departureDate = departureDate;
    }

    // to create businessObject from loaded data
    public TrainOrder(Object[] data) {
        if (data.length == 6) {
            this.departurePoint = (data[0].toString() != "") ? data[0].toString() : null;
            this.destinationPoint = (data[1].toString() != "") ? data[1].toString() : null;
            if (data[2].toString() != "") {
                setDepartureDate(data[2].toString());
            }
            else {
                this.departureDate = null;
            }
            this.trainNumber = (!data[3].toString().equals("")) ? data[3].toString() : null;
            this.carriageNumber = (!data[4].toString().equals("")) ? data[4].toString() : null;
            this.placeNumber = (!data[5].toString().equals("")) ? data[5].toString() : null;
        }
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public TrainOrder setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
        return this;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public TrainOrder setDestinationPoint(String destinationPoint) {
        this.destinationPoint = destinationPoint;
        return this;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public TrainOrder setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
        return this;
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public String getStringDepartureDate() {
        return DateManager.getStringFromDate(departureDate);
    }

    public TrainOrder setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public TrainOrder setDepartureDate(String departureDate) {
        this.departureDate = DateManager.getDateFromString(departureDate);
        return this;
    }

    public String getCarriageNumber() {
        return carriageNumber;
    }

    public TrainOrder setCarriageNumber(String carriageNumber) {
        this.carriageNumber = carriageNumber;
        return this;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public TrainOrder setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof TrainOrder)) return false;

        TrainOrder that = (TrainOrder) o;

        if (departurePoint != null ? !departurePoint.equals(that.departurePoint) : that.departurePoint != null)
            return false;
        if (destinationPoint != null ? !destinationPoint.equals(that.destinationPoint) : that.destinationPoint != null)
            return false;
        if (trainNumber != null ? !trainNumber.equals(that.trainNumber) : that.trainNumber != null) return false;
        return departureDate != null ? departureDate.equals(that.departureDate) : that.departureDate == null;
    }

    @Override
    public int hashCode() {
        int result = departurePoint != null ? departurePoint.hashCode() : 0;
        result = 31 * result + (destinationPoint != null ? destinationPoint.hashCode() : 0);
        result = 31 * result + (trainNumber != null ? trainNumber.hashCode() : 0);
        result = 31 * result + (departureDate != null ? departureDate.hashCode() : 0);
        return result;
    }

}
