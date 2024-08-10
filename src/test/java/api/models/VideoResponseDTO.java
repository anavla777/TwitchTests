package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoResponseDTO {
    public ArrayList<Datum> data;
    public Pagination pagination;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Datum {
        public String id;
        public String stream_id;
        public String user_id;
        public String user_login;
        public String user_name;
        public String title;
        public String description;
        public Date created_at;
        public Date published_at;
        public String url;
        public String thumbnail_url;
        public String viewable;
        public int view_count;
        public String language;
        public String type;
        public String duration;
        public Object muted_segments;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pagination {
    }
}
