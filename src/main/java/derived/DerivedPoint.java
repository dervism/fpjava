package derived;

import derived.DerivedPoint.Point.Point2D;
import derived.DerivedPoint.Point.Point3D;

import java.util.function.Function;

/** With JEP-468 Derived Record Creation

 Will provide a simpler and more expressive way of creating
 records with updated state. This feature is not available yet.

 Point finalLoc = nextLoc with {
     x *= 2;
     y *= 2;
     z *= 2;
 };

 or

 Point finalLoc = nextLoc
     with { x *= 2; }
     with { y *= 2; }
     with { z *= 2; };

 */

public class DerivedPoint {
    sealed interface Point {
        record Point2D(int x, int y) implements Point {
            Point2D withX(int newX) { return new Point2D(newX, y); }
            Point2D withY(int newY) { return new Point2D(x, newY); }
        }

        record Point3D(int x, int y, int z) implements Point {
            Point3D withX(int newX) { return new Point3D(newX, y, z); }
            Point3D withY(int newY) { return new Point3D(x, newY, z); }
            Point3D withZ(int newZ) { return new Point3D(x, y, newZ); }
        }
    }

    Point3D p = new Point3D(0, 0, 0);
    Point p2 = p.withX(p.x * 2).withY(p.y * 2).withZ(p.z * 2);

    Function<Point, Point> f = point -> switch (point) {
        case Point2D(var x, var y) -> {
            x *= 2;
            y *= 2;
            yield new Point2D(x, y);
        }
        case Point3D(var a, var b, var c) -> {
            a *= 2;
            b *= 2;
            c *= 2;
            yield new Point3D(a, b, c);
        }
    };
}
