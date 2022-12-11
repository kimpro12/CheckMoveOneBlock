package me.checkmoveoneblock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CheckMoveOneBlock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("[CheckMoveOneBlock] Plugin enabled");
        Bukkit.getPluginManager().registerEvents(this, this);
        new CheckMovesOneBlock(this).check(getConfig().getInt("Ticks"));
    }
    @EventHandler
    public void check(PlayerMoveOneBlockEvent e) {
        Player p = e.getPlayer();
        p.sendMessage("You have moved one block");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[CheckMoveOneBlock] Plugin disabled");
    }
}
