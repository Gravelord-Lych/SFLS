package lych.sfls.world.chunkgen;

import lych.sfls.SFLSMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static net.minecraft.util.registry.Registry.register;

@Mod.EventBusSubscriber(modid = SFLSMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModChunkGenerators {
    public static final ResourceLocation DYNAMIC_SEED_CHUNKGEN = SFLSMod.prefix("dynamic_seed_noise");

    private ModChunkGenerators() {}

    @SubscribeEvent
    public static void registerChunkGenerators(FMLCommonSetupEvent event) {
        register(Registry.CHUNK_GENERATOR, DYNAMIC_SEED_CHUNKGEN, DynamicSeedNoiseChunkGenerator.CODEC);
    }
}
