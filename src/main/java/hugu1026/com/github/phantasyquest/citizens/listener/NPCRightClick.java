package hugu1026.com.github.phantasyquest.citizens.listener;

import hugu1026.com.github.phantasyquest.quest.QuestSuggester;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class NPCRightClick implements Listener {
    @EventHandler
    public void NPCClick(NPCRightClickEvent event) {
        if (event.isCancelled()) {
            return;
        }
        int id = event.getNPC().getId();
        Player player = event.getClicker();

        QuestSuggester questSuggester = new QuestSuggester(id, player);
        Map<String, String> suggestedQuests = questSuggester.getSuggestedQuests();

        suggestedQuests.keySet().forEach(questName -> player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.GOLD + questName));
    }
}
