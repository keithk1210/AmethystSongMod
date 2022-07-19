package net.fabricmc.allseeingeye.items;

import net.fabricmc.allseeingeye.items.armor.AllSeeingArmorMaterial;
import net.fabricmc.allseeingeye.items.blockeyes.AncientDebrisEye;
import net.fabricmc.allseeingeye.items.blockeyes.DiamondEye;
import net.fabricmc.allseeingeye.items.structureeyes.AncientCityEye;
import net.fabricmc.allseeingeye.items.structureeyes.OceanMonumentEye;
import net.fabricmc.allseeingeye.items.structureeyes.VillageEye;
import net.fabricmc.allseeingeye.items.structureeyes.WoodlandMansionEye;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistrationHelper {

    public static final ItemGroup EYE_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("allseeingeye", "other"))
            .icon(() -> new ItemStack(Items.ENDER_EYE))
            .build();

    //Block eyes
    public static final Item DIAMOND_EYE = new DiamondEye(new FabricItemSettings().group(EYE_GROUP));
    public static final Item ANCIENT_DEBRIS_EYE = new AncientDebrisEye(new FabricItemSettings().group(EYE_GROUP));
    //Structure Eyes
    public static final Item VILLAGE_EYE = new VillageEye(new FabricItemSettings().group(EYE_GROUP));
    public static final Item WOODLAND_MANSION_EYE = new WoodlandMansionEye(new FabricItemSettings().group(EYE_GROUP));
    public static final Item OCEAN_MONUMENT_EYE = new OceanMonumentEye(new FabricItemSettings().group(EYE_GROUP));
    public static final Item ANCIENT_CITY_EYE = new AncientCityEye(new FabricItemSettings().group(EYE_GROUP));
    public static final ArmorMaterial ALL_SEEING_ARMOR_MATERIAL = new AllSeeingArmorMaterial();
    public static final Item ALL_SEEING_BOOTS = new ArmorItem(ALL_SEEING_ARMOR_MATERIAL, EquipmentSlot.FEET,new FabricItemSettings().group(EYE_GROUP));
    public static final String NAMESPACE = "allseeingeye";

    public ItemRegistrationHelper() {}

    public static void register() {
        //Block eyes
        Registry.register(Registry.ITEM,new Identifier("allseeingeye","diamond_eye"), DIAMOND_EYE);
        Registry.register(Registry.ITEM,new Identifier("allseeingeye","ancient_debris_eye"),ANCIENT_DEBRIS_EYE);
        //Structure eyes
        Registry.register(Registry.ITEM,new Identifier("allseeingeye","village_eye"),VILLAGE_EYE);
        Registry.register(Registry.ITEM, new Identifier(NAMESPACE,"ocean_monument_eye"),OCEAN_MONUMENT_EYE);
        Registry.register(Registry.ITEM,new Identifier("allseeingeye","all_seeing_boots"),ALL_SEEING_BOOTS);
        Registry.register(Registry.ITEM,new Identifier("allseeingeye","woodland_mansion_eye"),WOODLAND_MANSION_EYE);
        Registry.register(Registry.ITEM,new Identifier(NAMESPACE,"ancient_city_eye"),ANCIENT_CITY_EYE);
    }
}
