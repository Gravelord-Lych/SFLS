package lych.sfls.item;

import lych.sfls.SFLSMod;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public final class ModItemGroups {
    public static final ItemGroup SFLS = new ItemGroup(SFLSMod.MOD_ID) {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.BRICKS);
        }
    };

    private ModItemGroups() {}
}
