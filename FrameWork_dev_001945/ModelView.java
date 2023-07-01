package etu1945.framework;

import java.util.HashMap;

public class ModelView {
    // chemin pour atteindre les pages .jsp
    String url;
    HashMap<String, Object> data = new HashMap<>();

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void addItem(String key, Object value) {
        this.getData().put(key, value);
    }
}