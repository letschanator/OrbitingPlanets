import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * planet defined with a lot of properties to be used in the frame
 */
public class Planet extends JLabel{

    /**
     * stores the X value the planet should currently be at
     */
    private double currentX;

    /**
     * stores the Y value the planet should currently be at
     */
    private  double currentY;

    /**
     * stores the X value of the sun of the planet, for a planet it will be the sun, for a moon it will be it's corresponding planet
     */
    private double sunX;

    /**
     * stores the X value of the sun of the planet, for a planet it will be the sun, for a moon it will be it's corresponding planet
     */
    private double sunY;

    /**
     * radius of the circle
     */
    private double radius;

    /**
     * color the circle should be
     */
    private Color color;

    /**
     * all of the moons orbiting around this planet
     */
    ArrayList<Moon> moons;

    /**
     * the degree of rotation around it's sun
     */
    private int degree;

    /**
     * constructor for the Planet class
     * @param startX starting X value of the planet
     * @param startY starting Y value of the planet
     * @param sunX current X value of the sun
     * @param sunY current Y value of the sun
     * @param radius radius of the planet
     * @param color the color the planet should be colored
     */
    public Planet(double startX, double startY, double sunX, double sunY, double radius, Color color){
        currentX = startX;
        currentY = startY;
        this.sunX = sunX;
        this.sunY = sunY;
        this.radius = radius;
        this.color = color;
        moons = new ArrayList<>();
        degree = 0;
    }


    /**
     * getter for the radius of the planet
     * @return radius of the planet
     */
    public double getRadius() {
        return radius;
    }

    /**
     * setter for the radius of the planet
     * @param radius new radius of the planet
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * getter for the current X value of the planet
     * @return current X value of the planet
     */
    public double getCurrentX() {
        return currentX;
    }

    /**
     * setter for the current X value of the planet
     * @param currentX new X value of the planet
     */
    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    /**
     * getter for the current Y value of the planet
     * @return the current Y value of the planet
     */
    public double getCurrentY() {
        return currentY;
    }

    /**
     * setter for the current Y value of the planet
     * @param currentY new Y value for the planet
     */
    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    /**
     * getter for the Sun X value
     * @return the Sun X value
     */
    public double getSunX() {
        return sunX;
    }

    /**
     * setter for the sun X value
     * @param sunX the new sun X value
     */
    public void setSunX(double sunX) {
        this.sunX = sunX;
    }

    /**
     * getter for the sun Y value
     * @return the sun Y value
     */
    public double getSunY() {
        return sunY;
    }

    /**
     * setter for the sun Y value
     * @param sunY the new sun Y value
     */
    public void setSunY(double sunY) {
        this.sunY = sunY;
    }

    /**
     * getter for the color of the planet
     * @return the color of the planet
     */
    public Color getColor() {
        return color;
    }

    /**
     * setter for the color of the planet
     * @param color the new color of the planet
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * adds a moon to the list of moons that need to be updated when this planet moves
     * @param moon the moon to add
     */
    public void addMoon(Moon moon){
        moons.add(moon);
    }

    /**
     * getter for the moons around this planet
     * @return
     */
    public ArrayList<Moon> getMoons() {
        return moons;
    }

    /**
     * getter for the moons around this planet
     * @param moons
     */
    public void setMoons(ArrayList<Moon> moons) {
        this.moons = moons;
    }

    /**
     * updates the degrees around the sun
     * @param degree the extra degrees it was rotated
     */
    public void rotatedDegree(int degree){
        this.degree += degree;
    }

    /**
     * getter for the degree
     * @return the degree around its sun
     */
    public int getDegree() {
        return degree;
    }

    /**
     * setter for the degree
     * @param degree the new degree
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }
}
