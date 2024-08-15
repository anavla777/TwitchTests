package api.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StreamsResponseDTO {
    public ArrayList<Data> data;
    public Pagination pagination;

    public static class Data {
        public String id;
        public String user_id;
        public String user_login;
        public String user_name;
        public String game_id;
        public String game_name;
        public String type;
        public String title;
        public int viewer_count;
        public String started_at;
        public String language;
        public String thumbnail_url;
        public ArrayList<Object> tag_ids;
        public ArrayList<String> tags;
        public boolean is_mature;
    }

    public static class Pagination {
        public String cursor;
    }

}
