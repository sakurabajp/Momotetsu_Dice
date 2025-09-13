package mc.cherry_leaves.net.momotetsu2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class card {

    static List<ItemStack> CardList = List.of(
            just(), express(), limited_express(), real_express(), force_best(), port(), break_turn(), real_shinkansen(), expo(), angel(), devil()
    );

    public ItemStack get_card(String s) {
        return switch (s) {
            case "just" -> CardList.getFirst();
            case "express" -> CardList.get(1);
            case "limited_express" -> CardList.get(2);
            case "real_express" -> CardList.get(3);
            case "force_best" -> CardList.get(4);
            case "port" -> CardList.get(5);
            case "break_turn" -> CardList.get(6);
            case "real_shinkansen" -> CardList.get(7);
            case "expo" -> CardList.get(8);
            case "angel" -> CardList.get(9);
            case "devil" -> CardList.get(10);
            default -> null_check();
        };
    }

    public static ItemStack just() {
        return setItem(
                Material.ANGLER_POTTERY_SHERD,
                Component.text("ぴったりカード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("指定した他の人と同じマスに移動する。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack express() {
        return setItem(
                Material.ARCHER_POTTERY_SHERD,
                Component.text("急行カード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("サイコロを追加で1つ入手することができる。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack limited_express() {
        return setItem(
                Material.ARMS_UP_POTTERY_SHERD,
                Component.text("特急カード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("サイコロを追加で2つ入手することができる。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack real_express() {
        return setItem(
                Material.BLADE_POTTERY_SHERD,
                Component.text("リアル急行カード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("路線種別の快速や急行として移動することができるようになる。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack force_best() {
        return setItem(
                Material.BREWER_POTTERY_SHERD,
                Component.text("強制絶好調カード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("指定した人を強制的に絶好調(サイコロ+4)にする。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack port() {
        return setItem(
                Material.BURN_POTTERY_SHERD,
                Component.text("港乗り継ぎカード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("特定の港(空港含む)間を移動することができる。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack break_turn() {
        return setItem(
                Material.DANGER_POTTERY_SHERD,
                Component.text("１ターン休みカード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("指定したプレイヤーを１ターン休みにする。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack real_shinkansen() {
        return setItem(
                Material.EXPLORER_POTTERY_SHERD,
                Component.text("リアル新幹線カード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("新幹線を利用することができる。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack expo() {
        return setItem(
                Material.FLOW_POTTERY_SHERD,
                Component.text("万博カード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("指定したプレイヤーを").decoration(TextDecoration.ITALIC, false).append(Component.text("『愛・地球博記念公園』").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true).append(Component.text("に飛ばし、入場料7,500円を強制的に払わせる").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, false))),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack angel() {
        return setItem(
                Material.FRIEND_POTTERY_SHERD,
                Component.text("エンジェルカード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("5ターンの間、1ターン毎に3,000円獲得できる。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack devil() {
        return setItem(
                Material.GUSTER_POTTERY_SHERD,
                Component.text("デビルカード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("5ターンの間、1ターン毎に3,000円没収される。").decoration(TextDecoration.ITALIC, false),
                        Component.text("※Qで投げて使用").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false)
                )
        );
    }

    public static ItemStack null_check() {
        return setItem(
                Material.GUSTER_POTTERY_SHERD,
                Component.text("存在しないカード").decoration(TextDecoration.ITALIC, false),
                List.of(Component.text("間違っているコマンド、または予期せぬ処理により生まれるカード。").decoration(TextDecoration.ITALIC, false),
                        Component.text("特に効果はない。特に効果はない。特に効果はない。").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, true).decoration(TextDecoration.UNDERLINED, true)
                )
        );
    }

    public static ItemStack setItem(Material m, Component s, List<Component> lore){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(s);
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
