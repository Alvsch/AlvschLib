package me.alvsch.alvschlib.classes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.xml.stream.FactoryConfigurationError;
import java.io.*;

public class JsonFacade {

    @Getter
    private final JsonObject jsonObject;

    public JsonFacade(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonFacade(Reader reader) {
        JsonElement element = JsonParser.parseReader(reader);
        this.jsonObject = element.getAsJsonObject();
    }

    public void set(String path, String value) {
        String[] keys = path.split("\\.");

        JsonObject currentObject = jsonObject;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (!currentObject.has(key) || !currentObject.get(key).isJsonObject()) {
                JsonObject newObject = new JsonObject();
                currentObject.add(key, newObject);
                currentObject = newObject;
            } else {
                currentObject = currentObject.get(key).getAsJsonObject();
            }
        }

        String lastKey = keys[keys.length - 1];
        currentObject.addProperty(lastKey, value);
    }

    public JsonElement get(String path) {
        String[] keys = path.split("\\.");

        JsonObject currentObject = this.jsonObject;
        for(int i = 0; i < keys.length; i++) {
            if (currentObject.has(keys[i]) && currentObject.get(keys[i]).isJsonObject()) {
                currentObject = currentObject.get(keys[i]).getAsJsonObject();
            } else {
                return i == keys.length - 1 ? currentObject.get(keys[i]) : null;
            }
        }

        return currentObject.get(keys[keys.length - 1]);
    }

    public boolean has(String path) {
        String[] keys = path.split("\\.");

        JsonObject currentObject = jsonObject;
        for (int i = 0; i < keys.length; i++) {
            if (currentObject.has(keys[i]) && currentObject.get(keys[i]).isJsonObject()) {
                currentObject = currentObject.get(keys[i]).getAsJsonObject();
            } else {
                return i == keys.length - 1 && currentObject.has(keys[i]);
            }
        }

        return true;
    }


}
