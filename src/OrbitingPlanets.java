

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

/**
 * The frame
 */
public class OrbitingPlanets extends JFrame{

    /**
     * all the planets currently made, sun is always at index 0
     */
    private ArrayList<Planet> planets;

    private ArrayList<Moon> moons;

    /**
     * keeps the number of planets made so far for easy calculations
     */
    private int numOfPlanets;

    /**
     * keeps the number of moons on each planet, so we can check if there are too many moons (index 0 corresponds to first planet not including star)
     */
    private int[] moonsOnPlanet = {0,0,0,0,0,0,0,0};

    /**
     * keeps the suns X coordinate for ease of calculation
     */
    private double sunX;

    /**
     * keeps the suns Y coordinate for ease of calculation
     */
    private double sunY;

    /**
     * keeps the width of the screen so you only need change in 1 place
     */
    private int screenWidth;

    /**
     * keeps the height of the screen so you only need change in 1 place
     */
    private int screenHeight;

    /**
     * timer to keep the planets moving constantly
     */
    private Timer timer;

    /**
     * constructor for the frame, also has the handlers for the frame
     */
    public OrbitingPlanets(){
        super("Orbiting Planets");
        planets = new ArrayList<>();
        screenWidth =1500;
        screenHeight = 1000;
        sunX = screenWidth/2 - 25;
        sunY = screenHeight/2 - 25;
        numOfPlanets = 1;
        Planet sun = new Planet(sunX,sunY,sunX,sunY,50,Color.yellow);
        add(sun);
        planets.add(sun);
        Planet planet1 = new Planet(sunX + 50,sunY,sunX,sunY,30,Color.red);
        add(planet1);
        planets.add(planet1);
        repaint();
        moons = new ArrayList<>();

        addMouseListener(new MouseListener() { // every time the user clicks, if there are < 8 planets create another one
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(numOfPlanets >= 8){ // if more than 8, we just return
                    return;
                }
                numOfPlanets++;
                Planet planet = new Planet(sunX + 50*numOfPlanets+1,sunY,sunX,sunY,30,Color.red); // creates a new planet 50 units higher than the last one
                add(planet);
                planets.add(planet);
                repaint(); // need to repaint to have it show up
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        addKeyListener(new KeyListener() { // every time the user presses a number 1-8 adds a moon to the corresponding planet
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                char[] numbers = {'1','2','3','4','5','6','7','8'};
                int number = 0;
                boolean correctKey = false;
                for(char n:numbers){ // checks if the numbers 1-8 were pressed
                    if (e.getKeyChar() == n){
                        correctKey = true;
                        number = Character.getNumericValue(n);
                        break;
                    }
                }
                //if any other key was pressed, a number > the number of planets we have so far was pressed, or the corresponding planet already has 2 moons, return
                if(!correctKey || number > numOfPlanets || moonsOnPlanet[number-1] >= 2){
                    return;
                }
                moonsOnPlanet[number-1]++; // number -1 index because the 0 index corresponds to the first planet
                Moon moon = new Moon(planets.get(number).getCurrentX() +25,planets.get(number).getCurrentY(),planets.get(number), 10, Color.gray);
                add(moon);
                moons.add(moon);
                planets.get(number).addMoon(moon);
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JFrame frame = this;
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { // creates and executes a planet mover task
                    PlanetMover task = new PlanetMover(planets,moons,frame);
                    task.execute();
                    repaint(); // repaint so the planets don't lag behind
            }
        });
        timer.start(); // timer starts right away and keeps going until end of program




    }

    /**
     * paints of the planets as circles
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // clears the frame
        Graphics2D g2d = (Graphics2D) g;
        for(Planet p:planets){
            //creates a circle for each planet, need to subtract half of radius to center the point and not be in top right corner
            Ellipse2D.Double cir = new Ellipse2D.Double(p.getCurrentX()-p.getRadius()/2,p.getCurrentY()-p.getRadius()/2,p.getRadius(),p.getRadius());
            g2d.setColor(p.getColor());
            g2d.fill(cir); // fills the circle with the given color
        }
        for(Moon m:moons){
            Ellipse2D.Double cir = new Ellipse2D.Double(m.getCurrentX()-m.getRadius()/2,m.getCurrentY()-m.getRadius()/2,m.getRadius(),m.getRadius());
            g2d.setColor(m.getColor());
            g2d.fill(cir);
        }


    }



}
