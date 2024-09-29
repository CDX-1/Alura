package net.cdx.alura;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {
    private static Main instance;
    private LuaScriptManager scriptManager;

    @Override
    public void onEnable() {
        instance = this;

        File scriptsFolder = createScriptsFolder();
        scriptManager = new LuaScriptManager();
        try {
            scriptManager.executeScripts(scriptsFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }

    private File createScriptsFolder() {
        File scriptsFolder = new File(getDataFolder(), "scripts");
        scriptsFolder.mkdirs();
        return scriptsFolder;
    }

    public static Main getInstance() {
        return instance;
    }
}
