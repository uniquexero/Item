package xero.plugin.item.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.minecraft.nbt.NBTList;
import xero.plugin.item.Permissions;

public class CommandItemEnchant extends ItemCommand
{

	public CommandItemEnchant()
	{
		super("<id> <level>", "인챈트를 변경합니다.", Permissions.ENCHANT, 2);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		String name = args.next();
		Enchantment ench = Enchantment.getByName(name);
		
		if (ench == null)
		{
			sender.sendMessage(Message.NOT_FOUND_ENCHANTMENT.toString(name));
			return true;
		}
		
		int id = ench.getId();
		short level = args.nextShort();
		
		if (level > 0)
		{
			NBTCompound tag = item.getTag();
			
			if (tag == null)
			{
				tag = NBTCompound.newInstance();
				item.setTag(tag);
			}
			
			NBTList list = tag.getList("ench");
			
			if (list == null)
			{
				list = NBTList.newInstance();
				tag.setList("ench", list);
			}
			
			NBTCompound compound = null;
			
			for (int i = 0, size = list.size(); i < size; i++)
			{
				NBTCompound c = list.getCompound(i);
				
				if (c.getShort("id") == id)
				{
					compound = c;
				}
			}
			
			if (compound == null)
			{
				compound = NBTCompound.newInstance();
				list.addCompound(compound);
				compound.setShort("id", (short) id);
			}
			
			compound.setShort("lvl", level);
		}
		else
		{
			NBTCompound tag = item.getTag();
			
			if (tag != null)
			{
				NBTList list = tag.getList("ench");
				
				if (list != null)
				{
					for (int i = 0, size = list.size(); i < size; i++)
					{
						NBTCompound c = list.getCompound(i);
						
						if (c.getShort("id") == id)
						{
							if (size > 1)
							{
								NBTList other = NBTList.newInstance();
								
								for (int j = 0; j < size; j++)
								{
									if (j == i)
										continue;
									
									other.addCompound(list.getCompound(j));
									tag.setList("ench", other);
								}
							}
							else
							{
								tag.remove("ench");
								
								if (tag.isEmpty())
									item.setTag(null);
							}
							
							break;
						}
					}
				}
			}
		}
		
		broadcast(sender, label, componentLabel, ench.getName() + " " + level);
		
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
			
			for (Enchantment ench : Enchantment.values())
			{
				String name = ench.getName();
				
				if (name.regionMatches(true, 0, arg, 0, len))
					complete.add(name);
			}
			
			return complete;
		}
		
		return Collections.emptyList();
	}
}
