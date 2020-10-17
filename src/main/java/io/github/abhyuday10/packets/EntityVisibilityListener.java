package io.github.abhyuday10.packets;

import java.util.Set;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;
import static com.comphenix.protocol.PacketType.Play.Server.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import io.github.abhyuday10.AmongUsMC;
import io.github.abhyuday10.Tags;

// Listener that prevents ingame players from hearing sound when assigning map
// to player's offhand1
public class EntityVisibilityListener extends PacketAdapter {
    public EntityVisibilityListener(AmongUsMC plugin) {
        super(plugin, ListenerPriority.NORMAL, ENTITY);
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        e.setCancelled(true);
    }
}
