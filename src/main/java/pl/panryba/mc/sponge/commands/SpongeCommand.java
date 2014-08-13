package pl.panryba.mc.sponge.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.panryba.mc.sponge.enchant.EnchantResult;
import pl.panryba.mc.sponge.enchant.Enchanter;
import pl.panryba.mc.sponge.impl.BukkitPlayer;
import pl.panryba.mc.sponge.interfaces.FishPlayer;
import pl.panryba.mc.sponge.language.FishLanguageStrings;

public class SpongeCommand implements CommandExecutor {
    private final Enchanter enchanter;
    private final FishLanguageStrings messages;

    public SpongeCommand(Enchanter enchanter, FishLanguageStrings messages) {
        this.enchanter = enchanter;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (!(cs instanceof Player)) {
            return false;
        }

        FishPlayer fishPlayer = new BukkitPlayer((Player) cs);
        return process(fishPlayer);
    }

    public boolean process(FishPlayer fishPlayer) {
        EnchantResult result = this.enchanter.enchantItemInHands(fishPlayer);

        if(!result.getSuccess()) {
            String msg = null;

            switch(result.getReason()) {
                case NO_ITEM_IN_HANDS:
                    msg = this.messages.getNoItemInHands();
                    break;
                case NO_SPONGE:
                    msg = this.messages.getNoSponge();
                    break;
                case NO_INVENTORY_BUT_WHYYYY:
                    msg = this.messages.getNoInventory();
                    break;
                case CANNOT_ENCHANT:
                    msg = this.messages.getCannotEnchant();
                    break;
            }
            
            if(msg != null) {
                fishPlayer.sendMessage(msg);
            }
            
            return true;
        }

        fishPlayer.sendMessage(this.messages.getEnchanted());
        return true;
    }
}
