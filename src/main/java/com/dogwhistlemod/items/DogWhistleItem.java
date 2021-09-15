package com.dogwhistlemod.items;

import java.util.List;


import com.google.common.collect.Lists;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DogWhistleItem extends Item{
	
	
	
	
	
	public DogWhistleItem(Properties p_41383_) {
		super(p_41383_);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		
		CompoundTag compoundtag = itemstack.getOrCreateTag();
        if (!compoundtag.contains("SitOrStand")) {
           compoundtag.putShort("SitOrStand", (short) 0);
        }
		
        if(!player.isCrouching()) {
	        
			
			List<Wolf> list = world.getEntitiesOfClass(Wolf.class, player.getBoundingBox().inflate(40.0));
			List<Wolf> list1 = Lists.newArrayList();
			
			list.forEach((wolf)->{
				if (wolf.getOwner() == player) {
					list1.add(wolf);
				}
			});
			if(((ShortTag) compoundtag.get("SitOrStand")).getAsInt() == 0){
				list1.forEach((wolf) ->{
					((Wolf)wolf).setOrderedToSit(true);
				});
			}
			else {
				list1.forEach((wolf) ->{
					((Wolf)wolf).setOrderedToSit(false);
				});
			}
			player.awardStat(Stats.ITEM_USED.get(this));
        }
        else { //if the player is standing, change mode
        	if(((ShortTag) compoundtag.get("SitOrStand")).getAsInt() == 0) {
        		compoundtag.putShort("SitOrStand", (short) 1);
        	}
        	else {
        		compoundtag.putShort("SitOrStand", (short) 0);
        	}
        }
		
		
		return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
	}
	
}
