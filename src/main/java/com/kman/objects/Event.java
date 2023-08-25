package com.kman.objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kman.data.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Event {
    private Data data;
    private String name;
    private String description;
    private JsonArray jsonTags;
    private Tag selectedTag;
    private List<Tag> tags = new ArrayList<>();
    private DefaultCellEditor cellEditor;
    public Event(Data data, String name, String description, JsonArray jsonTags) {
        this.data = data;
        this.name = name;
        this.description = description;
        this.jsonTags = jsonTags;
        parseTags(jsonTags);
        this.selectedTag = this.tags.get(0);
    }
    public void setSelectedTag(String selectedTag) {
        for (Tag t : this.tags){
            if (t.getName().equals(selectedTag)){
                this.selectedTag = t;
                return;
            }
        }
    }
    private void parseTags(JsonArray tags){
        for (JsonElement j : tags){
            JsonObject obj = j.getAsJsonObject();
            String tagName = obj.get("tag").getAsString();
            if (tagName.equals("*")){
                for (String s : data.tagNames){
                    this.tags.add(new Tag(this.data, s, obj.get("code").getAsString().replace("*", s), obj.getAsJsonArray("browsers"), obj.get("interaction").getAsBoolean()));
                }
                return;
            }
            this.tags.add(new Tag(this.data, obj.get("tag").getAsString(), obj.get("code").getAsString(), obj.getAsJsonArray("browsers"), obj.get("interaction").getAsBoolean()));
            Comparator<Tag> compar = Comparator.comparing(Tag::getName, String.CASE_INSENSITIVE_ORDER);
            this.tags.sort(compar);
        }
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public DefaultCellEditor getCellEditor() {
        return cellEditor;
    }
    public Tag getSelectedTag() {
        return selectedTag;
    }
}
