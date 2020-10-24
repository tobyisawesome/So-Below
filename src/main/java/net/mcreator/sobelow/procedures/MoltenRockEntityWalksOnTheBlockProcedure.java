package net.mcreator.sobelow.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;

import net.mcreator.sobelow.SoBelowModElements;

import java.util.Map;

@SoBelowModElements.ModElement.Tag
public class MoltenRockEntityWalksOnTheBlockProcedure extends SoBelowModElements.ModElement {
	public MoltenRockEntityWalksOnTheBlockProcedure(SoBelowModElements instance) {
		super(instance, 17);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MoltenRockEntityWalksOnTheBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.attackEntityFrom(DamageSource.HOT_FLOOR, (float) 3);
	}
}
