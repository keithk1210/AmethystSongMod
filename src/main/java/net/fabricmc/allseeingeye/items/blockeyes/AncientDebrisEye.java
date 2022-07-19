package net.fabricmc.allseeingeye.items.blockeyes;

import net.minecraft.block.Blocks;

import java.util.Arrays;
import java.util.HashSet;

public class AncientDebrisEye extends BaseBlockEye {

    public static final int  MIN_DEPTH = 15;
    public static final int  MAX_DEPTH = 25;

    public AncientDebrisEye(Settings settings) {
        super(settings,new HashSet<>(Arrays.asList(Blocks.ANCIENT_DEBRIS)),30,20);
    }
}
