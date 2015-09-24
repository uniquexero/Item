package xero.plugin.item.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.minecraft.nbt.NBTList;
import xero.plugin.item.Permissions;

public class CommandItemPotion extends ItemCommand
{
	public CommandItemPotion()
	{
		super("<type> <duration> [amplifier] [ambient] [particle]", "포션 효과를 설정합니다.", Permissions.POTION, 3);
	}
	
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		if (item.getId() != 373)
			return false;
		
		String name = args.next();
		PotionEffectType type = PotionEffectType.getByName(name);
		
		if (type == null)
		{
			sender.sendMessage(Message.NOT_FOUND_POTION_TYPE.toString(name));
			return true;
		}
		
		@SuppressWarnings("deprecation")
		byte id = (byte) type.getId();
		int duration = args.nextInt();
		
		if (duration > 0)
		{
			byte amplifier = args.hasNext() ? args.nextByte() : 0;
			boolean ambient = args.hasNext() ? args.nextBoolean() : false;
			boolean particle = args.hasNext() ? args.nextBoolean() : false;
			
			NBTCompound tag = item.getTag();
			
			if (tag == null)
			{
				item.setTag(tag = NBTCompound.newInstance());
			}
			
			NBTList list = tag.getList("CustomPotionEffects");
			
			if (list == null)
			{
				tag.setList("CustomPotionEffects", list = NBTList.newInstance());
			}
			
			NBTCompound compound = null;
			
			for (int i = 0, size = list.size(); i < size; i++)
			{
				NBTCompound c = list.getCompound(i);
				
				if (c.getByte("Id") == id)
				{
					compound = c;
					break;
				}
			}
			
			if (compound == null)
			{
				list.addCompound(compound = NBTCompound.newInstance());
				compound.setByte("Id", id);
			}
			
			compound.setInt("Duration", duration);
			compound.setByte("Amplifier", amplifier);
			compound.setBoolean("Ambient", ambient);
			compound.setBoolean("ShowParticles", particle);
			
			broadcast(sender, label, componentLabel, "add " + type.getName() + " " + duration + " " + amplifier);
		}
		else
		{
			NBTCompound tag = item.getTag();
			
			if (tag != null)
			{
				NBTList list = tag.getList("CustomPotionEffects");
				
				if (list != null)
				{
					for (int i = 0, size = list.size(); i < size; i++)
					{
						NBTCompound c = list.getCompound(i);
						
						if (c.getByte("Id") == id)
						{
							if (size > 1)
							{
								NBTList other = NBTList.newInstance();

								for (int j = 0; j < size; j++)
								{
									if (j == i)
										continue;
									
									other.addCompound(list.getCompound(j));
									tag.setList("CustomPotionEffects", other);
								}
							}
							else
							{
								tag.remove("CustomPotionEffects");
								
								if (tag.isEmpty())
									item.setTag(null);
							}
							
							break;
						}
					}
				}
			}
			
			broadcast(sender, label, componentLabel, "remove " + type.getName() + " " + duration);
		}
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(Player sender, Command command, String label, String componentLabel, ArgumentList args)
	{
		if (args.isLast())
		{
			String arg = args.next();
			int len = arg.length();
			ArrayList<String> complete = new ArrayList<>();
			
			for (PotionEffectType type : PotionEffectType.values())
			{
				if (type == null)
					continue;
				
				String name = type.getName();
				
				if (name.regionMatches(true, 0, arg, 0, len))
					complete.add(name);
			}
			
			return complete;
		}
		
		return Collections.emptyList();
	}
}
