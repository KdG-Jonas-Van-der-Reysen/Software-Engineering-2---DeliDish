package be.kdg.delidish.business.domain.common;

public class Position {

    private int longitude;
    private int lattitude;

    // Method to calculate distance in km between two positions
    public double calculateDistance(Position position) {
        // Don't worry, I didn't write this myself either. Here's why it (apparently) works:
        // https://stackoverflow.com/a/58276194

        final double r2d = 180.0D / 3.141592653589793D;
        final double d2r = 3.141592653589793D / 180.0D;
        final double d2km = 111189.57696D * r2d;

        double x = lattitude * d2r;
        double y = position.getLattitude() * d2r;

        double meters = Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (longitude - position.getLongitude()))) * d2km;
        double km = meters / 1000;

        return km;
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