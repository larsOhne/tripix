package delaunay_triangulation;



public class Triangle_dt {

    private Point_dt p1;
    private Point_dt p2;
    private Point_dt p3;

    public Triangle_dt(Point_dt p1, Point_dt p2, Point_dt p3){

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }


    public Point_dt p1() {
        return this.p1;
    }

    public Point_dt p2() {
        return this.p2;
    }

    public Point_dt p3() {
        return this.p3;
    }
}
