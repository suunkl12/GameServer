package Simulation.Scene;

import Simulation.framework.SimulationBody;
import Simulation.framework.SimulationFrame;
import org.dyn4j.collision.Filter;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.Settings;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rotation;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.StepListener;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestScene extends SimulationFrame {
    /**
     * The serial version id
     */
    private static final long serialVersionUID = -8515555343422956827L;

    /**
     * Default constructor.
     */
    public TestScene() {
        super("TestScene", 250.0);
    }

    /* (non-Javadoc)
     * @see Simulation.SimulationFrame#initializeWorld()
     */

    static final float  speed = 5;
    static final Vector2  Horizon= new Vector2(1, 0);
    static final Vector2  Vertical= new Vector2(0, 1);
    @Override

    protected void initializeWorld() {
        // no gravity on a top-down view of a game
        this.world.setGravity(World.ZERO_GRAVITY);
            //========1 Simple GameObject Moving Ball=======//
            //
            //Tao 1 Body cho gameobject có màu tùy chọn
        for (int i = 0; i < 100; i++) {
            SimulationBody cueBall = new SimulationBody(new Color(RandomInt(255,0), RandomInt(255,0), RandomInt(255,0)));
            BodyFixture fixture = cueBall.addFixture(Geometry.createCircle(0.2));
            fixture.setRestitutionVelocity(0.0);
            //translate vị trí Spawn
            cueBall.translate(0, 0);
            //setMagnitude vận tốc
            //Rotation.rotation45().toVector() Rotation về Vector
            cueBall.setLinearVelocity(Direction(RandomInt(1,8)));
            //System.out.println("Vec2 "+Rotation.rotation45().toVector());
            // set mass infinite de Object Move lien tuc
            cueBall.setMass(MassType.INFINITE);
            this.world.addBody(cueBall);
            //this.world.update(1);
            //
            //========End Moving Ball=======//
        }

    }
    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public int RandomInt(int min, int max){
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt(max - min + 1) + min;

        return randomNum;
    }
    public Vector2 Direction(int RandomDirID){
        Vector2 Result = new Vector2(0,0);
        switch (RandomDirID) {
            case 1:
                // Right 0
                Result=Rotation.rotation0().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 2:
                // Top Right 45
                Result=Rotation.rotation45().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 3:
                // Top
                Result=Rotation.rotation90().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 4:
                // Top Left 135
                // Top Left
                Result=Rotation.rotation135().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 5:
                //Left
                Result=Rotation.rotation180().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 6:
                //Left Bot
                Result=Rotation.rotation225().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 7:
                // Bot
                Result=Rotation.rotation270().toVector().setMagnitude(RandomInt(10,1));
                return Result;
            case 8: //Bot Right
                Result=Rotation.rotation315().toVector().setMagnitude(RandomInt(10,1));
                return Result;

            default:
                return Result;
        }
    }
    /**
     * Entry point for the example application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        TestScene simulation = new TestScene();
        simulation.run();
    }
}