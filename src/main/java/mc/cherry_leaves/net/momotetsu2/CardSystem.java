package mc.cherry_leaves.net.momotetsu2;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class CardSystem implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Item item = e.getItemDrop();
    }
}
