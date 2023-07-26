package lych.sfls.client.renderer;

import lych.sfls.SFLSMod;
import lych.sfls.entity.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;

@Mod.EventBusSubscriber(modid = SFLSMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityRenderers {
    @SubscribeEvent
    public static void registerRenderers(FMLClientSetupEvent event) {
        registerEntityRenderingHandler(ModEntities.MATH_PAPER, manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer(), 1, false));
    }
}
