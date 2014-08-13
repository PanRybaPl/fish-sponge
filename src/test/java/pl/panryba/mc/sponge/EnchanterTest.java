package pl.panryba.mc.sponge;

import org.junit.Before;
import org.junit.Test;
import pl.panryba.mc.sponge.enchant.EnchantReason;
import pl.panryba.mc.sponge.enchant.EnchantResult;
import pl.panryba.mc.sponge.enchant.Enchanter;
import pl.panryba.mc.sponge.enchant.RandomEnchantSelector;
import pl.panryba.mc.sponge.interfaces.EnchantSelector;
import pl.panryba.mc.sponge.interfaces.FishItemEnchantment;
import pl.panryba.mc.sponge.interfaces.FishItemStack;
import pl.panryba.mc.sponge.stubs.TestPlayer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class EnchanterTest {

    private Enchanter e;
    private TestPlayer p;



    class TestItem implements FishItemStack {
        public List<FishItemEnchantment> enchantments;

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<FishItemEnchantment> getValidEnchantments() {
            return enchantments;
        }
    }

    class TestItemEnchantment implements FishItemEnchantment {
        private boolean applyCalled;

        @Override
        public int getMaxLevel() {
            return 0;
        }

        @Override
        public int getStartLevel() {
            return 0;
        }

        @Override
        public void apply(int level) {
            applyCalled = true;
        }

        public void validate() {
            assertTrue(applyCalled);
        }
    }

    @Before
    public void setUp() {
        EnchantSelector applier = new RandomEnchantSelector();
        this.e = new Enchanter(applier);
        this.p = new TestPlayer();
    }

    @Test
    public void testEnchantWhenNoItemInHands() {
        EnchantResult r =  e.enchantItemInHands(p);

        assertFalse(r.getSuccess());
        assertEquals(EnchantReason.NO_ITEM_IN_HANDS, r.getReason());
    }

    @Test
    public void testEnchantWhenItemInHandsButNoSponge() {
        p.itemInHands = new TestItem();

        EnchantResult r = e.enchantItemInHands(p);
        assertFalse(r.getSuccess());
        assertEquals(EnchantReason.NO_SPONGE, r.getReason());
    }

    @Test
    public void testEnchantWhenItemInHandsButNullEnchantments() {
        p.itemInHands = new TestItem();
        p.sponges = 1;

        EnchantResult r = e.enchantItemInHands(p);
        assertFalse(r.getSuccess());
        assertEquals(EnchantReason.CANNOT_ENCHANT, r.getReason());
    }

    @Test
    public void testEnchantWhenItemInHandsButNoValidEnchantment() {
        TestItem item = new TestItem();
        item.enchantments = new ArrayList<>();
        p.itemInHands = item;
        p.sponges = 1;

        EnchantResult r = e.enchantItemInHands(p);
        assertFalse(r.getSuccess());
        assertEquals(EnchantReason.CANNOT_ENCHANT, r.getReason());
    }


    @Test
    public void testEnchant() {
        TestItem item = new TestItem();
        item.enchantments = new ArrayList<>();

        TestItemEnchantment enchant = new TestItemEnchantment();
        item.enchantments.add(enchant);

        p.itemInHands = item;
        p.sponges = 1;

        EnchantResult r = e.enchantItemInHands(p);
        assertTrue(r.getSuccess());
        enchant.validate();
    }
}
