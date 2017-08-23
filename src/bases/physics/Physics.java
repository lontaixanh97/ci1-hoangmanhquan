package bases.physics;

import touhou.enemies.Enemy;

import java.util.Vector;


public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>(); // Generics

    public static <T extends PhysicsBody> T collideWith(BoxCollider boxCollider, Class<T> classz) {
        for(PhysicsBody body : bodies) {
            if (body.isActive()) {
                if (body.getClass().equals(classz) && body.getBoxCollider().intersects(boxCollider)) {
                    return (T) body;
                }
            }
        }

        return null;
    }

    // TODO: collide with many

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }

    public static void clearAll() {
        bodies.clear();
    }
}
