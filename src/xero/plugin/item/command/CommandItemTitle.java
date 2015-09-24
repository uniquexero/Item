package xero.plugin.item.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.plugin.item.Permissions;

public class CommandItemTitle extends ItemCommand
{
	public CommandItemTitle()
	{
		super("<title...>", "쓰여진 책의 제목을 변경합니다.", Permissions.TITLE, 1);
	}
	
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		if (item.getId() != 387)
			return true;
		
		String title = args.hasNext() ? ChatColor.translateAlternateColorCodes('&', args.join()) : null;
		
		if (title != null)
		{
			
			NBTCompound tag = item.getTag();
			
			if (tag == null)
			{
				tag = NBTCompound.newInstance();
				item.setTag(tag);
			}
			
			tag.setString("title", title);
		}
		else
		{
			NBTCompound tag = item.getTag();
			
			if (tag != null)
			{
				tag.remove("title");
				
				if (tag.isEmpty())
					item.setTag(null);
			}
		}
		
		broadcast(sender, label, componentLabel, title == null ? "§7null" : title);
		return true;
	}
}
