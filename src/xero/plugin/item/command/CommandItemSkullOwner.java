package xero.plugin.item.command;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.plugin.item.Permissions;

public class CommandItemSkullOwner extends ItemCommand
{
	public CommandItemSkullOwner()
	{
		super("<player>", "머리의 주인을 변경합니다.", Permissions.SKULL_OWNER, 1);
	}

	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		if (item.getId() != 397 || item.getData() != 3)
			return false;
		
		String name = args.next();
		NBTCompound tag = item.getTag();
		
		if (tag == null)
		{
			tag = NBTCompound.newInstance();
			item.setTag(tag);
		}
		
		tag.setString("SkullOwner", name);
		broadcast(sender, label, componentLabel, name);
		return true;
	}
	
	@Override
	public List<String> onTabComplete(Player sender, Command command, String label, String componentLabel, ArgumentList args)
	{
		if (args.isLast())
		{
			return null;
		}
		
		return Collections.emptyList();
	}
}
