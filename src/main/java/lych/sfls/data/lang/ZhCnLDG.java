package lych.sfls.data.lang;

import lych.sfls.entity.ModEntities;
import lych.sfls.item.ModItems;
import lych.sfls.util.ModSoundEvents;
import net.minecraft.data.DataGenerator;

public class ZhCnLDG extends BaseLanguageDataGen {
    public ZhCnLDG(DataGenerator gen) {
        super(gen, "zh_cn");
    }

    @Override
    protected void addItems() {
        addItem(ModItems.MATH_PAPER, "数学试卷");
    }

    @Override
    protected void addEntities() {
        addEntityType(ModEntities.MATH_PAPER, "数学试卷");
    }

    @Override
    protected void addSubtitles() {
        addSubtitle(ModSoundEvents.MATH_PAPER_USE, "数学试卷：使用");
    }

    @Override
    protected void addMiscellaneous() {
        add(ITEM_GROUP, "SFLS");
    }
}
