package delaunay_triangulation;

public class Point_dt {
    private double x;
    private double y;

    public Point_dt(double x, double y) {

        this.x = x;
        this.y = y;
    }

    public Point_dt() {
        this.x = 0;
        this.y = 0;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }
}
