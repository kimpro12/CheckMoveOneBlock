package me.checkmoveoneblock;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerMoveOneBlockEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    public PlayerMoveOneBlockEvent(Player who) {
        super(who);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
