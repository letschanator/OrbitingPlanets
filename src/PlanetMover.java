import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * swing worker to moving the planets to their correct places in the background
 */
public class PlanetMover extends SwingWorker<double[][], Object> {

    /**
     * the array list of planets that need to be moved
     */
    private ArrayList<Planet> planets;

    /**
     * the list of moons that need to be updated
     */
    private ArrayList<Moon> moons;

    /**
     * the frame that needs to be updated
     */
    private JFrame frame;



    /**
     * constructor for planet mover
     * @param planets array list of planets to work with
     * @param moons array list of moons to work with
     * @param frame the frame the planets and moons are on
     */
    public PlanetMover(ArrayList<Planet> planets, ArrayList<Moon> moons, JFrame frame){
        this.planets = planets;
        this.moons = moons;
        this.frame = frame;


    }

    /**
     * calculates the new X and Y values after the planets have been rotated oen degree around their sun
     * @return each planet's new X and Y values
     */
    @Override
    protected double[][] doInBackground() {
        double[][] ret = new double[planets.size() + moons.size()][2];
        int i = 0;
        for (Planet planet:planets) {
            //calculates 1 degree of rotation around the planets sun as X and Y coordinates
            ret[i][0] = planet.getSunX() + (planet.getCurrentX() - planet.getSunX()) * Math.cos(Math.PI / 180) - (planet.getCurrentY() - planet.getSunY()) * Math.sin(Math.PI / 180);
            ret[i][1] = planet.getSunY() + (planet.getCurrentX() - planet.getSunX()) * Math.sin(Math.PI/180) + (planet.getCurrentY() - planet.getSunY()) * Math.cos(Math.PI/180);
            planet.setCurrentX(ret[i][0]); // sets the planets X and Y values to the updated values
            planet.setCurrentY(ret[i][1]);
            planet.rotatedDegree(1);
            for(Moon moon: planet.getMoons()){
                moon.setCurrentX(planet.getCurrentX() +25);
                moon.setCurrentY(planet.getCurrentY());
                double degree =  moon.getDegree();
                double tempX = planet.getCurrentX() + (moon.getCurrentX() - planet.getCurrentX())*Math.cos(Math.PI /180 * degree) - (moon.getCurrentY() - planet.getCurrentY())*Math.sin(Math.PI/180 * degree);
                double tempY = planet.getCurrentY() + (moon.getCurrentX() - planet.getCurrentX())*Math.sin(Math.PI/180 * degree) + (moon.getCurrentY() - planet.getCurrentY())*Math.cos(Math.PI/180 * degree);
                moon.setCurrentX(tempX);
                moon.setCurrentY(tempY);
            }
            i++;
        }
        for(Moon moon:moons){
            moon.rotatedDegree(5);
            ret[i][0] = moon.getSunX() + (moon.getCurrentX() - moon.getSunX()) * Math.cos(Math.PI / 180 * 5) - (moon.getCurrentY() - moon.getSunY()) * Math.sin(Math.PI / 180 * 5);
            ret[i][1] = moon.getSunY() + (moon.getCurrentX() - moon.getSunX()) * Math.sin(Math.PI/180 *5) + (moon.getCurrentY() - moon.getSunY()) * Math.cos(Math.PI/180 * 5);
            moon.setCurrentX(ret[i][0]); // sets the planets X and Y values to the updated values
            moon.setCurrentY(ret[i][1]);
            i++;
        }
        frame.repaint();
        return (ret);
    }

}
