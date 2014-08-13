package pl.panryba.mc.sponge.enchant;

public class EnchantResult {
    private boolean success;
    private EnchantReason reason;
    
    public EnchantResult(boolean success, EnchantReason reason) {
        this.success = success;
        this.reason = reason;
    }
    
    public static EnchantResult enchanted() {
        return new EnchantResult(true, EnchantReason.ENCHANTED);
    }
    
    public static EnchantResult notEnchanted(EnchantReason reason) {
        return new EnchantResult(false, reason);
    }
    
    public boolean getSuccess() {
        return this.success;
    }
    
    public EnchantReason getReason() {
        return this.reason;
    }
}
