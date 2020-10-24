package net.mcreator.sobelow.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.sobelow.potion.TurretActivatedPotion;
import net.mcreator.sobelow.SoBelowModElements;

import java.util.Map;

@SoBelowModElements.ModElement.Tag
public class MechaBodyTickEventProcedure extends SoBelowModElements.ModElement {
	public MechaBodyTickEventProcedure(SoBelowModElements instance) {
		super(instance, 38);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MechaBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(TurretActivatedPotion.potion, (int) 1, (int) 0, (false), (false)));
	}
}
