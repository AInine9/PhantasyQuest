package hugu1026.com.github.phantasyquest.quest.condition;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HasItem extends Condition {
    /*arg 1 > Material name
    arg 2 > item amount
     */

    public HasItem(String conditionID) {
        super(conditionID);
    }

    @Override
    public boolean checkMeetCondition(Player player) {
        String item = getArg(1);
        int amount = Integer.parseInt(getArg(2));
        ItemStack itemStack = new ItemStack(Material.getMaterial(item), amount);

        return player.getInventory().contains(itemStack);
    }
}
