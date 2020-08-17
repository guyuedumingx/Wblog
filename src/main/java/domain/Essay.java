package domain;

public class Essay {
    private int essay_id;
    private int user_id;
    private String title;
    private String content;
    private String update_time;
    private String create_time;

    public Essay(int user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
    }

    public Essay(int essay_id, int user_id, String title,
                 String content, String update_time, String create_time) {
        this.essay_id = essay_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.update_time = update_time;
        this.create_time = create_time;
    }

    public int getEssay_id() {
        return essay_id;
    }

    public void setEssay_id(int essay_id) {
        this.essay_id = essay_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
