package com.kman.objects;

import com.google.gson.JsonObject;

public class SavedResponse {
    public String name;
    public String url;
    public String response;

    public SavedResponse(String name, String url, String response) {
        this.name = name;
        this.url = url;
        this.response = response;
    }
    // Return object as JSON
    public JsonObject toJSON(){
        JsonObject json = new JsonObject();
        json.addProperty("name", this.name);
        json.addProperty("response", this.response);
        return json;
    }
}
