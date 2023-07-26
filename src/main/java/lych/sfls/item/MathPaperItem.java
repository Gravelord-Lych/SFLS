package lych.sfls.item;

import lych.sfls.entity.MathPaperEntity;
import lych.sfls.util.ModSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class MathPaperItem extends Item {
    public MathPaperItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);

        level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSoundEvents.MATH_PAPER_USE.get(), SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            MathPaperEntity paper = new MathPaperEntity(player, level);
            paper.setItem(stack);
            paper.shootFromRotation(player, player.xRot, player.yRot, 0, 1.5F, 1);
            level.addFreshEntity(paper);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            stack.shrink(1);
        }

        return ActionResult.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
