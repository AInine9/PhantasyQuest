package hugu1026.com.github.phantasyquest;

import hugu1026.com.github.phantasyquest.citizens.listener.NPCRightClick;
import hugu1026.com.github.phantasyquest.commands.QuestCommands;
import hugu1026.com.github.phantasyquest.listener.PlayerJoin;
import hugu1026.com.github.phantasyquest.quest.objective.BreakBlock;
import hugu1026.com.github.phantasyquest.quest.objective.Objective;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class PhantasyQuest extends JavaPlugin {

    private static PhantasyQuest main;
    private Map<String, Class<? extends Objective>> objectiveTypes = new HashMap<>();

    public PhantasyQuest() {
        main = this;
    }

    public static PhantasyQuest getMain() {
        return main;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        this.registerEvents();

        registerObjective();

        getCommand("quest").setExecutor(new QuestCommands(this));

        for (String objectiveName : objectiveTypes.keySet()) {
            Class<? extends Objective> objectiveClass = objectiveTypes.get(objectiveName);
            try {
                objectiveClass.getConstructor(String.class).newInstance(objectiveName);
                Bukkit.getLogger().info("Objective " + objectiveName + " has been registered");
            } catch (Exception ex) {
                Bukkit.getLogger().warning("Something wrong! Check the objective's spells or classes.");
                return;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new NPCRightClick(), this);
        pm.registerEvents(new PlayerJoin(), this);
    }

    private void registerObjective() {
        objectiveTypes.put("breakBlock", BreakBlock.class);
    }
}
