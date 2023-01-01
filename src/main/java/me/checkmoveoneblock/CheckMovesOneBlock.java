package me.checkmoveoneblock;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;


public class CheckMovesOneBlock implements Listener {
    private final JavaPlugin plugin;
    private final ArrayList<Player> playermove = new ArrayList<>();
    private final ArrayList<Player> playerdontmove = new ArrayList<>();

    public CheckMovesOneBlock(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void check(long interval) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    check2(p);
                    for (Player p1 : playermove) {
                        Bukkit.getPluginManager().callEvent(new PlayerMoveOneBlockEvent(p1));
                    }
                    for (Player p2 : playerdontmove) {
                        Bukkit.getPluginManager().callEvent(new PlayerDontMoveOneBlockEvent(p2));
                    }
                }
            }
        }.runTaskTimer(plugin, 0, interval + 1);
    }

    private void check2(Player p) {
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
                if (x1 != x || y1 != y || z1 != z) {
                    playermove.add(p);
                    if (playerdontmove.contains(p)) {
                        playerdontmove.remove(p);
                    }
                } else {
                    playerdontmove.add(p);
                    if (playermove.contains(p)) {
                        playermove.remove(p);
                    }
                }
            }
        };
    }

    public ArrayList<Player> getPlayermove() {
        return playermove;
    }

    public ArrayList<Player> getPlayerdontmove() {
        return playerdontmove;
    }
}
