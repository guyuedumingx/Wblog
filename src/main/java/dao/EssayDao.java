package dao;

import domain.Essay;

import java.sql.SQLException;
import java.util.List;

public interface EssayDao {
    public Essay getEssay(int essay_id);
    public List<Essay> getEssays();
    public List<Essay> getEssays(int user_id);
    public int addEssay(Essay essay) throws SQLException;
    public int getStar(int essay_id);
    public boolean addStar(int essay_id, int user_id);
    public boolean addFavorite(int essay_id, int favorite_id);
}
