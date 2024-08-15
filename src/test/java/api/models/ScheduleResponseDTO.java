package api.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ScheduleResponseDTO {
    public Data data;

    public static class Category {
        public int id;
        public String name;
    }

    public static class Data {
        public ArrayList<Segment> segments;
        public int broadcaster_id;
        public String broadcaster_name;
        public String broadcaster_login;
        public Object vacation;
    }

    public static class Segment {
        public String id;
        public String start_time;
        public String end_time;
        public String title;
        public Object canceled_until;
        public Category category;
        public boolean is_recurring;
    }
}
