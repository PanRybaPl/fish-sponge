package pl.panryba.mc.sponge.enchant;

import pl.panryba.mc.sponge.interfaces.EnchantSelector;
import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;
import pl.panryba.mc.sponge.interfaces.FishItemStack;
import pl.panryba.mc.sponge.interfaces.FishPlayer;

import java.util.List;
import java.util.Map;

public class Enchanter {

    private final EnchantSelector applier;

    public Enchanter(EnchantSelector applier) {
        this.applier = applier;
    }

    public EnchantResult enchantItemInHands(FishPlayer player) {
        FishItemStack held = player.getItemInHand();

        if(held == null || held.isEmpty()) {
            return EnchantResult.notEnchanted(EnchantReason.NO_ITEM_IN_HANDS);
        }

        if(!player.hasInventory()) {
            return EnchantResult.notEnchanted(EnchantReason.NO_INVENTORY_BUT_WHYYYY);
        }

        if(!player.hasSponge()) {
            return EnchantResult.notEnchanted(EnchantReason.NO_SPONGE);
        }
        
        List<FishItemEnchantment> valid = held.getValidEnchantments();
        if(valid == null || valid.isEmpty()) {
            return EnchantResult.notEnchanted(EnchantReason.CANNOT_ENCHANT);
        }

        Map<FishItemEnchantment, Integer> toApply = this.applier.select(valid, held);
        if(toApply.isEmpty()) {
            return EnchantResult.notEnchanted(EnchantReason.CANNOT_ENCHANT);
        }

        for(Map.Entry<FishItemEnchantment, Integer> entry : toApply.entrySet()) {
            entry.getKey().apply(entry.getValue());
        }
        
        player.removeSponge();
        return EnchantResult.enchanted();
    }
}
