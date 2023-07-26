package lych.sfls;

import lych.sfls.util.ModSoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistryEntry;

@Mod(SFLSMod.MOD_ID)
public class SFLSMod {
    public static final String MOD_ID = "sfls";

    public SFLSMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModSoundEvents.SOUNDS.register(bus);
    }

    public static <T extends ForgeRegistryEntry<T>> T make(T t, String name) {
        return t.setRegistryName(MOD_ID, name);
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    public static String prefixMsg(String name) {
        return prefixMsg("message", name);
    }

    public static String prefixMsg(String type, String name) {
        return type + "." + MOD_ID + "." + name;
    }
}
