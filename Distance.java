import java.awt.*;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Distance {
    public static void main(String[] args) {
        int LEN = 100;
        Point2D[] point2Ds = new Point2D[LEN];
        double closestD = Double.MAX_VALUE;
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < LEN; i++) {

            point2Ds[i] = new Point2D.Double(Double.parseDouble(df.format(Math.random() * 10)), Double.parseDouble(df.format(Math.random() * 10)));
        }
        System.out.println("the points are: ");
        System.out.println(Arrays.toString(point2Ds));
        Point2D[] point2DScopy = Arrays.copyOf(point2Ds, point2Ds.length);
        selectSort(point2DScopy, true);
        System.out.println("the sorted points are: ");
        System.out.println(Arrays.toString(point2DScopy));
        for (int i = 0; i < LEN; i++) {
            for (int j = i + 1; j < LEN; j++) {
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
                if (onX) {
                    if (min_index_X > cur_X || ((min_index_X == cur_X) && min_index_Y > cur_Y)) {
                        min_index = j;
                    }
                } else {
                    if (min_index_Y > cur_Y || ((min_index_Y == cur_Y) && min_index_X > cur_X)) {
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
        Point2D[] pointsCopy = Arrays.copyOf(points, points.length);
        selectSort(points, true);
        selectSort(pointsCopy, false);
        return distance(points, 0, points.length, pointsCopy);
    }

    public static Pair getClosestPair(double[][] points) {

        return null;
    }

    static boolean isInSet(Point2D[] point2DS, int low, int high, Point2D point2D) {
        return point2D.getX() >= point2DS[low].getX() && point2D.getX() <= point2DS[high - 1].getX();
    }

    public static Pair distance(Point2D[] pointsOrderOnX, int low, int high, Point2D[] pointsOrderOnY) {

        if (high - low == 2)
            return new Pair(pointsOrderOnX[low], pointsOrderOnX[high-1]);
        else if (high - low == 1) {
            return null;
        }
        Pair pairL = distance(pointsOrderOnX, low, (low + high) / 2, pointsOrderOnY);

        double dL = pairL == null ? Double.MAX_VALUE : pairL.getDistance();
        Pair pairR = distance(pointsOrderOnX, (low + high) / 2, high, pointsOrderOnY);
        double dR = pairR == null ? Double.MAX_VALUE : pairR.getDistance();
        double d = Math.min(dL, dR);

        Pair minPair = d == dL ? pairL : pairR;

        Point2D pointMiddle = pointsOrderOnX[(low + high) / 2];

        //order by Y axis
        ArrayList<Point2D> stripL = new ArrayList<>();
        ArrayList<Point2D> stripR = new ArrayList<>();

        for (Point2D point2D : pointsOrderOnY) {
            //in left set?
            if (isInSet(pointsOrderOnX, low, (low + high) / 2, point2D) && pointMiddle.getX() - point2D.getX() <= d) {
                stripL.add(point2D);
                //in right set?
            } else if (isInSet(pointsOrderOnX, (low + high) / 2, high, point2D) && point2D.getX() - pointMiddle.getX() <= d) {
                stripR.add(point2D);
            }
        }
        //index of a point in stripR
        int r = 0;

        for (Point2D point2D : stripL) {
            while (r < stripR.size() && stripR.get(r).getY() <= point2D.getY() - d)
                r++;

            int r1 = r;
            while (r1 < stripR.size() && point2D.getY() - stripR.get(r1).getY() <= d) {
                double curD = distance(point2D, stripR.get(r1));
                if (curD < d)
                {d = curD;
                    minPair = new Pair(point2D, stripR.get(r1));
                }
                r1 += 1;
            }
        }


        return minPair;

    }

    public static double distance(Point2D p1, Point2D p2) {
        return p1.distance(p2);
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
