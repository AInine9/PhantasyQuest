package hugu1026.com.github.phantasyquest.quest.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItem extends Event {
    /*arg1 is Material Name.
    arg2 is item amount*/

    public GiveItem(String eventID, Player player) {
        super(eventID, player);
    }

    @Override
    public void ExecuteEvent() {
        Player player = super.getPlayer();
        int amount = Integer.parseInt(getArg(2));
        String item = getArg(1);
        ItemStack itemStack = new ItemStack(Material.valueOf(item), amount);

        player.getInventory().addItem(itemStack);
    }
}
