package me.alvsch.alvschlib.classes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonFacade {

    private final JsonObject jsonObject;

    public JsonFacade(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonFacade(File file) throws IOException {
        try(FileReader reader = new FileReader(file)) {
            JsonElement element = JsonParser.parseReader(reader);
            this.jsonObject = element.getAsJsonObject();
        }
    }

    public void set(String path, String value) {
        String[] keys = path.split("\\.");

        JsonObject currentObject = jsonObject;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (currentObject.has(key) && currentObject.get(key).isJsonObject()) {
                currentObject = currentObject.get(key).getAsJsonObject();
            } else {
                currentObject.addProperty(keys[i], value);
            }
        }
    }

    public JsonElement get(String path) {
        String[] keys = path.split("\\.");

        JsonObject currentObject = this.jsonObject;
        for(String key : keys) {
            if (currentObject.has(key) && currentObject.get(key).isJsonObject()) {
                currentObject = currentObject.get(key).getAsJsonObject();
            } else {
                return null;
            }
        }

        return currentObject.get(keys[keys.length - 1]);
    }

    public boolean has(String path) {
        String[] keys = path.split("\\.");

        JsonObject currentObject = jsonObject;
        for (String key : keys) {
            if (currentObject.has(key) && currentObject.get(key).isJsonObject()) {
                currentObject = currentObject.get(key).getAsJsonObject();
            } else {
                return false;
            }
        }

        return true;
    }


}
