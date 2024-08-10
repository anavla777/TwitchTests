package api.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class UserResponseDTO {
    public ArrayList<Data> data;

    public static class Data {
        public String id;
        public String login;
        public String display_name;
        public String type;
        public String broadcaster_type;
        public String description;
        public String profile_image_url;
        public String offline_image_url;
        public int view_count;
        public Date created_at;
    }

}
