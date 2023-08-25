package com.kman.objects;

import com.google.gson.JsonArray;
import com.kman.data.Data;
import com.kman.ui.CompoundIcon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Tag {
    private String name;
    private String code;
    private boolean interaction;
    private CompoundIcon icon;
    private Data data;
    public Boolean chrome = false;
    public Boolean firefox = false;
    public Boolean safari = false;

    public Tag(Data data, String name, String code, JsonArray browsers, Boolean interaction) {
        this.data = data;
        this.name = name;
        this.code = code;
        this.interaction = interaction;
        parseBrowsers(browsers);
    }
    public String getName() {
        if (name.equals("*")){
            return "custom tags";
        }
        return name;
    }

    public String getCode() {
        if (code.contains("*")){
            return code.replace("*","xss");
        }
        return code;
    }

    // Parse JSON array to determine compatibility and generate CompoundIcon
    private void parseBrowsers(JsonArray browsers){
        String browserString = browsers.toString();
        List<Icon> icons = new ArrayList<>();
        if (browserString.contains("chrome")){
            this.chrome = true;
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
            this.firefox = true;
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
            this.safari = true;
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

    // Determine compatibility by browser name
    public Boolean hasBrowser(String browser){
        Boolean add = false;
        if (browser.equals("Chrome") && this.chrome){
            add = true;
        }
        if (browser.equals("Firefox") && this.firefox){
            add = true;
        }
        if (browser.equals("Safari") && this.safari){
            add = true;
        }
        return add;
    }

    public boolean getInteraction() {
        return interaction;
    }
    public void setInteraction(boolean interaction) {
        this.interaction = interaction;
    }
    public CompoundIcon getIcon(){
        return this.icon;
    }
}
