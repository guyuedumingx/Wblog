package service;

import domain.Essay;

import java.util.List;

public interface EssayService {
    public Essay getEssayFromId(int essay_id);
    public List<Essay> getEssays();
    public List<Essay> getEssays(int user_id);
    public Essay addEssay(Essay essay);
    public List<Essay> getEssaysFromPage(int page);
    public boolean delEssay(int essay_id);
    public boolean addStar(int essay_id, int user_id);
    public int getStar(int essay_id);
    public boolean addFavorite(int essay_id, int favorite_id);

}
