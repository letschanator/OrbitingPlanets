
import javax.swing.JFrame;

/**
 * runner class to create a Orbiting Planets frame
 */
public class PlanetRunner {

    /**
     * creates a frame and shows it to the user
     * @param Args
     */
    public static void main(String Args[]){
        OrbitingPlanets planetFrame = new OrbitingPlanets();
        planetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        planetFrame.setSize(1500,1000);
        planetFrame.setResizable(false);
        planetFrame.setVisible(true);
    }


}

