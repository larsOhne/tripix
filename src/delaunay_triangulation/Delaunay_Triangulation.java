package delaunay_triangulation;

import org.delaunay.algorithm.Triangulation;
import org.delaunay.model.Triangle;
import org.delaunay.model.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Delaunay_Triangulation {
    private ArrayList<Point_dt> points;

    public Delaunay_Triangulation(Point_dt[] points) {
        this.points = new ArrayList<Point_dt>(points.length);
        this.points.addAll(Arrays.asList(points));
    }

    public void insertPoint(Point_dt p) {
        this.points.add(p);
    }

    public ArrayList<Point_dt> getPoints(){
        return this.points;
    }

    public Triangle_dt[] computeTriangles() {

        Triangle_dt[] triaArray = new Triangle_dt[0];

        ArrayList<Vertex> inputVerts = new ArrayList<>(points.size());
        for (int i = 0; i < points.size(); i++) {
            inputVerts.add(new Vertex(points.get(i).x(), points.get(i).y()));
        }

        Triangulation t = new Triangulation();
        t.addAllVertices(inputVerts);

        try {
            t.triangulate();
            LinkedHashSet<Triangle> triangles_hashmap = t.getTriangles();
            ArrayList<Triangle_dt> triangles = new ArrayList<>(triangles_hashmap.size());

            for (Triangle triangle : triangles_hashmap) {
                Object[] vertices = triangle.getVertices().toArray();

                Vertex v1 = (Vertex) vertices[0];
                Vertex v2 = (Vertex) vertices[1];
                Vertex v3 = (Vertex) vertices[2];

                Point_dt p1 = new Point_dt(v1.x,v1.y);
                Point_dt p2 = new Point_dt(v2.x,v2.y);
                Point_dt p3 = new Point_dt(v3.x,v3.y);

                Triangle_dt conv_triangle = new Triangle_dt(p1,p2,p3);
                triangles.add(conv_triangle);

            }

            triaArray = new Triangle_dt[triangles.size()];
            triaArray = triangles.toArray(triaArray);


        } catch (Triangulation.InvalidVertexException e) {
            e.printStackTrace();
        }

        return triaArray;
    }
}
