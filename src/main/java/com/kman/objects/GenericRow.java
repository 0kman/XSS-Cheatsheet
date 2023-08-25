package com.kman.objects;

import com.google.gson.JsonArray;
import com.kman.data.Data;
import com.kman.ui.CompoundIcon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GenericRow {
    private Data data;
    private CompoundIcon icon;
    private String description;
    private String code;
    private JsonArray browsers;
    public GenericRow(Data data, String description, String code, JsonArray browsers) {
        this.data = data;
        this.description = description;
        this.code = code;
        this.browsers = browsers;
        parseBrowsers(this.browsers);
    }

    // Parse JSON array to determine compatibility and generate CompoundIcon
    private void parseBrowsers(JsonArray browsers){
        String browserString = browsers.toString();
        List<Icon> icons = new ArrayList<>();
        if (browserString.contains("chrome")){
            ImageIcon icon;
            icon = this.data.chromeIcon;
            icons.add(icon);
        }
        else{
            ImageIcon icon;
            icon = this.data.chromeIcon;
            icons.add(UIManager.getLookAndFeel().getDisabledIcon(null, icon));
        }
        if (browserString.contains("firefox")){
            ImageIcon icon;
            icon = this.data.firefoxIcon;
            icons.add(icon);
        }
        else{
            ImageIcon icon;
            icon = this.data.firefoxIcon;
            icons.add(UIManager.getLookAndFeel().getDisabledIcon(null, icon));
        }
        if (browserString.contains("safari")){
            ImageIcon icon;
            icon = this.data.safariIcon;
            icons.add(icon);
        }
        else{
            ImageIcon icon;
            icon = this.data.safariIcon;
            icons.add(UIManager.getLookAndFeel().getDisabledIcon(null, icon));
        }
        this.icon = new CompoundIcon(icons);
    }
    public CompoundIcon getIcon() {
        return icon;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCode(){
        return this.code;
    }
}
