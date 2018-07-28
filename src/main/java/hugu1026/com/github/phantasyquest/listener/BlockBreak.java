package hugu1026.com.github.phantasyquest.listener;

import hugu1026.com.github.phantasyquest.quest.objective.ObjectiveCompleter;
import hugu1026.com.github.phantasyquest.quest.objective.ObjectiveProgressor;
import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        /*arg1 is block name
        arg2 is amount of blocks*/
        ObjectiveProgressor progressor = new ObjectiveProgressor(event.getPlayer(), "blockBreak");
        if (progressor.checkAvailable()) {
            String item = progressor.getArg(1);
            int amount = Integer.parseInt(progressor.getArg(2));
            Material material = Material.valueOf(item);

            if (event.getBlock().getType() == material) {
                if (amount - 1 == 0) {
                    //complete objective
                    ObjectiveCompleter completer = new ObjectiveCompleter(progressor.getObjectiveID(), event.getPlayer());
                    completer.completeObjective();
                } else {
                    //progress objective
                    String originalObjective = "blockBreak " + item + " " + amount;
                    String editedObjective = "blockBreak " + item + " " + Integer.toString(amount - 1);
                    PlayerQuestDataUtil.replaceObjective(originalObjective, editedObjective, event.getPlayer());
                }
            }
        }
    }
}
