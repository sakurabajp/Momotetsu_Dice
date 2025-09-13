package mc.cherry_leaves.net.momotetsu2;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.util.Random;

public final class Momotetsu2 extends JavaPlugin implements Listener {

    private Random random;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.random = new Random();
        new command().AlliRegister();

        // イベントリスナーを登録
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("Momotetsu2プラグインが有効になりました！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Momotetsu2プラグインが無効になりました。");
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        // 捨てたアイテムが的ブロック（ターゲットブロック）かチェック
        if (event.getItemDrop().getItemStack().getType() != Material.TARGET) {
            return;
        }

        // 的ブロックのドロップを防ぐ（サイコロとして使用するため）
        // event.setCancelled(true);
        event.getItemDrop().remove();

        // サイコロの演出を開始
        startDiceAnimation(player);
    }

    private void startDiceAnimation(Player player) {
        // 周りのプレイヤーを取得（半径20ブロック以内）
        java.util.List<Player> nearbyPlayers = player.getWorld().getPlayers().stream()
                .filter(p -> p.getLocation().distance(player.getLocation()) <= 20)
                .toList();

        // 演出開始の音
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.5f);

        // 演出フェーズ1: サイコロを振り始める
        nearbyPlayers.forEach(p -> {
            Title title = Title.title(
                    Component.text("🎲 サイコロタイム 🎲").color(NamedTextColor.GOLD),
                    Component.text(player.getName() + " がサイコロを振っています...").color(NamedTextColor.YELLOW),
                    Title.Times.times(Duration.ofMillis(0),Duration.ofSeconds(3),Duration.ofMillis(0)
                    )
            );
            player.showTitle(title);
        });

        new BukkitRunnable() {
            int count = 0;
            final String[] rollAnimation = {"⚀", "⚁", "⚂", "⚃", "⚄", "⚅"};

            @Override
            public void run() {
                count++;

                if (count <= 15) {
                    // ローリング演出（6回）
                    String currentDice = rollAnimation[random.nextInt(6)];
                    nearbyPlayers.forEach(p -> {
                        Title title = Title.title(
                                Component.text("回転中... ").color(NamedTextColor.AQUA).append(Component.text(currentDice)),
                                Component.text(player.getName() + " がサイコロを振っています").color(NamedTextColor.YELLOW),
                                Title.Times.times(Duration.ofMillis(0),Duration.ofSeconds(3),Duration.ofMillis(0)
                                )
                        );
                        player.showTitle(title);
                    });

                    // ローリング音
                    player.getWorld().playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.5f);

                }
                if (count == 17 || count == 19 || count == 23) {
                    // ローリング演出（6回）
                    String currentDice = rollAnimation[random.nextInt(6)];
                    nearbyPlayers.forEach(p -> {
                        Title title = Title.title(
                                Component.text("回転中... ").color(NamedTextColor.AQUA).append(Component.text(currentDice)),
                                Component.text(player.getName() + " がサイコロを振っています").color(NamedTextColor.YELLOW),
                                Title.Times.times(Duration.ofMillis(0),Duration.ofSeconds(3),Duration.ofMillis(0)
                                )
                        );
                        player.showTitle(title);
                    });

                    // ローリング音
                    player.getWorld().playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.5f);

                }
                else if (count == 28) {
                    // 最終結果を決定
                    int finalResult = random.nextInt(6) + 1;
                    String finalDiceSymbol = rollAnimation[finalResult - 1];

                    // 結果発表の音
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

                    // 結果を表示
                    nearbyPlayers.forEach(p -> {
                        Title title = Title.title(
                                Component.text("結果: ").color(NamedTextColor.RED).append(Component.text(finalResult).color(NamedTextColor.GOLD)).append(Component.text(" " + finalDiceSymbol)),
                                Component.text("おめでとう！").color(NamedTextColor.GREEN),
                                Title.Times.times(Duration.ofMillis(200),Duration.ofSeconds(5),Duration.ofMillis(200)
                                )
                        );
                        player.showTitle(title);
                    });

                    Component chatComponent = Component.text("🎲 ").append(Component.text(player.getName()).color(NamedTextColor.WHITE)).append(Component.text(" のサイコロの結果: ").color(NamedTextColor.YELLOW).append(Component.text(finalResult + " " + finalDiceSymbol).color(NamedTextColor.RED)));

                    nearbyPlayers.forEach(p -> p.sendMessage(chatComponent));
                    this.cancel();
                }
            }
        }.runTaskTimer(this, 0, 2); // 0.1秒間隔で実行
    }
}
