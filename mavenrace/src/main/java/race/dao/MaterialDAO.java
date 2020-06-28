package race.dao;

import javafx.scene.paint.Material;
import race.engine.Engine;

import java.util.List;

public interface MaterialDAO {
    public void createMaterial(Material material);
    List<Material> getMaterial(); // список покрытий
}
