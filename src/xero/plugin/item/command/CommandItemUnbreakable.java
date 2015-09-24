package xero.plugin.item.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.plugin.item.Permissions;

public class CommandItemUnbreakable extends ItemCommand
{
	public CommandItemUnbreakable()
	{
		super(null, "내구성을 설정합니다.", Permissions.UNBREAKABLE, 0);
	}

	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		int id = item.getId();
		
		if (id != 257 && id != 270 && id != 274 && id != 278)
			return false;
		
		NBTCompound tag = item.getTag();
		
		if (tag == null)
		{
			item.setTag(tag = NBTCompound.newInstance());
		}
		
		tag.setBoolean("Unbreakable", true);
		
		broadcast(sender, label, componentLabel, item.getName());
		
		return true;
	}
}
