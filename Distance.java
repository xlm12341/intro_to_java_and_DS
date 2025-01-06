import java.awt.*;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Distance {
    public static void main(String[] args) {
        int LEN = 10;
        Point2D[] point2Ds =  new Point2D[LEN];
        double closestD = Double.MAX_VALUE;
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < LEN; i++) {

            point2Ds[i] = new Point2D.Double(Double.parseDouble(df.format(Math.random() * 10)), Double.parseDouble(df.format(Math.random() * 10)));
        }
        System.out.println("the points are: ");
        System.out.println(Arrays.toString(point2Ds));
        Point2D[] point2DScopy =  Arrays.copyOf(point2Ds, point2Ds.length);
        selectSort(point2DScopy);
        System.out.println("the sorted points are: ");
        System.out.println(Arrays.toString(point2DScopy));
        for (int i = 0; i< LEN; i++) {
            for (int j = i+1; j < LEN; j++) {
                double curD = point2DScopy[i].distance(point2DScopy[j]);
                if (curD < closestD) {
                    closestD = curD;
                }
            }
        }
        System.out.println("the closest distance is: ");

        System.out.println(closestD);


        System.out.println("calculated by division algorithm,the closest distance is: ");
        System.out.println(getClosestPair(point2Ds).getDistance());
    }
    public static void selectSort(Point2D[] point2DS, boolean onX) {
        for (int i = 0; i < point2DS.length; i++) {
            int min_index = i;
            for (int j = i; j < point2DS.length; j++) {
               double min_index_X = point2DS[min_index].getX();
               double min_index_Y = point2DS[min_index].getY();
               double cur_X = point2DS[j].getX();
               double cur_Y = point2DS[j].getY();
               if(onX) {
                   if (min_index_X > cur_X  || ((min_index_X == cur_X) && min_index_Y > cur_Y)) {
                       min_index = j;
                   }
               }else {
                   if (min_index_Y > cur_Y  || ((min_index_Y == cur_Y) && min_index_X > cur_X)) {
                       min_index = j;
                   }
               }
           }

           if (min_index != i) {
               Point2D tmp = point2DS[min_index];
               point2DS[min_index] = point2DS[i];
               point2DS[i] = tmp;
           }
        }
    }
    public static Pair getClosestPair(Point2D[] points) {
        Point2D[] pointsCopy =  Arrays.copyOf(points, points.length);
        selectSort(points,true);
        selectSort(pointsCopy, false);
        return distance(points, 0, points.length, pointsCopy);
    }
    public static Pair getClosestPair(double[][] points) {

        return null;
    }

    public static Pair distance(Point2D[] pointsOrderOnX, int low, int high, Point2D[] pointsOrderOnY) {

        if (high-low == 2)
            return new Pair(pointsOrderOnX[low], pointsOrderOnX[high]);
        else if (high-low == 1) {
            return null;
        }
        Pair pairL = distance(pointsOrderOnX, low, (low+high) / 2, null);

        double dL = pairL == null ? Double.MAX_VALUE : pairL.getDistance();
        Pair pairR = distance(pointsOrderOnX, (low+high) / 2, high, null);
        double dR = pairR == null ? Double.MAX_VALUE : pairR.getDistance();
        double d = Math.min(dL, dR);

        Pair minPair = d == dL ? pairL : pairR;

        Point2D pointMiddle = pointsOrderOnX[(low+high) / 2];
        ArrayList<Point> stripL = new ArrayList<>();
        ArrayList<Point> stripR = new ArrayList<>();





        return

        return null;
    }
    public static double distance(Point2D p1, Point2D p2) {
        return 0;
    }
}

class Pair {
    Point2D point1;
    Point2D point2;
    public Pair(Point2D point1, Point2D point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    public double getDistance() {
        return Point2D.distance(this.point1.getX(),
                this.point1.getY(), this.point2.getX(), this.point2.getY());
    }
}
