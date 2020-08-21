package service;

import domain.Essay;

import java.util.List;
import java.util.Map;

public interface EssayService {
    public Essay getEssayFromId(int essay_id);
    public List<Essay> getEssays();
    public List<Essay> getEssays(int user_id);
    public Essay addEssay(Essay essay);
    public List<Essay> getEssaysFromPage(int page);
    public int getEssaysTotalNumber();
    public boolean toggleStar(int essay_id, int user_id);
    public int getStar(int essay_id);
    public boolean addFavorite(int essay_id, int favorite_id);
    public Essay getNext(int essay_id);
    public Essay getPrevious(int essay_id);
    public List<Essay> getStarEssays(int user_id);
    public boolean delEssay(int essay_id);
    public List<Essay> getEssayList(String content);
    public Essay updateEssay(int id,Essay essay);
}
