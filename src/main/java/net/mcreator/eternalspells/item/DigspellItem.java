
package net.mcreator.eternalspells.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.eternalspells.itemgroup.SpellShaperItemGroup;
import net.mcreator.eternalspells.EternalSpellsModElements;

@EternalSpellsModElements.ModElement.Tag
public class DigspellItem extends EternalSpellsModElements.ModElement {
	@ObjectHolder("eternal_spells:digspell")
	public static final Item block = null;
	public DigspellItem(EternalSpellsModElements instance) {
		super(instance, 58);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(SpellShaperItemGroup.tab).maxStackSize(64));
			setRegistryName("digspell");
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

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}
	}
}
