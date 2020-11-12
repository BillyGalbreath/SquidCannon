package net.pl3x.bukkit.squidcannon;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class SquidCannon extends JavaPlugin {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command");
            return true;
        }

        Location loc = ((Player) sender).getEyeLocation();
        Squid squid = (Squid) loc.getWorld().spawnEntity(loc, EntityType.SQUID);
        squid.setVelocity(loc.getDirection().multiply(2));

        new BukkitRunnable() {
            @Override
            public void run() {
                Location loc = squid.getLocation();
                squid.remove();
                loc.getWorld().createExplosion(loc, 0F);
            }
        }.runTaskLater(this, 20);

        return true;
    }
}
