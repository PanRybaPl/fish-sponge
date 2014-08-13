package pl.panryba.mc.sponge;

import org.junit.Before;
import org.junit.Test;
import pl.panryba.mc.sponge.commands.SpongeCommand;
import pl.panryba.mc.sponge.enchant.Enchanter;
import pl.panryba.mc.sponge.interfaces.EnchantSelector;
import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;
import pl.panryba.mc.sponge.interfaces.FishItemStack;
import pl.panryba.mc.sponge.language.FishLanguageStrings;
import pl.panryba.mc.sponge.stubs.TestItem;
import pl.panryba.mc.sponge.stubs.TestItemEnchantment;
import pl.panryba.mc.sponge.stubs.TestPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SpongeCommandTest {

    private TestSelector selector;
    private Enchanter enchanter;
    private TestPlayer player;
    private TestStrings strings;
    private SpongeCommand cmd;
    private TestItem item;
    private TestItemEnchantment enchantment;

    private class TestSelector implements EnchantSelector {

        @Override
        public Map<FishItemEnchantment, Integer> select(List<FishItemEnchantment> valid, FishItemStack item) {
            Map<FishItemEnchantment, Integer> result = new HashMap<>();
            if(valid.size() > 0) {
                FishItemEnchantment e = valid.get(0);
                result.put(e, e.getMaxLevel());
            }

            return result;
        }
    }

    private static class TestStrings implements FishLanguageStrings {

        public static String noItemInHands = "noItemInHands";
        public static String noSponge = "noSponge";
        public static String noInventory = "noInventory";
        public static String cannotEnchant = "cannotEnchant";
        public static String enchanted = "enchanted";

        @Override
        public String getNoItemInHands() {
            return noItemInHands;
        }

        @Override
        public String getNoSponge() {
            return noSponge;
        }

        @Override
        public String getNoInventory() {
            return noInventory;
        }

        @Override
        public String getCannotEnchant() {
            return cannotEnchant;
        }

        @Override
        public String getEnchanted() {
            return enchanted;
        }
    }

    @Before
    public void setUp() {
        this.selector = new TestSelector();
        this.enchanter = new Enchanter(selector);
        this.player = new TestPlayer();
        this.strings = new TestStrings();
        this.cmd = new SpongeCommand(enchanter, strings);
        this.item = new TestItem();
        this.enchantment = new TestItemEnchantment();
    }

    @Test
    public void testCommandCallNoItem() {
        cmd.process(player);
        assertEquals(TestStrings.noItemInHands, player.lastMessage);
    }

    @Test
    public void testCommandCallNoItemWithSponge() {
        player.sponges = 1;
        cmd.process(player);
        assertEquals(TestStrings.noItemInHands, player.lastMessage);
    }

    @Test
    public void testCommandCallNoSponge() {
        player.itemInHands = item;
        cmd.process(player);
        assertEquals(TestStrings.noSponge, player.lastMessage);
    }

    @Test
    public void testCommandCallNoEnchantments() {
        player.itemInHands = item;
        player.sponges = 1;
        cmd.process(player);
        assertEquals(TestStrings.cannotEnchant, player.lastMessage);
    }

    @Test
    public void testCommandCallNoInventory() {
        player.itemInHands = item;
        player.sponges = 1;
        player.hasInventory = false;
        cmd.process(player);
        assertEquals(TestStrings.noInventory, player.lastMessage);
    }

    @Test
    public void testCommandCallWithEnchantments() {
        item.enchantments = new ArrayList<>();
        item.enchantments.add(enchantment);

        player.itemInHands = item;
        player.sponges = 1;

        cmd.process(player);

        assertEquals(TestStrings.enchanted, player.lastMessage);
        enchantment.validate();
    }
}
