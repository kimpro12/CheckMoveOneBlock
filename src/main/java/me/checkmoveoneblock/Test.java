package me.checkmoveoneblock;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Test implements Runnable {

    private int taskId;

    public Test(JavaPlugin plugin, int arg1) {
        taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, arg1);
    }
}