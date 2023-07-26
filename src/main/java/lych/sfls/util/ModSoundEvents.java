package lych.sfls.util;

import lych.sfls.SFLSMod;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SFLSMod.MOD_ID);
    public static final RegistryObject<SoundEvent> MATH_PAPER_USE = register("item.math_paper.use");

    private ModSoundEvents() {}

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(SFLSMod.prefix(name)));
    }
}
