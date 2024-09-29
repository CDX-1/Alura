package net.cdx.alura;

import org.jetbrains.annotations.NotNull;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class LuaScriptManager {
    private final Globals globals;

    public LuaScriptManager() {
        globals = JsePlatform.standardGlobals();
    }

    public void executeScript(@NotNull File file) throws IOException {
        if (file.isDirectory()) throw new IOException("Script must be a file");
        try (Reader reader = new FileReader(file)) {
            LuaValue chunk = globals.load(reader, "script");
            chunk.call();
        }
    }

    public void executeScripts(@NotNull File directory) throws IOException {
        if (!directory.isDirectory()) throw new IOException("Script folder must be a directory");
        for (File file : directory.listFiles()) {
            executeScript(file);
        }
    }
}
