
package net.mcreator.eternalspells.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.eternalspells.item.CrystalItem;
import net.mcreator.eternalspells.EternalSpellsModElements;

@EternalSpellsModElements.ModElement.Tag
public class SpellShaperItemGroup extends EternalSpellsModElements.ModElement {
	public SpellShaperItemGroup(EternalSpellsModElements instance) {
		super(instance, 58);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabspell_shaper") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CrystalItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
