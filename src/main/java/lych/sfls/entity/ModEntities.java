package lych.sfls.entity;

import lych.sfls.SFLSMod;
import lych.sfls.item.ModItemNames;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import static lych.sfls.SFLSMod.make;

@Mod.EventBusSubscriber(modid = SFLSMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEntities {
    public static final EntityType<MathPaperEntity> MATH_PAPER = EntityType.Builder.<MathPaperEntity>of(MathPaperEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).clientTrackingRange(6).updateInterval(10).build(ModItemNames.MATH_PAPER);

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        registry.register(make(MATH_PAPER, ModItemNames.MATH_PAPER));
    }

    private ModEntities() {}
}
