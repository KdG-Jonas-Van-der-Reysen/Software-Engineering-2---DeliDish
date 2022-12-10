package be.kdg.delidish.business.domain.common;

public class Position {

    private int longitude;
    private int lattitude;

    public Position(int longitude, int lattitude) {
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

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLattitude() {
        return lattitude;
    }

    public void setLattitude(int lattitude) {
        this.lattitude = lattitude;
    }

}

//    public static double distance(double lat1, double lat2, double lon1,
//                                  double lon2, double el1, double el2) {
//
//        final int R = 6371; // Radius of the earth
//
//        double latDistance = Math.toRadians(lat2 - lat1);
//        double lonDistance = Math.toRadians(lon2 - lon1);
//        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = R * c * 1000; // convert to meters
//
//        double height = el1 - el2;
//
//        distance = Math.pow(distance, 2) + Math.pow(height, 2);
//
//        return Math.sqrt(distance);
//    }