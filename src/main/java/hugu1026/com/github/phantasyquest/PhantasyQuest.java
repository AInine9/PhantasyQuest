package hugu1026.com.github.phantasyquest;

import hugu1026.com.github.phantasyquest.citizens.listener.NPCRightClick;
import hugu1026.com.github.phantasyquest.commands.QuestCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhantasyQuest extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEvents();
        getCommand("quest").setExecutor(new QuestCommands(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new NPCRightClick(), this);
    }
}
