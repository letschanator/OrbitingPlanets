import java.awt.*;

/**
 * a moon is a planet that has a moving sun as another planet
 */
public class Moon extends Planet{

    /**
     * the planet the moon is orbiting around
     */
    private Planet planet;



    /**
     * constructor for a moon
     * @param startX starting X value
     * @param startY starting Y value
     * @param planet the planet it is orbiting around
     * @param radius the radius of the moon
     * @param color the color of the moon
     */
    public Moon(double startX, double startY, Planet planet, double radius, Color color){
        super(startX,startY,planet.getCurrentX(), planet.getCurrentY(), radius,color);
        this.planet = planet;
    }

    /**
     * need to override the get sun X method to update with the planet
     * @return the current X value of the planet the moon is orbiting around
     */
    @Override
    public double getSunX(){
        return (planet.getCurrentX());
    }

    /**
     * need to override the get sun Y method to update with the planet
     * @return the current Y value of the planet the moon is orbiting around
     */
    @Override
    public double getSunY(){
        return (planet.getCurrentY());
    }
}
