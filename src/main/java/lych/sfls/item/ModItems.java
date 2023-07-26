package lych.sfls.item;

import lych.sfls.SFLSMod;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import static lych.sfls.SFLSMod.make;

@Mod.EventBusSubscriber(modid = SFLSMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModItems {
    public static final MathPaperItem MATH_PAPER = new MathPaperItem(common());

    private ModItems() {}

    private static Item.Properties common() {
        return new Item.Properties().tab(ModItemGroups.SFLS);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(make(MATH_PAPER, ModItemNames.MATH_PAPER));
    }
}
