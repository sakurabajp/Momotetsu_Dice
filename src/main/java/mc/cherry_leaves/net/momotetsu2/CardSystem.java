package mc.cherry_leaves.net.momotetsu2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

import static mc.cherry_leaves.net.momotetsu2.card.CardList;

public class CardSystem implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Item item = e.getItemDrop();
        if (CardList.contains(item.getItemStack())) {
            e.getItemDrop().remove();
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Component.text(e.getPlayer().getName() + "が")
                        .append(Component.text(" " + PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(item.getItemStack().getItemMeta().displayName())) + " ").color(NamedTextColor.YELLOW))
                        .append(Component.text("を使用しました！")).color(NamedTextColor.WHITE));
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 0.1f);
            }
        }
    }
}
