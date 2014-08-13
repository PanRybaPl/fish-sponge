package pl.panryba.mc.sponge;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.panryba.mc.sponge.commands.SpongeCommand;
import pl.panryba.mc.sponge.enchant.Enchanter;
import pl.panryba.mc.sponge.enchant.RandomEnchantSelector;
import pl.panryba.mc.sponge.interfaces.EnchantSelector;
import pl.panryba.mc.sponge.language.BukkitLanguageStrings;
import pl.panryba.mc.sponge.language.FishLanguageStrings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        EnchantSelector applier = new RandomEnchantSelector();
        Enchanter enchanter = new Enchanter(applier);

        FileConfiguration config = getConfig();
        FishLanguageStrings messages = getLanguageStrings(config);

        CommandExecutor spongeCmd = new SpongeCommand(enchanter, messages);
        getCommand("sponge").setExecutor(spongeCmd);
    }
    
    private BukkitLanguageStrings getLanguageStrings(FileConfiguration config) {
        String locale = readLocale(config);

        YamlConfiguration defaultConfig = new YamlConfiguration();
        InputStream defaultStream = getResource("default_messages.yml");

        try {
            defaultConfig.load(defaultStream);
        } catch (IOException | InvalidConfigurationException ex) {
            Logger.getLogger(Plugin.class.getName()).log(Level.SEVERE, null, ex);
        }

        YamlConfiguration messagesConfig = new YamlConfiguration();
        File messagesFile = new File(getDataFolder(), "messages_" + locale + ".yml");

        try {
            messagesConfig.load(messagesFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Plugin.class.getName()).log(Level.WARNING, "Could not find file: " + messagesFile);
        } catch (IOException | InvalidConfigurationException ex) {
            Logger.getLogger(Plugin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new BukkitLanguageStrings(messagesConfig, defaultConfig);
    }

    private String readLocale(FileConfiguration config) {
        String locale = config.getString("locale", "en");
        if(locale == null || locale.isEmpty()) {
            locale = "en";
        }
        return locale;
    }
}
