package com.kman;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;
import com.coreyd97.BurpExtenderUtilities.Preferences;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kman.data.Data;
import com.kman.functions.Functions;
import com.kman.ui.MainTab;

import java.util.ArrayList;
import java.util.List;

public class CustomExtension implements BurpExtension {
    MontoyaApi api;
    Logging logging;
    Functions functions;
    Data data;
    @Override
    public void initialize(MontoyaApi api) {
        this.api = api;
        this.logging = api.logging();
        this.data = new Data(api, logging);
        this.functions = new Functions(api, logging, data);
        try{
            // Register persisted setting
            data.preferences.registerSetting("saved", new TypeToken<List<JsonObject>>() {}.getType(), new ArrayList<JsonObject >(), Preferences.Visibility.GLOBAL);
        }
        catch(Exception e){
            // Already registered!
        }

        logging.logToOutput("XSS Cheatsheet v1.0 - All credit to PortSwigger research: https://portswigger.net/web-security/cross-site-scripting/cheat-sheet");
        logging.logToOutput("Developed by Kai Mannion");
        logging.logToOutput("Attempting to load persisted data (if any)...");
        functions.loadReponses();

        api.extension().setName("XSS Cheatsheet");
        api.userInterface().registerSuiteTab("XSS Cheatsheet", new MainTab(functions, data).getUiComponent());
    }
}
