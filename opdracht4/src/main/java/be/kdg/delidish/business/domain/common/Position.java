package be.kdg.delidish.business.domain.common;

public class Position {

    private double longitude;
    private double lattitude;

    public Position(double longitude, double lattitude) {
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    // Method to calculate distance in km between two positions
    public double calculateDistance(Position position) {
        // Don't worry, I didn't write this myself either. Here's why it (apparently) works:
        // https://stackoverflow.com/a/58276194
        double earthRadiusKm = 6371.0;

        double dLat = Math.toRadians(position.lattitude - lattitude);
        double dLon = Math.toRadians(position.longitude- longitude);

        double rlat1 = Math.toRadians(lattitude);
        double rlat2 = Math.toRadians(position.lattitude);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(rlat1) * Math.cos(rlat2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadiusKm * c;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}