package me.checkmoveoneblock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class CheckMovesOneBlock {
    private final JavaPlugin plugin;
    private boolean isPlayerMoveOneBlock;
    public CheckMovesOneBlock(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void check(long interval) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    check(p);
                    if (isPlayerMoveOneBlock(p)) {
                        Bukkit.getPluginManager().callEvent(new PlayerMoveOneBlockEvent(p));
                    }
                }
            }
        }.runTaskTimer(plugin, 0, interval + 1);
    }
    private void check(Player p) {
        Location t = p.getLocation();
        int x = (int) t.getX();
        int y = (int) t.getY();
        int z = (int) t.getZ();
        new Test(plugin, plugin.getConfig().getInt("Ticks")) {
            @Override
            public void run() {
                Location t2 = p.getLocation();
                int x1 = (int) t2.getX();
                int y1 = (int) t2.getY();
                int z1 = (int) t2.getZ();
                setPlayerMoveOneBlock(x != x1 || y != y1 || z != z1);
            }
        };
    }

    public void setPlayerMoveOneBlock(boolean playerMoveOneBlock) {
        isPlayerMoveOneBlock = playerMoveOneBlock;
    }

    public boolean isPlayerMoveOneBlock(Player p) {
        return isPlayerMoveOneBlock;
    }
}
