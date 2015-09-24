package xero.plugin.item.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import xero.command.ArgumentList;
import xero.minecraft.item.XeroItemStack;
import xero.minecraft.nbt.NBTCompound;
import xero.plugin.item.Permissions;

public class CommandItemAuthor extends ItemCommand
{

	public CommandItemAuthor()
	{
		super("<author...>", "쓰여진 책의 작성자를 변경합니다.", Permissions.AUTHOR, 0);
	}
	
	@Override
	public boolean onCommand(Player sender, Command command, String label, String componentLabel, ArgumentList args, XeroItemStack item)
	{
		if (item.getId() != 387)
			return false;
		
		String author = args.hasNext() ? ChatColor.translateAlternateColorCodes('&', args.join()) : null;
		
		if (author != null)
		{
			
			NBTCompound tag = item.getTag();
			
			if (tag == null)
			{
				tag = NBTCompound.newInstance();
				item.setTag(tag);
			}
			
			tag.setString("author", author);
		}
		else
		{
			NBTCompound tag = item.getTag();
			
			if (tag != null)
			{
				tag.remove("author");
				
				if (tag.isEmpty())
					item.setTag(null);
			}
		}
		
		broadcast(sender, label, componentLabel, author == null ? "§7null" : author);
		return true;
	}

}
