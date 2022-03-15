package me.adelemphii.strangehappenings.crystalbox.command;

import me.adelemphii.strangehappenings.StrangeHappenings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class BoxCreationCommand implements CommandExecutor {

    StrangeHappenings plugin;

    public BoxCreationCommand(StrangeHappenings plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equals("createbox")) {
            if(sender instanceof Player player) {

                if(player.hasPermission("strangehappenings.createbox")) {
                    player.sendMessage("You have created a box!");
                    BoundingBox box = plugin.getBoxManager().createBox(player.getLocation(), 50, 50, 50);

                    plugin.getCrystalManager().createCrystal(box.getCenter().toLocation(player.getWorld()));
                }
            }
        }

        return false;
    }
}
