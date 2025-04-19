package com.dogwhistlemod.items;

import java.util.List;
import java.util.function.Consumer;


import com.dogwhistlemod.components.Components;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DogWhistleItem extends Item{



	//indicate to user if mob is sitting or standing
	// also why tf is this depreciated?  there's no way of adding tooltips without this method. I tore my hair out worrying about this needless depreciation for nothing. - @989onan
	@Override
	public void appendTooltip(ItemStack itemstack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {

		if (itemstack.contains(Components.SIT_OR_STAND)) {
			if(itemstack.get(Components.SIT_OR_STAND) == 0){
				textConsumer.accept(Text.translatable("itemTooltip.onansdogwhistlemod.dog_whistle_stand"));
			}
			else{
				textConsumer.accept(Text.translatable("itemTooltip.onansdogwhistlemod.dog_whistle_sit"));
			}
		}
	}
	
	public DogWhistleItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public ActionResult use(World world, PlayerEntity player, Hand hand) {
		if (world.isClient) {
			return ActionResult.PASS;
		}

		ItemStack itemstack = player.getStackInHand(hand);

		
        if(!player.isSneaking()) {
	        
			
			List<WolfEntity> list = world.getEntitiesByClass(WolfEntity.class, player.getBoundingBox().expand(40.0), (o) -> o.getOwner() == player);
			if(itemstack.get(Components.SIT_OR_STAND) == 0){
				list.forEach((wolf) ->{
					wolf.setSitting(true);

				});
			}
			else {
				list.forEach((wolf) ->{
					wolf.setSitting(false);
				});
			}
			//player.increaseStat(Stats.USED.getOrCreateStat(Onansdogwhistlemod.DOG_WHISTLE_REGISTRY), 1);
        }
        else { //if the player is standing, change mode
			if (itemstack.contains(Components.SIT_OR_STAND)) {
				itemstack.set(Components.SIT_OR_STAND, (short)(itemstack.get(Components.SIT_OR_STAND) == 0 ? 1:0));
			}
        }

		
		
		return ActionResult.SUCCESS;
	}
	
}
