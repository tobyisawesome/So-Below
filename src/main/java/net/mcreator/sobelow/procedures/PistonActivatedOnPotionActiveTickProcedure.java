package net.mcreator.sobelow.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.sobelow.SoBelowModElements;

import java.util.Map;

@SoBelowModElements.ModElement.Tag
public class PistonActivatedOnPotionActiveTickProcedure extends SoBelowModElements.ModElement {
	public PistonActivatedOnPotionActiveTickProcedure(SoBelowModElements instance) {
		super(instance, 33);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PistonActivatedOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.fallDistance = (float) ((-1));
	}
}
