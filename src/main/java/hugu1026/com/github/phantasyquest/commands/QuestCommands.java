package hugu1026.com.github.phantasyquest.commands;

import hugu1026.com.github.phantasyquest.PhantasyQuest;
import hugu1026.com.github.phantasyquest.quest.Quest;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.CitizensPlugin;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestCommands implements CommandExecutor {
    private final PhantasyQuest plg;

    public QuestCommands(PhantasyQuest plg) {
        this.plg = plg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("quest")) {
            if (args.length == 5) {
                if (args[0].equalsIgnoreCase("start")) {
                    String questFileName = args[1];
                    Player player = Bukkit.getPlayer(args[2]);
                    int startPoint = Integer.parseInt(args[3]);
                    NPC NPCID = CitizensAPI.getNPCRegistry().getById(Integer.parseInt(args[4]));
                    Quest quest = new Quest(questFileName, player, NPCID);
                    quest.startQuest(startPoint);
                }
            }
        }
        return false;
    }
}
