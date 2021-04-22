package Yol.mise.ExternLibrary;
/**
 * @author aquilegia
 * <p>
 * The code based on hyosang(http://hyosang.kr/tc/96) and aero's blog ((http://aero.sarang.net/map/analysis.html)
 * License:  LGPL : http://www.gnu.org/copyleft/lesser.html
 */
public class GeoPoint {

    public double x;
    public double y;
    public double z;

    /**
     *
     */
    public GeoPoint() {
        super();
    }

    /**
     * @param x
     * @param y
     */
    public GeoPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    /**
     * @param x
     * @param y
     * @param y
     */
    public GeoPoint(double x, double y, double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

}
