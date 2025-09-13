package mc.cherry_leaves.net.momotetsu2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class command {
    public void AlliRegister() {
        registerCardCommand();
    }
    private void registerCardCommand() {
        org.bukkit.command.Command Command = new org.bukkit.command.Command("card") {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
                if(!(sender instanceof Player player)){return false;}
                if(args.length == 0) {
                    for (int i = 0; i < card.CardList.size(); i++) {
                        player.getInventory().addItem(card.CardList.get(i));
                    }
                    player.sendMessage(Component.text("全てのカードを入手しました。"));
                    player.playSound(player.getLocation(), "minecraft:entity.experience_orb.pickup", 1.0f, 1.0f);
                }
                else if(args.length == 1) {
                    player.getInventory().addItem(new card().get_card(args[0]));
                    player.sendMessage(Objects.requireNonNull(Objects.requireNonNull(new card().get_card(args[0]).getItemMeta().displayName()).color(NamedTextColor.AQUA)).append(Component.text(" を入手しました。").color(NamedTextColor.WHITE)));
                    player.playSound(player.getLocation(), "minecraft:entity.experience_orb.pickup", 1.0f, 1.0f);
                }
                else {return false;}
                return true;
            }
            @Override
            public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
                List<String> completions = new ArrayList<>();

                if (args.length == 1) {
                    // カード名の予測を提供
                    List<String> cardNames = List.of("just", "express", "limited_express", "real_express", "force_best", "port", "break_turn", "real_shinkansen", "expo", "angel", "devil"); // カード名のリストを取得
                    String currentArg = args[0].toLowerCase();

                    for (String cardName : cardNames) {
                        if (cardName.toLowerCase().startsWith(currentArg)) {
                            completions.add(cardName);
                        }
                    }
                }

                return completions;
            }
        };

        Command.setDescription("カードを入手✓");
        new Momotetsu2().getServer().getCommandMap().register("Momotetsu2", Command);
    }
}
