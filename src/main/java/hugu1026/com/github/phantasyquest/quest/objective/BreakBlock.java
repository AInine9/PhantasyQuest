package hugu1026.com.github.phantasyquest.quest.objective;

import hugu1026.com.github.phantasyquest.PhantasyQuest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock extends Objective implements Listener {

    public BreakBlock(String objectiveName) {
        super(objectiveName);
    }

    @EventHandler
    public void BreakBlock(BlockBreakEvent event) {
        setArguments(event.getPlayer());

        for (String objectiveID : getObjectiveIDs()) {
            formatObjectiveID(objectiveID);

            String item = getArg(1);
            int amount = Integer.parseInt(getArg(2));
            Material material = Material.valueOf(item);

            //check whether the objective should proceed or not
            if (event.getBlock().getType() == material) {
                //check whether the objective should proceed or complete
                if (amount - 1 == 0) {
                    completeObjective(objectiveID, getQuestFileName(), getPlayer());
                } else {
                    String editedObjective = getQuestFileName() + " breakBlock " + item + " " + Integer.toString(amount - 1);
                    proceedObjective(objectiveID, editedObjective, getPlayer());
                }
            }
        }
    }

    @Override
    public void registerListener() {
        Bukkit.getPluginManager().registerEvents(this, PhantasyQuest.getMain());
    }
}
