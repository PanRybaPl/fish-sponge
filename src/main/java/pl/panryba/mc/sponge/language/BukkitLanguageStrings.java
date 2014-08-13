package pl.panryba.mc.sponge.language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import pl.panryba.mc.sponge.utils.ColorUtils;

public class BukkitLanguageStrings implements FishLanguageStrings {
    private final FileConfiguration config;
    private final FileConfiguration defaults;
    
    public BukkitLanguageStrings(FileConfiguration config, FileConfiguration defaults) {
        this.config = config;
        this.defaults = defaults;
    }

    private String getString(String name) {
        String template = this.config.getString(name);

        if(template == null) {
            template = this.defaults.getString(name);
        }

        if(template == null) {
            Bukkit.getLogger().warning("missing message for " + name);
        }

        return ColorUtils.replaceColors(template);
    }
    
    @Override
    public String getNoItemInHands() {
        return this.getString("no_item_in_hands");
    }
    @Override
    public String getNoSponge() {
        return getString("no_sponge");
    }
    @Override
    public String getNoInventory() {
        return getString("no_inventory");
    }
    @Override
    public String getCannotEnchant() {
        return getString("cannot_enchant");
    }
    @Override
    public String getEnchanted() {
        return getString("enchanted");
    }
}
