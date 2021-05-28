package Simulation.Scene;

import Simulation.framework.SimulationBody;
import Simulation.framework.SimulationFrame;
import org.dyn4j.collision.Filter;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Rotation;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.world.World;

import java.awt.*;

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
        SimulationBody cueBall = new SimulationBody(new Color(75, 255, 0));
        BodyFixture fixture = cueBall.addFixture(Geometry.createCircle(0.2));
        fixture.setRestitutionVelocity(0.0);
        //translate vị trí Spawn
        cueBall.translate(0, 0);
        //setMagnitude vận tốc
        //Rotation.rotation45().toVector() Rotation về Vector
        cueBall.setLinearVelocity(Rotation.rotation45().toVector().setMagnitude(speed));
        //System.out.println("Vec2 "+Rotation.rotation45().toVector());
        // set mass infinite de Object Move lien tuc
        cueBall.setMass(MassType.INFINITE);
        this.world.addBody(cueBall);
        //
        //========End Moving Ball=======//

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