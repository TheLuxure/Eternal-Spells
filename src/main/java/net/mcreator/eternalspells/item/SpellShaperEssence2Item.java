
package net.mcreator.eternalspells.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.eternalspells.itemgroup.SpellShaperItemGroup;
import net.mcreator.eternalspells.EternalSpellsModElements;

@EternalSpellsModElements.ModElement.Tag
public class SpellShaperEssence2Item extends EternalSpellsModElements.ModElement {
	@ObjectHolder("eternal_spells:spell_shaper_essence_2")
	public static final Item block = null;
	public SpellShaperEssence2Item(EternalSpellsModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(SpellShaperItemGroup.tab).maxStackSize(64));
			setRegistryName("spell_shaper_essence_2");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
