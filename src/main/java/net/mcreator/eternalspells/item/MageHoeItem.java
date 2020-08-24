
package net.mcreator.eternalspells.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;

import net.mcreator.eternalspells.itemgroup.SpellShaperItemGroup;
import net.mcreator.eternalspells.EternalSpellsModElements;

@EternalSpellsModElements.ModElement.Tag
public class MageHoeItem extends EternalSpellsModElements.ModElement {
	@ObjectHolder("eternal_spells:mage_hoe")
	public static final Item block = null;
	public MageHoeItem(EternalSpellsModElements instance) {
		super(instance, 33);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new HoeItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 0f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, -3f, new Item.Properties().group(SpellShaperItemGroup.tab)) {
		}.setRegistryName("mage_hoe"));
	}
}
