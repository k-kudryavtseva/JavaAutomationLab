package race.dao.impl;

import javafx.scene.paint.Material;
import org.apache.ibatis.session.SqlSession;
import race.config.SessionFactory;
import race.dao.MaterialDAO;

import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    private final static String namespace = "material_mapper.xml";

    @Override
    public void createMaterial(Material material) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.insert(namespace + ".createMaterial", material);
        sqlSession.commit();
        sqlSession.close();
   }

   @Override
   public List<Material> getMaterial() {
       SqlSession sqlSession = SessionFactory.getSession();
       List<Material> materials = sqlSession.selectList(namespace + ".getMaterial");
       System.out.println(materials);
       sqlSession.close();
       return materials;
   }
}
