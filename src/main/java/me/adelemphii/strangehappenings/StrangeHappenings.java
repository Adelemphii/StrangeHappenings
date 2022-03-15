package me.adelemphii.strangehappenings;

import me.adelemphii.strangehappenings.crystalbox.command.BoxCreationCommand;
import me.adelemphii.strangehappenings.crystalbox.utils.BoxManager;
import me.adelemphii.strangehappenings.crystalbox.utils.CrystalManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrangeHappenings extends JavaPlugin {

    private BoxManager boxManager;
    private CrystalManager crystalManager;

    @Override
    public void onEnable() {

        boxManager = new BoxManager(this);
        crystalManager = new CrystalManager(this);

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {

        this.getCommand("createbox").setExecutor(new BoxCreationCommand(this));
    }

    private void registerEvents() {

    }

    public BoxManager getBoxManager() {
        return boxManager;
    }

    public CrystalManager getCrystalManager() {
        return crystalManager;
    }

}
